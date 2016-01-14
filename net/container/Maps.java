package container;

import net.mindview.util.CountingMapData;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by zhangbin on 16/1/14.
 */
public class Maps {
    public static void printKeys(Map<Integer, String> map) {
        System.out.println("Size = " + map.size() + ", ");
        System.out.println("Keys: ");
        System.out.print(map.keySet());
    }

    public static void test(Map<Integer, String> map) {
        System.out.println(map.getClass().getSimpleName());
        map.putAll(new CountingMapData(25));
        map.putAll(new CountingMapData(25));

        printKeys(map);

        System.out.println("Values: ");
        System.out.print(map.values());
        System.out.print(map);
        System.out.print("map.containsKey(11): " + map.containsKey(11));
        System.out.print("map.get(11): " + map.get(11));
        System.out.println("map.containsValue(\"F0\"): " + map.containsValue("F0"));

        Integer key = map.keySet().iterator().next();
        System.out.print("First key in map: " + key);

        map.remove(key);
        printKeys(map);
        map.clear();
        System.out.print("map.isEmpty(): " + map.isEmpty());
        map.putAll(new CountingMapData(25));

        map.keySet().removeAll(map.keySet());
        System.out.println("map.isEmpty(): " + map.isEmpty());
    }

    public static void main(String[] args) {
        test(new HashMap<>());
        test(new TreeMap<>());
        test(new LinkedHashMap<>());
        test(new IdentityHashMap<>());
        test(new WeakHashMap<>());
        test(new ConcurrentHashMap<>());
    }
}
