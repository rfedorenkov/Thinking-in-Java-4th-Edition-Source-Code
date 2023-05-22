package exercises.chapter22;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static net.mindview.util.SwingConsole.run;

/**
 * Exercise 27
 * Modify Exercise 25 so that the javax.swing.Timer class
 * is used to drive the animation. Note the difference
 * between this and java.util.Timer.
 */
class SineDraw_T extends JPanel {
    private static final int SCALEFACTOR = 300;
    private int cycles;
    private int points;
    private double[] sines;
    private int[] pts;
    private double offset;

    private JSlider speed = new JSlider(0, 15, 5);
    private Timer timer = new Timer(500, new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            offset += 0.25;
            synchronized (SineDraw_T.this) {
                for (int i = 0; i < points; i++) {
                    double radians = (Math.PI / SCALEFACTOR) * i + offset;
                    sines[i] = Math.sin(radians);
                }
            }
            repaint();
        }
    });

    public SineDraw_T() {
        super(new BorderLayout());
        setCycles(5);
        speed.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                timer.setDelay(speed.getValue() * 100);
            }
        });
        add(BorderLayout.SOUTH, speed);
        speed.setInverted(true);
        timer.setInitialDelay(1000);
        timer.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int maxWidth = getWidth();
        double hstep = (double) maxWidth / (double) points;
        int maxHeight = getHeight();
        for (int i = 0; i < points; i++)
            pts[i] = (int) (sines[i] * maxHeight / 2 * .94 + maxHeight / 2 * .96);
        g.setColor(Color.RED);
        for (int i = 1; i < points; i++) {
            int x1 = (int) ((i - 1) * hstep);
            int x2 = (int) (i * hstep);
            int y1 = pts[i - 1];
            int y2 = pts[i];
            g.drawLine(x1, y1, x2, y2);
        }
    }

    public void setCycles(int newCycles) {
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

class SineWave_T extends JFrame {
    private SineDraw_T sines = new SineDraw_T();
    private JSlider adjustCycles = new JSlider(1, 30, 5);

    public SineWave_T() {
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

public class E27_TimerAnimation {
    public static void main(String[] args) {
        run(new SineWave_T(), 700, 400);
    }
}