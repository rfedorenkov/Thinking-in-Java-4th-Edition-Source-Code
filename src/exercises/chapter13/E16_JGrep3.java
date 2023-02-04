package exercises.chapter13;

import net.mindview.util.TextFile;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Exercise 16
 * Modify JGrep.java to accept a directory name or a file name
 * as argument (if a directory is provided, search should include
 * all files in the directory). Hint: you can generate a list of
 * filenames with File[] files = new File(".").listFiles();
 * {Args: src/exercises/chapter13/E16_JGrep3.java java}
 */
public class E16_JGrep3 {
    public static void main(String[] args) throws Exception {
        if (args.length < 2) {
            System.out.println("Usage: java E16_JGrep3 file regex");
            System.exit(0);
        }
        Pattern p = Pattern.compile(args[1]);
        // Iterate through the lines of the input file:
        int index = 0;
        Matcher m = p.matcher("");
        File file = new File(args[0]);
        if (file.exists()) {
            File[] files;
            if (file.isDirectory())
                files = file.listFiles();
            else
                files = new File[]{file};

            for (File f : files) {
                System.out.println("File: " + f);
                for (String line : new TextFile(f.toString())) {
                    m.reset(line);
                    while (m.find())
                        System.out.println(index++ + ": " + m.group() + ": " + m.start());
                }
            }
        } else {
            throw new IllegalArgumentException("Directory or file not found: " + file);
        }
    }
}
/* Output:
File: src/exercises/chapter13/E16_JGrep3.java
0: java: 7
1: java: 7
2: java: 7
3: java: 16
4: java: 45
5: java: 50
6: java: 39
 */