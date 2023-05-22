package swt;

import org.eclipse.swt.widgets.*;

/**
 * {Requires" org.eclipse.swt.widgets.Diplay; You must
 * install the SWT library from http://www.eclipse.org }
 */
public class HelloSWT {
    public static void main(String[] args) {
        Display display = new Display();
        Shell shell = new Shell(display);
        shell.setText("Hi there, SWT!"); // Title bar
        shell.open();
        while (!shell.isDisposed())
            if (!display.readAndDispatch())
                display.sleep();
        display.dispose();
    }
}