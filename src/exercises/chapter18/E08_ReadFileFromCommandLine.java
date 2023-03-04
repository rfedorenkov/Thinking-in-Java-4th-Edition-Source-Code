package exercises.chapter18;

import java.io.IOException;
import java.util.List;
import java.util.ListIterator;

import static net.mindview.util.Print.print;

/**
 * Exercise 8
 * Modify Exercise 7 so that the name of the file
 * you read is provided as a command-line argument.
 * {Args: E08_ReadFileFromCommandLine.java}
 */
public class E08_ReadFileFromCommandLine {
    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            System.err.println("Usage: E08_ReadFileFromCommandLine file");
            System.exit(1);
        }

        List<String> list = E07_ReverseReadLines.read(args[0]);
        ListIterator<String> it = list.listIterator(list.size());
        while (it.hasPrevious())
            print(it.previous());
    }
}
/* (Execute to see output) */