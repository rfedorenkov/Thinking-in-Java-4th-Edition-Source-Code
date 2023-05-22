package exercises.chapter22;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import static net.mindview.util.SwingConsole.run;

/**
 * Exercise 28
 * Create a dice class (just a class, without a GUI). Create five
 * dice and throw them repeatedly. Draw the curve showing the sum
 * of the dots from each throw, and show the curve evolving
 * dynamically as you throw more and more times.
 */
class Dice {
    static Random rand = new Random();

    int throwDice() {
        return rand.nextInt(6) + 1;
    }
}

class Curve extends JPanel {
    private static final int MAX_DATA_POINTS = 100;
    private int[] data = new int[MAX_DATA_POINTS];
    private int offset;
    private int[] pts = new int[MAX_DATA_POINTS];

    public void addData(int sumOfDots) {
        if (offset == MAX_DATA_POINTS) {
            offset = MAX_DATA_POINTS / 2;
            System.arraycopy(data, offset, data, 0, offset);
        }
        data[offset++] = sumOfDots;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED);
        int maxWidth = getWidth();
        double hstep = (double) maxWidth / (double) MAX_DATA_POINTS;
        int maxHeight = getHeight();
        for (int i = 0; i < offset; i++)
            pts[i] = (data[i] - 6) * maxHeight / 24;
        for (int i = 1; i < offset; i++) {
            int x1 = (int) ((i - 1) * hstep);
            int x2 = (int) (i * hstep);
            int y1 = pts[i - 1];
            int y2 = pts[i];
            g.drawLine(x1, y1, x2, y1);
            g.drawLine(x2, y1, x2, y2);
        }
    }
}

class DiceTossing extends JFrame {
    private final Curve curve = new Curve();
    private final Dice[] dices = {new Dice(), new Dice(),
            new Dice(), new Dice(), new Dice()};
    private Timer timer = new Timer(100, new ActionListener() {
        int sumOfDots;

        @Override
        public void actionPerformed(ActionEvent e) {
            sumOfDots = 0;
            for (Dice dice : dices)
                sumOfDots += dice.throwDice();
            curve.addData(sumOfDots);
        }
    });

    public DiceTossing() {
        add(curve);
        timer.setInitialDelay(1000);
        timer.start();
    }
}

public class E28_DiceTossing {
    public static void main(String[] args) {
        run(new DiceTossing(), 500, 300);
    }
}