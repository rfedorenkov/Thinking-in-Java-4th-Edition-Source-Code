package exercises.chapter22;

import javax.swing.*;

import java.awt.*;

import static net.mindview.util.SwingConsole.run;

/**
 * Exercise 8
 * Almost every Swing component is derived from Component,
 * which has a setCursor() method. Look this up in the JDK
 * documentation. Create an application and change the cursor
 * to one of the stock cursors in the Cursor class.
 */
public class E08_Cursors extends JFrame {
    private JTextField textField = new JTextField(20);
    private JButton button = new JButton("Button");

    E08_Cursors() {
        setLayout(new FlowLayout());
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        add(textField);
        add(button);
    }

    public static void main(String[] args) {
        run(new E08_Cursors(), 300, 100);
    }
}