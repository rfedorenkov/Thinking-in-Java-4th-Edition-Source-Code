package exercises.chapter22;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

import static net.mindview.util.SwingConsole.run;

/**
 * Exercise 9
 * Starting with ShowAddListeners.java, create a program
 * with the full functionality of typeinfo.ShowMethods.java.
 */
class ShowMethods extends JFrame {
    private JTextField
            name = new JTextField(25),
            search = new JTextField(25);
    private JTextArea results = new JTextArea(40, 65);
    private JScrollPane scrollPane = new JScrollPane(results);
    private static Pattern qualifier = Pattern.compile("\\w+\\.");
    private String[] arr;

    class NameL implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String nm = name.getText().trim();
            if (nm.length() == 0) {
                results.setText("No match");
                return;
            }
            Class<?> kind;
            try {
                kind = Class.forName(nm);
            } catch (ClassNotFoundException ex) {
                results.setText("No match");
                return;
            }
            int lines = 0;
            Method[] methods = kind.getMethods();
            Constructor<?>[] ctors = kind.getConstructors();
            arr = new String[methods.length + ctors.length];
            for (int i = 0; i < methods.length; i++)
                arr[i] = methods[i].toString();
            for (int i = 0; i < ctors.length; i++)
                arr[i + methods.length] = ctors[i].toString();
            display();
        }
    }

    void display() {
        results.setText("");
        if (search.getText().trim().length() == 0)
            for (String s : arr)
                results.append(qualifier.matcher(s).replaceAll("") + "\n");
        else {
            List<String> lookFor = Arrays.asList(search.getText().split("\\s"));
            for (String s : arr) {
                Iterator<String> it = lookFor.iterator();
                boolean include = true;
                while (it.hasNext())
                    if (!s.contains(it.next()))
                        include = false;
                if (include)
                    results.append(qualifier.matcher(s).replaceAll("") + "\n");
            }
        }
        scrollPane.getViewport().setViewPosition(new Point(0, 0));
    }

    public ShowMethods() {
        name.addActionListener(new NameL());
        search.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                display();
            }
        });
        JPanel top1 = new JPanel();
        top1.add(new JLabel("Qualified.class.name (press Enter):"));
        top1.add(name);
        JPanel top2 = new JPanel();
        top2.add(new JLabel("Words to search for (optional):"));
        top2.add(search);
        JPanel top = new JPanel(new GridLayout(2, 1));
        top.add(top1);
        top.add(top2);
        add(BorderLayout.NORTH, top);
        add(scrollPane);
    }
}

public class E09_ShowMethodsTest {
    public static void main(String[] args) {
        run(new ShowMethods(), 550, 400);
    }
}