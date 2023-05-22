package exercises.chapter22;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import swt.util.SWTApplication;
import swt.util.SWTConsole;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Exercise 40
 * {Requires: org.eclipse.swt.widgets.Display; You must
 * install the SWT library from http://www.eclipse.org }
 *
 * Modify DisplayProperties.java so that it uses SWTConsole.
 */
public class E40_DisplayProperties2 implements SWTApplication {
    @Override
    public void createContents(Composite parent) {
        parent.setLayout(new FillLayout());
        Text text = new Text(parent, SWT.WRAP | SWT.V_SCROLL);
        StringWriter props = new StringWriter();
        System.getProperties().list(new PrintWriter(props));
        text.setText(props.toString());
    }

    public static void main(String[] args) {
        SWTConsole.run(new E40_DisplayProperties2(), 400, 300);
    }
}