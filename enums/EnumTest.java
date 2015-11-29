package enums;

import java.util.*;

/**
 * This program demonstrates enumerated types.
 *
 * @author Cay Horstmann
 * @version 1.0 2004-05-24
 */

public class EnumTest {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter a size: (SMALL,MEDIUM,LARGE,EXTRA_LARGE)");
        String input = in.next().toUpperCase();
        Size size = Enum.valueOf(Size.class, input);
        Size[] values=Size.values();
        System.out.println(values);
        System.out.println("size=" + size);
        System.out.println("abbreviation=" + size.abbr());
        if (size == Size.EXTRA_LARGE) {
            System.out.println("Good job--you paid attention to the _.");
        }
    }
}

enum Size {
    SMALL("S"), MEDIUM("M"), LARGE("L"), EXTRA_LARGE("XL");

    private String abbr;

    private Size(String abbreviation) {
        abbr = abbreviation;
    }

    public String abbr() {
        return abbr;
    }
}