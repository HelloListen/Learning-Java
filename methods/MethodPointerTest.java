//package methods;

import java.lang.reflect.*;

/**
 * This program demonstrates how to invoke methods through reflection.
 */

public class MethodPointerTest {
    public static void main(String[] args) throws Exception {
        //get method pointers to the square and sqrt methods.
        Method square = MethodPointerTest.class.getMethod("square", double.class);
        Method sqrt = Math.class.getMethod("sqrt", double.class);

        //print tables of x- and y-values
        printTable(1, 10, 10, square);
        printTable(1, 10, 10, sqrt);
    }

    /**
     * Returns the square of a number
     *
     * @param x a number
     * @param x squared
     */
    public static double square(double x) {
        return x * x;
    }

    /**
     * Prints a table with x- and y-values for a method
     *
     * @param from the lower bound for the x-values
     * @param to   the upper bound for the x-values
     * @param n    the number of rows in the table
     * @param f    a method with a double parameter and double return value
     */
    public static void printTable(double from, double to, int n, Method f) {
        //print
        System.out.println(f);

        double dx = (to - from) / (n - 1);
        for (double x = from; x <= 10; x += dx) {
            try {
                double y = (Double) f.invoke(null, x);
                System.out.printf("%10.4f | %10.4f%n", x, y);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}