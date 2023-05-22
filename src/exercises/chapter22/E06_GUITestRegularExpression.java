package exercises.chapter22;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static net.mindview.util.SwingConsole.run;

/**
 * Exercise 6
 * Turn strings/TestRegularExpression.java into an
 * interactive Swing program that allows you to put an
 * input string in one JTextArea and a regular expressions in
 * a JTextField. The results should be displayed in a second
 */
public class E06_GUITestRegularExpression extends JFrame {
    private JTextField regex = new JTextField(30);
    private JTextArea
            input = new JTextArea(3, 50),
            output = new JTextArea(10, 50);
    private JButton match = new JButton("Match");
    private JLabel
            inputLabel = new JLabel("Input:"),
            outputLabel = new JLabel("Output:"),
            regexLabel = new JLabel("RegEx");

    private JPanel
            panel1 = new JPanel(),
            panel2 = new JPanel(),
            panel3 = new JPanel();

    public E06_GUITestRegularExpression() {
        setLayout(new GridLayout(3, 1));
        panel1.add(inputLabel);
        panel1.add(new JScrollPane(input));
        add(panel1);
        panel2.add(regexLabel);
        panel2.add(regex);
        panel2.add(match);
        add(panel2);
        panel3.add(outputLabel);
        panel3.add(new JScrollPane(output));
        add(panel3);

        match.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                output.setText("");
                output.append("Regular expression: \"" + regex.getText() + "\"");
                Pattern p = Pattern.compile(regex.getText());
                Matcher m = p.matcher(input.getText());
                while (m.find())
                    output.append("Match \"" + m.group() + "\" at positions " +
                            m.start() + "-" + (m.end() - 1) + "\n");
            }
        });
    }


    public static void main(String[] args) {
        run(new E06_GUITestRegularExpression(), 700, 700);
    }
}