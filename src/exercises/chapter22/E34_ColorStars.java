package exercises.chapter22;

import javax.swing.*;
import java.awt.*;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static net.mindview.util.SwingConsole.run;

/**
 * Exercise 34
 * Modify ColorBoxes.java so that it begins by
 * sprinkling points ("stars") across the canvas,
 * then randomly changes the colors of those "stars".
 */
class Star extends JPanel implements Runnable {
    private final int num_points;
    private final int pause;
    static Random rand = new Random();
    private Color color = new Color(0);
    private final int[] x;
    private final int[] y;
    private final double[] dx;
    private final double[] dy;

    public Star(int num_points, int pause) {
        this.num_points = num_points;
        this.pause = pause;
        x = new int[num_points * 2];
        y = new int[num_points * 2];
        dx = new double[num_points * 2];
        dy = new double[num_points * 2];
        double delta = Math.PI / num_points;
        for (int i = 0; i < num_points * 2; i++) {
            dx[i] = Math.cos(delta * i);
            dy[i] = Math.sin(delta * i);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        int width = getWidth();
        int height = getHeight();
        int centreX = width / 2;
        int centreY = height / 2;
        int outerRing, innerRing;
        if (width < height) {
            innerRing = width / 8;
            outerRing = width / 2;
        } else {
            innerRing = height / 8;
            outerRing = height / 2;
        }
        int d;
        for (int i = 0; i < num_points * 2; i++) {
            d = (i % 2 == 0) ? innerRing : outerRing;
            x[i] = centreX + (int) (d * dx[i]);
            y[i] = centreY + (int) (d * dy[i]);
        }
        g.setColor(color);
        g.fillPolygon(x, y, num_points * 2);
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                color = new Color(rand.nextInt(0xFFFFFF));
                repaint(); // Asynchronously request a paint();
                TimeUnit.MILLISECONDS.sleep(pause);
            }
        } catch (InterruptedException e) {
            // Accaptable way to exit
        }
    }
}

public class E34_ColorStars extends JFrame {
    private int grid = 12;
    private int pause = 50;
    private static ExecutorService exec = Executors.newCachedThreadPool();

    public void setUp() {
        setLayout(new GridLayout(grid, grid));
        for (int i = 0; i < grid * grid; i++) {
            Star cb = new Star(Star.rand.nextInt(12) + 3, pause);
            add(cb);
            exec.execute(cb);
        }
    }

    public static void main(String[] args) {
        E34_ColorStars stars = new E34_ColorStars();
        if (args.length > 0)
            stars.grid = new Integer(args[0]);
        if (args.length > 1)
            stars.pause = new Integer(args[1]);
        stars.setUp();
        run(stars, 500, 400);
    }
}