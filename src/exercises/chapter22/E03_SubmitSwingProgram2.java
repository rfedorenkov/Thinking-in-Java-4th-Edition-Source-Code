package exercises.chapter22;

import net.mindview.util.SwingConsole;

import javax.swing.*;
import java.util.concurrent.TimeUnit;

/**
 * Exercise 3
 * Modify SubmitSwingProgram.java so that it uses SwingConsole.
 */
public class E03_SubmitSwingProgram2 extends JFrame {
    JLabel label;

    public E03_SubmitSwingProgram2() {
        label = new JLabel("A Label");
        add(label);
    }

    static E03_SubmitSwingProgram2 ssp;

    public static void main(String[] args) throws Exception {
        ssp = new E03_SubmitSwingProgram2();
        SwingConsole.run(ssp, 300, 100);

        TimeUnit.SECONDS.sleep(1);
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ssp.label.setText("Hey! This is Different!");
            }
        });
    }
}