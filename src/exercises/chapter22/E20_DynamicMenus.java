package exercises.chapter22;

import net.mindview.util.TextFile;

import javax.swing.*;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

import static net.mindview.util.SwingConsole.run;

/**
 * Exercise 20
 * Create a program that breaks a text file into words.
 * Distribute those words as labels on menus and submenus.
 */
class DynamicMenus extends JFrame {
    private Set<String> words = new TreeSet<>(
            new TextFile("src/exercises/chapter22/E20_DynamicMenus.java", "\\W+"));
    private JMenuBar mb = new JMenuBar();
    private JMenu
            tm1 = new JMenu("Words Starting With UCC"),
            tm2 = new JMenu("Words Starting With LCC");

    DynamicMenus() {
        distributeWordsOnMenus();
        mb.add(tm1);
        mb.add(tm2);
        setJMenuBar(mb);
    }

    private void distributeWordsOnMenus() {
        boolean firstInGroup;
        char currentGroup, lastGroup = (char) 0;
        JMenu currentMenu;
        JMenu currentSM = null;
        String word, nextWord = null;
        boolean callNext = true;
        for (Iterator<String> it = words.iterator(); it.hasNext();) {
            if (callNext)
                word = it.next();
            else {
                word = nextWord;
                callNext = true;
            }
            if (word.matches("[0-9]+"))
                continue;
            currentGroup = word.charAt(0);
            firstInGroup = currentGroup != lastGroup;
            lastGroup = currentGroup;
            if (Character.isLowerCase(currentGroup))
                currentMenu = tm2;
            else if (Character.isUpperCase(currentGroup))
                currentMenu = tm1;
            else
                continue;
            JMenuItem itemToAdd = new JMenuItem(word);
            if (firstInGroup) {
                if (!it.hasNext()) {
                    currentMenu.add(itemToAdd);
                    break;
                }
                nextWord = it.next();
                callNext = false;
                if (currentGroup != nextWord.charAt(0))
                    currentMenu.add(itemToAdd);
                else {
                    currentSM = new JMenu(Character.toString(currentGroup));
                    currentSM.add(itemToAdd);
                    currentMenu.add(currentSM);
                }
            } else
                currentSM.add(itemToAdd);
        }
    }
}

public class E20_DynamicMenus {
    public static void main(String[] args) {
        run(new DynamicMenus(), 500, 400);
    }
}