package exercises.chapter13;

import net.mindview.util.TextFile;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Exercise 19
 * Build on Exercise 17 and 18 to write a program
 * that examines Java source code and produces all
 * class names used in a particular program.
 * {Args: src/exercises/chapter13/E19_ClassNameExtractor.java}
 */
public class E19_ClassNameExtractor {
    public static String regex = "class \\w+\\s+";

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: java E19_ClassNameExtractor file");
            System.exit(0);
        }

        Pattern p = Pattern.compile(regex);
        Pattern q = Pattern.compile(E17_CommentExtractor.regex);
        // Iterate through the lines of the input file:
        int index = 0;
        Matcher m = p.matcher("");
        Matcher n = q.matcher("");
        for (String line : new TextFile(args[0])) {
            m.reset(line);
            n.reset(line);
            while (m.find() && !n.find())
                System.out.println(index++ + ": " + m.group() + ": " + m.start());
        }
    }
}
/* Output:
0: class E19_ClassNameExtractor : 7
 */