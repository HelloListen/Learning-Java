//package innerClass;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;

/**
 * This program demonstrates the use of inner classes.
 *
 * @version 1.10 2004-02-27
 */
public class InnerClassTest {
    public static void main(String[] args) {
        TalkingClock clock = new TalkingClock(1000, true);
        clock.start();

        JOptionPane.showMessageDialog(null, "Quit program?");
        System.exit(0);
    }
}

/**
 * A clock that prints the time in regular intervals.
 */
class TalkingClock {
    private int interval;
    //private boolean beep;

    /**
     * Constructors a talking clock
     *
     * @param interval the interval between messages (in millseconds)
     * @param beep     true if the clock should beep
     */
    public TalkingClock(int interval, boolean beep) {
        this.interval = interval;
        //this.beep = beep;
    }

    /**
     * Starts the clock.
     */
    public void start(int interval,final boolean beep) {
        class TimePrinter implements ActionListener {
            public void actionPerformed(ActionEvent event) {
                Date now = new Date();
                System.out.println("At the tone ,the time is " + now);
                if (beep) {
                    Toolkit.getDefaultToolkit().beep();
                }
            }
        }
        ActionListener listener = new TimePrinter();
        Timer t = new Timer(interval, listener);
        t.start();
    }

//    public class TimePrinter implements ActionListener {
//        public void actionPerformed(ActionEvent event) {
//            Date now = new Date();
//            System.out.println("At the tone ,the time is " + now);
//            if (beep) {
//                Toolkit.getDefaultToolkit().beep();
//            }
//        }
//    }
}