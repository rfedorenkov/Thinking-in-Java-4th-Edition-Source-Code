package exercises.chapter13;

import net.mindview.util.TextFile;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Exercise 18
 * Write a program that reads a Java source-code file
 * and displays all the string literals in the code
 * (provide the file name on the command line).
 * {Args: src/exercises/chapter13/E18_StringExtractor.java}
 */
public class E18_StringExtractor {
    public static String regex = "\".+\"";
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: java E18_StringExtractor file");
            System.exit(0);
        }
        Pattern p = Pattern.compile(regex);
        // Iterate through the lines of the input file:
        int index = 0;
        Matcher m = p.matcher("");
        for (String line : new TextFile(args[0])) {
            m.reset(line);
            while (m.find())
                System.out.println(index++ + ": " + m.group() + ": " + m.start());
        }
    }
}
/* Output:
0: "\".+\"": 33
1: "Usage: java E18_StringExtractor file": 31
2: ": " + m.group() + ": ": 45
 */