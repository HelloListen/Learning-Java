import com.horstmann.corejava.*;

import static java.lang.System.*;

/**
 * This program demonstrates the use of packages.
 *
 * @author Cay Hrostmann
 * @version 1.11 2004-02-19
 */

public class PackageTest {
    /**
     * main method
     * @param args
     * @return none
     */
    public static void main(String[] args) {
        Employee harry = new Employee("Harry Hacker", 50000, 1989, 10, 1);
        harry.raiseSalary(5);

        out.println("name= " + harry.getName() + " ,salary=" + harry.getSalary());
    }
}