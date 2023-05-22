package gui;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.plaf.basic.BasicArrowButton;
import java.awt.*;

import static net.mindview.util.SwingConsole.run;

/**
 * Various Swing buttons.
 */
public class Buttons extends JFrame {
    private JButton jb = new JButton("JButton");
    private BasicArrowButton
            up = new BasicArrowButton(SwingConstants.NORTH),
            down = new BasicArrowButton(SwingConstants.SOUTH),
            right = new BasicArrowButton(SwingConstants.EAST),
            left = new BasicArrowButton(SwingConstants.WEST);

    public Buttons() {
        setLayout(new FlowLayout());
        add(jb);
        add(new JToggleButton("JToggleButton"));
        add(new JCheckBox("JCheckBox"));
        add(new JRadioButton("JRadioButton"));
        JPanel jp = new JPanel();
        jp.setBorder(new TitledBorder("Directions"));
        jp.add(up);
        jp.add(down);
        jp.add(left);
        jp.add(right);
        add(jp);
    }

    public static void main(String[] args) {
        run(new Buttons(), 350, 200);
    }
}