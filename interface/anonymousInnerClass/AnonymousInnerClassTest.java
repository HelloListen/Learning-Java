//package anonymousInnerClass;

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;

/**
 * This program demonstrates anonymous inner classes.
 *
 * @version 1.10 2004-02-27
 */

public class AnonymousInnerClassTest {
    public static void main(String[] args) {
        TalkingClock clock = new TalkingClock();
        clock.start(1000, true);

        JOptionPane.showMessageDialog(null, "Quit praogram?");
        System.exit(0);
    }
}

class TalkingClock {
    /**
     * Starts the clock.
     *
     * @param interval the interval between messages (in milliseconds)
     * @param beep     true if the clock should beep
     */
    public void start(int interval, final boolean beep) {
        ActionListener listener = new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                Date now = new Date();
                System.out.println("At the tone, the time is " + now);
                if (beep) {
                    Toolkit.getDefaultToolkit().beep();
                }
            }
        };
        Timer t = new Timer(interval, listener);
        t.start();
    }
}