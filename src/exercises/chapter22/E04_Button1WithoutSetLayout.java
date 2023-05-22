package exercises.chapter22;

import javax.swing.*;

import static net.mindview.util.SwingConsole.run;

/**
 * Exercise 4
 * Verify that without the setLayout() call in Button1.java,
 * only one button will appear in the resulting program.
 */
public class E04_Button1WithoutSetLayout extends JFrame {
    private JButton
            b1 = new JButton("Button 1"),
            b2 = new JButton("Button 2");

    public E04_Button1WithoutSetLayout() {
        // setLayout(new FlowLayout());
        add(b1);
        add(b2);
    }

    public static void main(String[] args) {
        run(new E04_Button1WithoutSetLayout(), 200, 100);
    }
}