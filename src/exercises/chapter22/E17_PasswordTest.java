package exercises.chapter22;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static net.mindview.util.SwingConsole.run;

/**
 * Exercise 17
 * Create an application using SwingConsole. In the
 * JDK documentation from http://java.sun.com, find
 * the JPasswordField and add this to the program.
 * If the user types in the correct password, use
 * JOptionPane to provide a success message to the user.
 */
class Password extends JFrame {
    private JLabel txt = new JLabel("Enter your password");
    private JPasswordField pwd = new JPasswordField(12);

    public Password() {
        setLayout(new FlowLayout());
        add(txt);
        add(pwd);
        pwd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPasswordField pwd = (JPasswordField) e.getSource();
                if ("123".equals(new String(pwd.getPassword())))
                    JOptionPane.showMessageDialog(null,
                            "Correct", "Access", JOptionPane.INFORMATION_MESSAGE);
                else
                    JOptionPane.showMessageDialog(null,
                            "Incorrect", "Access", JOptionPane.ERROR_MESSAGE);
                pwd.setText("");
            }
        });
    }
}

public class E17_PasswordTest {
    public static void main(String[] args) {
        run(new Password(), 200, 100);
    }
}