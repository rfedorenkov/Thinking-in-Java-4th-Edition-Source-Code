package exercises.chapter22;

import javax.swing.*;

/**
 * Exercise 1
 * Modify HelloSwing.java to prove to yourself that the
 * application will not close without the call to
 * setDefaultCloseOperation().
 */
public class E01_HelloSwing2 {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Hello Swing");
        // frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setSize(300, 100);
        frame.setVisible(true);
    }
}