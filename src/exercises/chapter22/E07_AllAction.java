package exercises.chapter22;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static net.mindview.util.SwingConsole.run;

/**
 * Exercise 7
 * Create an application using SwingConsole, and add all
 * the Swing components that have addActionListener() method.
 * (Look these up in the JDK documentation from http://java.sun.com.
 * Hint: Search for addActionListener() using the index).
 * Capture their events and display an appropriate message for
 * each inside a text field.
 */
public class E07_AllAction extends JFrame {
    JMenuItem menuItem = new JMenuItem("Menu Item");
    JTextField textField = new JTextField(30);
    JButton button = new JButton("Button");
    JComboBox<String> comboBox = new JComboBox<>(new String[] {
            "Elements", "To", "Place", "In", "Combobox"
    });
    JFileChooser fileChooser = new JFileChooser(".");

    public E07_AllAction() {
        setLayout(new FlowLayout());
        add(menuItem);
        add(textField);
        add(button);
        add(comboBox);
        add(fileChooser);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField.setText("Button pressed");
            }
        });
        textField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(
                        null,
                        "JTextField ActionListener fired",
                        "information",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });
        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField.setText("JComboBox selected: " + comboBox.getSelectedItem());
            }
        });
        fileChooser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField.setText("FileChooser ActionListener fired: " +
                        fileChooser.getSelectedFile());
            }
        });
        menuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField.setText("JMenuItem selected");
            }
        });
        new Timer(5000, new ActionListener() {
            int i = 0;
            @Override
            public void actionPerformed(ActionEvent e) {
                textField.setText("Timer Ticked " + i++);
            }
        }).start();
    }

    public static void main(String[] args) {
        run(new E07_AllAction(), 600, 500);
    }
}