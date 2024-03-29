package exercises.chapter18;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Exercise 21
 * Write a program that takes standard input and capitalizes
 * all characters, then puts the results on standard output.
 * Redirect the contents of a file into this program (the process
 * of redirection will very depending on your operating system).
 * {RunByHand}
 */
public class E21_Echo2 {
    public static void main(String[] args) throws IOException {
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));
        String s;
        while ((s = stdin.readLine()) != null && s.length() != 0)
            System.out.println(s.toUpperCase());
        // An empty line or Ctrl-Z terminates the program
    }
}
/* (Execute to see output) */