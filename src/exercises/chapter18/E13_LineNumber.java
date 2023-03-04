package exercises.chapter18;

import io.BufferedInputFile;

import java.io.*;

/**
 * Exercise 13
 * Modify BasicFileOutput.java so that is uses LineNumberReader
 * to keep track of the line count. Note that it's much easier
 * to just keep track programmatically.
 */
public class E13_LineNumber {
    static String file = "src/exercises/chapter18/E13_LineNumber.out";

    public static void main(String[] args) throws IOException {
        LineNumberReader in = new LineNumberReader(
                new FileReader("src/exercises/chapter18/E13_LineNumber.java"));
        PrintWriter out = new PrintWriter(
                new BufferedWriter(new FileWriter(file)));
        String s;
        while ((s = in.readLine()) != null)
            out.println(in.getLineNumber() + ": " + s);
        out.close();
        // Show the stored file:
        System.out.println(BufferedInputFile.read(file));
    }
}
/* (Execute to see output) */