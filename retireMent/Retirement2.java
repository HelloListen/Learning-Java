import java.util.*;

/**
 * This program demonstrates a <code>do/while</code> loop.
 *
 * @author listen
 * @version 1.20 2004-02-10
 */

public class Retirement2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("How much money will you contribute every year? ");
        double payment = in.nextDouble();
        System.out.print("Interest rate in %:");
        double interestRate = in.nextDouble();
        double balance = 0;
        int year = 0;

        String input;

        do {
            balance += payment;
            double interest = balance * interestRate / 100;
            balance += interest;

            year++;

            System.out.printf("After year %d, your balance is %,.2f%n", year, balance);

            System.out.print("Ready to retire?(Y/N)");
            input = in.next();
        } while (input.equalsIgnoreCase("N"));
    }
}