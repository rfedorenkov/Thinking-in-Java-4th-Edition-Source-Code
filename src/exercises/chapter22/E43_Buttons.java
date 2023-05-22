package exercises.chapter22;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;
import swt.util.SWTApplication;
import swt.util.SWTConsole;

/**
 * Exercise 43
 * Choose any one of the Swing examples that wasn't translated
 * in this section, and translate it to SWT.
 */
public class E43_Buttons implements SWTApplication {
    private Button button1, button2;
    private Label label, result;

    @Override
    public void createContents(Composite parent) {
        parent.setLayout(new GridLayout(1, true));
        label = new Label(parent, SWT.CENTER);
        label.setText("Press any button");
        label.setLayoutData(new GridData(SWT.CENTER, SWT.FILL, true, true));
        button1 = new Button(parent, SWT.PUSH);
        button1.setText("Button 1");
        button1.setLayoutData(new GridData(SWT.CENTER, SWT.FILL, true, true));
        button2 = new Button(parent, SWT.PUSH);
        button2.setText("Button 2");
        button2.setLayoutData(new GridData(SWT.CENTER, SWT.FILL, true, true));
        result = new Label(parent, SWT.BOTTOM);
        result.setLayoutData(new GridData(SWT.CENTER, SWT.FILL, true, true));
        result.setText("Press any button");
        button1.addListener(SWT.MouseDown, listener);
        button2.addListener(SWT.MouseDown, listener);
    }

    private Listener listener = new Listener() {
        @Override
        public void handleEvent(Event e) {
            Button button = (Button) e.widget;
            result.setText("You pressed: " + button.getText());
            result.getParent().layout();
        }
    };

    public static void main(String[] args) {
        SWTConsole.run(new E43_Buttons(), 200, 150);
    }
}