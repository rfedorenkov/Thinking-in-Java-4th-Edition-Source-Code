package exercises.chapter22;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

import static net.mindview.util.SwingConsole.run;

/**
 * Exercise 10
 * Create an application using SwingConsole, with a JButton
 * and a JTextField. Write and attach the appropriate listener
 * so that if the button has the focus, characters typed into
 * it will appear in the JTextField.
 */
public class E10_TypeableButton extends JFrame {
    private JButton button = new JButton("Button");
    private JTextField textField = new JTextField(10);

    public E10_TypeableButton() {
        setLayout(new FlowLayout());
        textField.setEditable(false);
        add(button);
        add(textField);
        button.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                textField.setText(textField.getText() + e.getKeyChar());
            }
        });
    }

    public static void main(String[] args) {
        run(new E10_TypeableButton(), 200, 100);
    }
}