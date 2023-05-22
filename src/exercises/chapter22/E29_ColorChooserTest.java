package exercises.chapter22;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static net.mindview.util.SwingConsole.run;

/**
 * Exercise 29
 * In the JDK documentation for javax.swing, look up
 * the JColorChooser. Write a program with a button
 * that brings up the color chooser as a dialog.
 */
class ColorChooser extends JFrame {
    JButton b1 = new JButton("Color Chooser");
    JPanel p = new JPanel();

    public ColorChooser() {
        setLayout(new GridLayout(2, 1));
        add(b1);
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Color color = JColorChooser.showDialog(null, "E29_ColorChooserTest", Color.RED);
                if (color != null)
                    p.setBackground(color);
            }
        });
        add(p);
    }
}

public class E29_ColorChooserTest {
    public static void main(String[] args) {
        run(new ColorChooser(), 150, 300);
    }
}