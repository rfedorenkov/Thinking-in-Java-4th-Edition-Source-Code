package swt;

import net.mindview.util.TextFile;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.*;
import swt.util.SWTApplication;
import swt.util.SWTConsole;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/**
 * Fun with menus.
 */
public class Menus implements SWTApplication {
    private static Shell shell;

    @Override
    public void createContents(Composite parent) {
        shell = parent.getShell();
        Menu bar = new Menu(shell, SWT.BAR);
        shell.setMenuBar(bar);
        Set<String> words = new TreeSet<>(
                new TextFile("src/swt/Menus.java", "\\W+"));
        Iterator<String> it = words.iterator();
        while (it.next().matches("[0-9]+"))
            ; // Move past the numbers.
        MenuItem[] mItem = new MenuItem[7];
        for (int i = 0; i < mItem.length; i++) {
            mItem[i] = new MenuItem(bar, SWT.CASCADE);
            mItem[i].setText(it.next());
            Menu submenu = new Menu(shell, SWT.DROP_DOWN);
            mItem[i].setMenu(submenu);
        }
        int i = 0;
        while (it.hasNext()) {
            addItem(bar, it, mItem[i]);
            i = (i + 1) % mItem.length;
        }
    }

    static Listener listener = new Listener() {
        @Override
        public void handleEvent(Event e) {
            System.out.println(e);
        }
    };

    void addItem(Menu bar, Iterator<String> it, MenuItem mItem) {
        MenuItem item = new MenuItem(mItem.getMenu(), SWT.PUSH);
        item.addListener(SWT.Selection, listener);
        item.setText(it.next());
    }

    public static void main(String[] args) {
        SWTConsole.run(new Menus(), 600, 200);
    }
}