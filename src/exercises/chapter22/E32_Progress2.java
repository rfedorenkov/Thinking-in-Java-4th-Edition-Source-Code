package exercises.chapter22;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

import static net.mindview.util.SwingConsole.run;

/**
 * Exercise 32
 * Modify Progress.java so that it does not share models, but
 * instead uses a listener to connect the slider and progress bar.
 */
public class E32_Progress2 extends JFrame {
    private JProgressBar pb = new JProgressBar();
    private JSlider sb = new JSlider(JSlider.HORIZONTAL, 0, 100, 60);

    public E32_Progress2() {
        setLayout(new GridLayout(2, 1));
        add(pb);
        sb.setValue(0);
        sb.setPaintTicks(true);
        sb.setMajorTickSpacing(20);
        sb.setMinorTickSpacing(5);
        sb.setBorder(new TitledBorder("Slide Me"));
        add(sb);
        sb.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                pb.setValue(sb.getValue());
            }
        });
    }

    public static void main(String[] args) {
        run(new E32_Progress2(), 300, 200);
    }
}