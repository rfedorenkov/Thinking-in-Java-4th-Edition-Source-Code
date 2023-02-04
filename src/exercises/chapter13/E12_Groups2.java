package exercises.chapter13;

import strings.Groups;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static net.mindview.util.Print.print;

/**
 * Exercise 12
 * Modify Groups.java to count all unique words
 * with no initial capital letter.
 */
public class E12_Groups2 {
    public static void main(String[] args) {
        Set<String> words = new HashSet<>();
        Matcher m = Pattern
                .compile("\\b[a-z]+\\b")
                .matcher(Groups.POEM);
        while (m.find())
            words.add(m.group());
        print(words);
        print("Number of unique words: " + words.size());
    }
}
/* Output:
[raths, mimsy, mome, gimble, that, claws, brillig, son, gyre, and, borogoves, bite, bird,
catch, shun, toves, jaws, in, outgrabe, my, the, slithy, were, wabe, frumious]
Number of unique words: 25
 */