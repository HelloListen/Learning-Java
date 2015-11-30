class Employee implements Comparable<Employee> {
    public int compareTo(Employee other) {

        if (anObject instanceof Comparable) {
            //...
        }

        return Double.compare(salary, other.salary);
    }
}

public interface Moveable {
    void move(double x, double y);
}

public interface Powered extends Moveable {
    double milesPerGallon();

    double SPEED_LIMIT = 95;
}

class Employee implements Cloneable, Comparable {
    //...
}

class Employee implements Cloneable {
    public Employee clone() throws CloneNotSupportedException {
        return (Employee) super.clone();
    }
}

class Employee implements Cloneable {
    public Employee clone() throws CloneNotSupportedException {
        Employee cloned = (Employee) super.clone();

        cloned.hireDay = (Date) hireDay.clone();
        return cloned;
    }
}

class TimePrinter implements ActionListener {
    public void actionPerformed(ActionEvent event) {
        Date now = new Date();
        System.out.println("At the tone, the time is " + now);
        Toolkit.getDefaultToolkit().beep();
    }
}

public class TalkingClock {
    private int interval;
    private boolean beep;

    public TalkingClock(int interval, boolean beep) {...}

    public void start() {...}

    public class TimePrinter implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            Date now = new Date();
            System.out.println("At the tone, the time is " + now);
            if (beep){
                Toolkit.getDefaultToolkit().beep();
            }
        }
    }

    public void start(int interval,final boolean beep){
        ActionListener listener=new ActionListener(){
            public void actionPerformed(ActionEvent event){
                Date now=new Date();
                System.out.println("At the tone, the time is "+now);
                if (beep){
                    Toolkit.getDefaultToolkit().beep();
                }
            }
        }
        Timer t=new Timer(interval,listener);
        t.start();
    }
}

