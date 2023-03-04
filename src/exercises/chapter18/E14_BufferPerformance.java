package exercises.chapter18;

import java.io.*;
import java.util.List;

/**
 * Exercise 14
 * Starting with BasicFileOutput.java, write a program
 * that compares the performance of writing to a file
 * when using buffered and unbuffered I/O.
 */
public class E14_BufferPerformance {
    static String file = "src/exercises/chapter18/E14_BufferPerformance.out";

    public static void main(String[] args) throws IOException {
        List<String> list = E07_ReverseReadLines.read("src/exercises/chapter18/E14_BufferPerformance.java");
        PrintWriter out = new PrintWriter(
                new BufferedWriter(new FileWriter(file)));
        int lineCount = 1;
        long start = System.nanoTime();
        for (String s : list) {
            out.println(lineCount++ + ": " + s);
        }
        long end = System.nanoTime();
        out.close();
        System.out.println("Buffered: " + (end - start) + " ns");

        out = new PrintWriter(file);
        lineCount = 1;
        start = System.nanoTime();
        for (String s : list) {
            out.println(lineCount++ + ": " + s);
        }
        end = System.nanoTime();
        out.close();
        System.out.println("Unbuffered: " + (end - start) + " ns");
    }
}
/* Output:
Buffered: 13015792 ns
Unbuffered: 258792 ns
 */