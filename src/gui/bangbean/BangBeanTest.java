package gui.bangbean;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.TooManyListenersException;

import static net.mindview.util.SwingConsole.run;

/**
 * {Timeout: 5} Abort after 5 seconds when testing.
 */
public class BangBeanTest extends JFrame {
    private JTextField txt = new JTextField(20);

    // During testing, report actions:
    class BBL implements ActionListener {
        private int count = 0;

        @Override
        public void actionPerformed(ActionEvent e) {
            txt.setText("BangBean action " + count++);
        }
    }

    public BangBeanTest() {
        BangBean bb = new BangBean();
        try {
            bb.addActionListener(new BBL());
        } catch (TooManyListenersException e) {
            txt.setText("Too many listeners");
        }
        add(bb);
        add(BorderLayout.SOUTH, txt);
    }

    public static void main(String[] args) {
        run(new BangBeanTest(), 400, 500);
    }
}