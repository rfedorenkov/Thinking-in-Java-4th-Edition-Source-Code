package exercises.chapter18;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import static net.mindview.util.Print.print;

/**
 * Exercise 7
 * Open a text file so that you can read the file
 * one line at a time. Read each line as a String
 * and place that String object into a LinkedList.
 * Print all of the lines in the LinkedList in
 * reverse order.
 */
public class E07_ReverseReadLines {
    public static List<String> read(String filename) throws IOException {
        // Reading input by lines:
        BufferedReader in = new BufferedReader(new FileReader(filename));
        List<String> list = new LinkedList<>();
        String s;
        while ((s = in.readLine()) != null)
            list.add(s);
        in.close();
        return list;
    }

    public static void main(String[] args) throws IOException {
        String filename = "src/exercises/chapter18/E07_ReverseReadLines.java";
        List<String> list = read(filename);
        ListIterator<String> it = list.listIterator(list.size());
        while (it.hasPrevious())
            print(it.previous());
    }
}
/* (Execute to see output) */