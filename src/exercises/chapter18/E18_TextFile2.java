package exercises.chapter18;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;

/**
 * Exercise 18
 * Modify TextFile.java so that it passes
 * IOExceptions out to the caller.
 */
public class E18_TextFile2 extends ArrayList<String> {
    // Read a file as a single String:
    public static String read(String fileName) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader in = new BufferedReader(new FileReader(new File(fileName).getAbsoluteFile()));
        try {
            String s;
            while ((s = in.readLine()) != null) {
                sb.append(s);
                sb.append("\n");
            }
        } finally {
            in.close();
        }
        return sb.toString();
    }

    // Write a single file in one method call:
    public static void write(String fileName, String text) throws IOException {
        PrintWriter out = new PrintWriter(new File(fileName).getAbsoluteFile());
        try {
            out.print(text);
        } finally {
            out.close();
        }
    }

    // Read a file, split by any regular expression:
    public E18_TextFile2(String fileName, String splitter) throws IOException {
        super(Arrays.asList(read(fileName).split(splitter)));
        // Regular expression split() often leaves an empty
        // String at the first position:
        if (get(0).equals("")) remove(0);
    }

    // Normally read by lines:
    public E18_TextFile2(String fileName) throws IOException {
        this(fileName, "\n");
    }

    public void write(String fileName) throws IOException {
        PrintWriter out = new PrintWriter(new File(fileName).getAbsoluteFile());
        try {
            for (String item : this)
                out.println(item);
        } finally {
            out.close();
        }
    }

    // Simple test:
    public static void main(String[] args) throws IOException {
        String file = read("src/exercises/chapter18/E18_TextFile2.java");
        write("src/exercises/chapter18/E18_test.txt", file);
        E18_TextFile2 text = new E18_TextFile2("src/exercises/chapter18/E18_test.txt");
        text.write("src/exercises/chapter18/E18_test2.txt");
        // Break into unique sorted list of words:
        TreeSet<String> words = new TreeSet<>(
                new E18_TextFile2("src/exercises/chapter18/E18_TextFile2.java", "\\W+"));
        // Display the capitalized words:
        System.out.println(words.headSet("a"));
    }
}
/* (Execute to see output) */