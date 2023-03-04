package exercises.chapter18;

import java.io.IOException;
import java.util.List;
import java.util.ListIterator;

/**
 * Exercise 9
 * Modify Exercise 8 to force all the lines in the LinkedList
 * to uppercase and send the results to System.out.
 * {Args: E09_UpperCase.java}
 */
public class E09_UpperCase {
    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            System.err.println("Usage: E09_UpperCase file");
            System.exit(1);
        }

        List<String> list = E07_ReverseReadLines.read(args[0]);
        ListIterator<String> it = list.listIterator(list.size());
        while (it.hasPrevious())
            System.out.println(it.previous().toUpperCase());
    }
}
/* (Execute to see output) */