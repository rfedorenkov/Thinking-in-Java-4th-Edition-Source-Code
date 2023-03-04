package exercises.chapter18;

import net.mindview.util.ProcessFiles;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

/**
 * Exercise 6
 * Use ProcessFiles to find all the Java source-code files
 * in a particular directory subtree that have been modified
 * after a particular date.
 * {Args: . 2/26/23}
 */
public class E06_ProcessFiles3 {
    public static void main(String[] args) throws ParseException {
        if (args.length != 2) {
            System.err.println("Usage:E06_ProcessFiles3 path date");
            System.exit(1);
        }
        DateFormat format = DateFormat.getDateInstance(DateFormat.SHORT, Locale.US);
        format.setLenient(true);
        Date parse = format.parse(args[1]);
        new ProcessFiles(new ProcessFiles.Strategy() {
            @Override
            public void process(File file) {
                Date d = new Date(file.lastModified());
                if (d.after(parse)) {
                    System.out.println(file);
                }
            }
        }, "java").start(new String[] { args[0] });
    }
}
/* (Execute to see output) */