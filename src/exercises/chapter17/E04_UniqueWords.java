package exercises.chapter17;

import net.mindview.util.TextFile;

import java.util.Set;
import java.util.TreeSet;

/**
 * Exercise 4
 * Create a Collection initializer that opens a file and breaks
 * it into words using TextFile, and then uses the words as the
 * source of data for the resulting Collection. Demonstrate that
 * it works.
 */
public class E04_UniqueWords {
    public static void main(String[] args) {
        Set<String> words = new TreeSet<>(
                new TextFile("src/exercises/chapter17/E04_UniqueWords.java", "\\W+"));
        System.out.println(words);
    }
}
/* Output:
[4, Collection, Create, Demonstrate, E04_UniqueWords, Exercise, Set, String, System, TextFile, TreeSet, W, a, and, args, as, breaks, chapter17, class, data, exercises, file, for, import, initializer, into, it, java, main, mindview, net, new, of, opens, out, package, println, public, resulting, source, src, static, that, the, then, uses, using, util, void, words, works]
 */