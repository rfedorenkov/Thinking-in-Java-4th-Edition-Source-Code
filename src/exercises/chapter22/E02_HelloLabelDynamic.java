package exercises.chapter22;

import javax.swing.*;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Exercise 2
 * Modify HelloLabel.java to show that label addition is
 * dynamic, by adding a random number of labels.
 */
public class E02_HelloLabelDynamic extends JFrame {
    JLabel label;

    public E02_HelloLabelDynamic() {
        super("Hello Swing");
        label = new JLabel("A Fruit");
        add(label);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300, 100);
        setVisible(true);
    }

    static E02_HelloLabelDynamic hld;
    static Random rand = new Random(47);
    private static String[] fruits = {
            "Apple", "Orange", "Banana", "Pineapple", "Watermelon", "Cherry", "Coconut"};

    public static void main(String[] args) throws Exception {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                hld = new E02_HelloLabelDynamic();
            }
        });
        while (!Thread.interrupted()) {
            TimeUnit.SECONDS.sleep(1);
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    hld.label.setText("A Fruit: " + fruits[rand.nextInt(fruits.length)]);
                }
            });
        }
    }
}