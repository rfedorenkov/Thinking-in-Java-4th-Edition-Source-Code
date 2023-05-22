package exercises.chapter22;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import static net.mindview.util.SwingConsole.run;

/**
 * Exercise 11
 * Inherit a new type of button from JButton. Each time you
 * press this button, it should change its color to a randomly
 * selected value. See ColorBoxes.java (later in this chapter)
 * for an example to how to generate a random color value.
 */
class RandomColorButton extends JButton {
    private static final Random rand = new Random(47);

    private static Color newColor() {
        return new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
    }

    public RandomColorButton(String text) {
        super(text);
        setBackground(newColor());
        setBorderPainted(false);
        setOpaque(true);
        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setBackground(newColor());
            }
        });
    }
}
public class E11_RandomColorButtonTest extends JFrame {
    public E11_RandomColorButtonTest() {
        add(new RandomColorButton("Random Color"));
    }

    public static void main(String[] args) {
        run(new E11_RandomColorButtonTest(), 200, 100);
    }
}