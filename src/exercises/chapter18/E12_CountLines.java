package exercises.chapter18;

import io.BufferedInputFile;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.ListIterator;

/**
 * Exercise 12
 * Modify Exercise 8 to also open a text file so you can
 * write text into it. Write the lines in the LinkedList,
 * along with line numbers (do not attempt to use the
 * "LineNumber" classes), out to the file.
 */
public class E12_CountLines {
    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            System.err.println("Usage: E12_LineNumber loadFile saveFile");
            System.exit(1);
        }

        List<String> list = E07_ReverseReadLines.read(args[0]);
        ListIterator<String> it = list.listIterator(list.size());
        int lineCount = list.size();
        PrintWriter out = new PrintWriter(args[1]);
        while (it.hasPrevious()) {
            out.println(lineCount-- + ": " + it.previous());
        }
        out.close();
        System.out.println(BufferedInputFile.read(args[1]));
    }
}
/* (Execute to see output:) */