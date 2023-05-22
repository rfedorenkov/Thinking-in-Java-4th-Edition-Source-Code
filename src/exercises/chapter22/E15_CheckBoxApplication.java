package exercises.chapter22;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static net.mindview.util.SwingConsole.run;

/**
 * Exercise 15
 * Add a check box to the application created in Exercise 5,
 * capture the event, and insert different text into the
 * text field.
 */
public class E15_CheckBoxApplication extends JFrame {
    private JButton
            b1 = new JButton("Button 1"),
            b2 = new JButton("Button 2"),
            b3 = new JButton("Button 3");
    private JTextField txt = new JTextField(12);
    private JCheckBox checkBox = new JCheckBox("CheckBox");
    private ActionListener bl = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = ((JButton) e.getSource()).getText();
            txt.setText(name);
        }
    };

    public E15_CheckBoxApplication() {
        b1.addActionListener(bl);
        b2.addActionListener(bl);
        b3.addActionListener(bl);
        setLayout(new FlowLayout());
        add(b1);
        add(b2);
        add(b3);
        add(txt);
        add(checkBox);
        checkBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkBox.isSelected())
                    txt.setText(checkBox.getText() + " checked");
                else
                    txt.setText(checkBox.getText() + " unchecked");
            }
        });
    }

    public static void main(String[] args) {
        run(new E15_CheckBoxApplication(), 200, 200);
    }
}