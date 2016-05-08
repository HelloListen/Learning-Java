package util;

import java.io.PrintStream;

/**
 * Created by zhangbin on 16/5/8.
 */
public class Print {
    public static void print(Object object) {
        System.out.println(object);
    }

    public static void print() {
        System.out.println();
    }

    public static void printnb(Object object) {
        System.out.print(object);
    }

    public static PrintStream printf(String format, Object... args) {
        return System.out.printf(format, args);
    }
}
