package exercises.chapter22;

import net.mindview.util.Generator;
import net.mindview.util.RandomGenerator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static net.mindview.util.SwingConsole.run;

/**
 * Exercise 14
 * Modify TextPane.java to use a JTextArea instead of a JTextPane.
 */
class TextPane extends JFrame {
    private JButton b = new JButton("Add Text");
    private JTextArea tp = new JTextArea();
    private static Generator sg = new RandomGenerator.String(7);

    public TextPane() {
        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 1; i < 10; i++)
                    tp.setText(tp.getText() + sg.next() + "\n");
            }
        });
        add(new JScrollPane(tp));
        add(BorderLayout.SOUTH, b);
    }
}

public class E14_UseTextArea extends JFrame {
    public static void main(String[] args) {
        run(new TextPane(), 475, 425);
    }
}