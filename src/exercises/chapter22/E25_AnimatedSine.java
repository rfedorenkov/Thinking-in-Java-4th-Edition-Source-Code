package exercises.chapter22;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

import static net.mindview.util.SwingConsole.run;

/**
 * Exercise 25
 * Starting with SineWave.java, create a program
 * (an application using the SwingConsole class)
 * that draws an animated sine wave that appears to
 * scroll past the viewing window like an oscilloscope,
 * driving the animation with a java.util.Timer. The
 * speed of the animation should be controlled with
 * a javax.swing.JSlider control.
 */
class ExtSineDraw extends JPanel {
    private static final int SCALEFACTOR = 300;
    private int cycles;
    private int points;
    private double[] sines;
    private int[] pts;
    private Timer timer = new Timer();
    private double offset;

    private class MyTimerTask extends TimerTask {
        @Override
        public void run() {
            offset += 0.25;
            synchronized (ExtSineDraw.this) {
                for (int i = 0; i < points; i++) {
                    double radians = (Math.PI / SCALEFACTOR) * i + offset;
                    sines[i] = Math.sin(radians);
                }
            }
            repaint();
        }
    }

    private JSlider speed = new JSlider(15, 115, 65);

    public ExtSineDraw() {
        super(new BorderLayout());
        setCycles(5);
        speed.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                timer.cancel();
                timer = new Timer();
                timer.scheduleAtFixedRate(
                        new MyTimerTask(), 0, speed.getValue());
            }
        });
        add(BorderLayout.SOUTH, speed);
        speed.setInverted(true);
        timer.scheduleAtFixedRate(new MyTimerTask(), 1000, 65);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int maxWidth = getWidth();
        double hstep = (double) maxWidth / (double) points;
        int maxHeight = getHeight();
        synchronized (this) {
            for (int i = 0; i < points; i++)
                pts[i] = (int) (sines[i] * maxHeight / 2 * .89 + maxHeight / 2 * .91);
        }
        g.setColor(Color.RED);
        for (int i = 1; i < points; i++) {
            int x1 = (int) ((i - 1) * hstep);
            int x2 = (int) (i * hstep);
            int y1 = pts[i - 1];
            int y2 = pts[i];
            g.drawLine(x1, y1, x2, y2);
        }
    }

    public synchronized void setCycles(int newCycles) {
        cycles = newCycles;
        points = SCALEFACTOR * cycles * 2;
        sines = new double[points];
        pts = new int[points];
        for (int i = 0; i < points; i++) {
            double radians = (Math.PI / SCALEFACTOR) * i;
            sines[i] = Math.sin(radians);
        }
        repaint();
    }
}

class ExtSineWave extends JFrame {
    private ExtSineDraw sines = new ExtSineDraw();
    private JSlider adjustCycles = new JSlider(1, 30, 5);

    public ExtSineWave() {
        add(sines);
        adjustCycles.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                sines.setCycles(((JSlider) e.getSource()).getValue());
            }
        });
        add(BorderLayout.SOUTH, adjustCycles);
    }
}

public class E25_AnimatedSine {
    public static void main(String[] args) {
        run(new ExtSineWave(), 700, 400);
    }
}