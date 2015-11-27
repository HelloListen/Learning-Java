import java.util.*;

/**
 * This program demonstrates object construction.
 *
 * @author Cay Horstmann
 * @version 1.01 2004-02-19
 */

public class ConstructorTest {
    public static void main(String[] args) {

    }
}

class Employee {
    private static int nextId;

    private int id;
    private String name = "";
    private double salary;

    static {
        Random generator = new Random();
        nextId = generator.nextInt(10000);
    }

    {
        id = nextId;
        nextId++;
    }

    public Employee(String n, double s) {
        name = n;
        salary = s;
    }

    public Employee(double s) {
        this("Employee # " + nextId, s);
    }

    public Employee() {

    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public int getId() {
        return id;
    }
}