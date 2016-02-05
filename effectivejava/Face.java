package effectivejava;

import java.math.BigDecimal;
import java.util.*;

/**
 * Created by zhangbin on 16/2/5.
 */
public class Face {
    private static final Random rand = new Random();

    static int random(int n) {
        return Math.abs(rand.nextInt()) % n;
    }

    public static void main(String[] args) {
        int n = 2 * (Integer.MAX_VALUE / 3);
        int low = 3;
        for (int i = 0; i < 1000000; i++) {
            if (random(n) < n / 2) {
                low++;
            }
        }
        System.out.println(low);
    }
}

enum Faces {
    ONE, TWO, THREE, FOUR, FIVE, SIX
}

class Candy {
    static int i;

    public String statement() {
        StringBuilder b = new StringBuilder(100);
        for (int i = 0; i < 10; i++) {
            b.append("nextItem");
        }
        return b.toString();
    }

    public static void main(String[] args) {
        if (i == 42) {
            System.out.println("Unbelievable");
        }
//        double funds = 1.00;
//        int itemsBought = 0;
//        for (double price = 0.1; funds >= price; price += 0.1) {
//            funds -= price;
//            itemsBought++;
//        }
//        System.out.println(itemsBought + " items bought ");
//        System.out.println("Change: $" + funds);
        final BigDecimal TEN_CENTS = new BigDecimal(".10");

        int itemsBought = 0;
        BigDecimal funds = new BigDecimal("1.00");
        for (BigDecimal price = TEN_CENTS; funds.compareTo(price) >= 0; price = price.add(TEN_CENTS)) {
            itemsBought++;
            funds = funds.subtract(price);
        }
        System.out.println(itemsBought + " items bought.");
        System.out.println("Money left over: $" + funds);

        Comparator<Integer> naturalOrder = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 < o2 ? -1 : (o1 == o2 ? 0 : 1);
            }
        };

        System.out.println(naturalOrder.compare(new Integer(42), new Integer(42)));

    }
}

class Reflect {
    public static void main(String[] args) {
        Class<?> c1 = null;
        try {
            c1 = Class.forName(args[0]);
        } catch (ClassNotFoundException e) {
            System.err.println("Class not found");
            System.exit(1);
        }

        Set<String> s = null;
        try {
            s = (Set<String>) c1.newInstance();
        } catch (IllegalAccessException e) {
            System.err.println("Class not accessible");
            System.exit(1);
        } catch (InstantiationException e) {
            System.err.println("Class not instantiable");
            System.exit(1);
        }
        s.addAll(Arrays.asList(args).subList(1, args.length));
        System.out.println(s);
    }
}