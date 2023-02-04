package exercises.chapter13;

import net.mindview.util.TextFile;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Exercise 17
 * Write a program that reads a Java source-code file
 * (you provide the file name on the command line)
 * and displays all the comments.
 * {Args: src/exercises/chapter13/E17_CommentExtractor.java}
 */
public class E17_CommentExtractor {
    public static String regex = "(\\/\\/.+)|(^\\/\\*.+)|(\\*\\s.+)";

    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: java E17_CommentExtractor file");
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
0: /**: 0
1: * Exercise 17: 1
2: * Write a program that reads a Java source-code file: 1
3: * (you provide the file name on the command line): 1
4: * and displays all the comments.: 1
5: * {Args: src/exercises/chapter13/E17_CommentExtractor.java}: 1
6: // Iterate through the lines of the input file:: 8
 */