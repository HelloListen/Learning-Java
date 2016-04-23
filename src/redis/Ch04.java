package redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Transaction;

import java.lang.reflect.Method;
import java.util.List;

/**
 * Created by zhangbin on 16/4/23.
 */
public class Ch04 {

    public boolean listItem(Jedis conn, String itemId, String sellerId, double price) {
        String inventory = "inventory:" + sellerId;
        String item = itemId + "." + sellerId;
        long end = System.currentTimeMillis() + 5000;

        while (System.currentTimeMillis() < end) {
            conn.watch(inventory);
            if (!conn.sismember(inventory, itemId)) {
                conn.unwatch();
                return false;
            }

            Transaction trans = conn.multi();
            trans.zadd("market", price, item);
            trans.srem(inventory, item);
            List<Object> results = trans.exec();
            if (results == null) {
                continue;
            }
            return true;
        }
        return false;
    }

    public boolean purchaseItem(Jedis conn, String buyerId, String itemId, String sellerId, double lprice) {
        String buyer = "users:" + buyerId;
        String seller = "sellers:" + sellerId;
        String item = itemId + "." + sellerId;
        String inventory = "inventory:" + buyerId;
        long end = System.currentTimeMillis() + 10000;

        while (System.currentTimeMillis() < end) {
            conn.watch("market", buyer);

            double price = conn.zscore("market:", item);
            double funds = Double.parseDouble(conn.hget(buyer, "funds"));
            if (price != lprice || price > funds) {
                conn.unwatch();
                return false;
            }

            Transaction trans = conn.multi();
            trans.hincrBy(seller, "funds", (int) price);
            trans.hincrBy(buyer, "funds", (int) -price);
            trans.sadd(inventory, itemId);
            trans.zrem("market:", item);
            List<Object> results = trans.exec();
            if (results == null) {
                continue;
            }
            return true;
        }
        return false;
    }

    public void updateTokenPipeline(Jedis conn, String token, String user, String item) {
        long timestamp = System.currentTimeMillis() / 1000;
        Pipeline pipe = conn.pipelined();
        pipe.hset("login:", token, user);
        pipe.zadd("recent:", timestamp, token);
        if (item != null) {
            pipe.zadd("viewed:" + token, timestamp, item);
            pipe.zremrangeByRank("viewed:", 0, -26);
            pipe.zincrby("viewed:", -1, item);
        }
        pipe.exec();
    }

    public void benchmarkUpdateToken(Jedis conn, int duration) {
        try {
            @SuppressWarnings("rawtypes")
            Class[] args = new Class[]{
                    Jedis.class, String.class, String.class, String.class
            };
            Method[] methods = new Method[]{
                    this.getClass().getDeclaredMethod("updateToken", args),
                    this.getClass().getDeclaredMethod("updateTokenPipeline", args),
            };
            for (Method method : methods) {
                int count = 0;
                long start = System.currentTimeMillis();
                long end = start + (duration * 1000);
                while (System.currentTimeMillis() < end) {
                    count++;
                    method.invoke(this, conn, "token", "user", "item");
                }
                long delta = System.currentTimeMillis() - start;
                System.out.println(
                        method.getName() + ' ' +
                                conn + ' ' +
                                (delta / 1000) + ' ' +
                                (count / (delta / 1000))
                );
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
