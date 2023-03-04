package exercises.chapter18;

import java.io.IOException;
import java.util.*;

import static net.mindview.util.Print.print;

/**
 * Exercise 10
 * Modify Exercise 8 to take additional command-line
 * arguments of words to find in the file. Print
 * all lines in which any of the words match.
 * {Args: E10_FindWords.java class string string}
 */
public class E10_FindWords {
    public static void main(String[] args) throws IOException {
        if (args.length < 2) {
            System.err.println("Usage: E10_FindWords file words...");
            System.exit(1);
        }

        List<String> list = E07_ReverseReadLines.read(args[0]);
        ListIterator<String> it = list.listIterator(list.size());
        while (it.hasPrevious()) {
            String s = it.previous();
            for (int i = 1; i < args.length; i++)
                if (s.contains(args[i]))
                    print(s);
        }
    }
}
/* (Execute to see output) */