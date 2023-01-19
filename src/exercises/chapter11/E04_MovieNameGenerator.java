package exercises.chapter11;

import java.util.*;

import static net.mindview.util.Print.print;

/**
 * Exercise 4
 * Create a generator class that produces String objects
 * with the names of characters from your favorite movie
 * each time you call next(), and then loops around to the
 * beginning of the character list when it runs out of names.
 * Use this generator to fill an array, an ArrayList, a LinkedList,
 * a HashSet, a LinkedHashSet, and a TreeSet, then print each container.
 */
class Generator {
    private static final String[] CHARACTERS = {
            "Snow Queen", "Doc", "Grumpy", "Happy", "Sleepy", "Bashful", "Sneezy", "Dopey"
    };
    private int index = 0;

    public String next() {
        return CHARACTERS[index++ % CHARACTERS.length];
    }
}

public class E04_MovieNameGenerator {
    private static final Generator gen = new Generator();

    public static Collection<String> fill(Collection<String> collection) {
        for (int i = 0; i < 5; i++)
            collection.add(gen.next());
        return collection;
    }

    public static String[] fill(String[] array) {
        for (int i = 0; i < array.length; i++)
            array[i] = gen.next();
        return array;
    }

    public static void main(String[] args) {
        print(Arrays.toString(fill(new String[5])));
        print(fill(new ArrayList<>()));
        print(fill(new LinkedList<>()));
        print(fill(new HashSet<>()));
        print(fill(new LinkedHashSet<>()));
        print(fill(new TreeSet<>()));
    }
}
/* Output:
[Snow Queen, Doc, Grumpy, Happy, Sleepy]
[Bashful, Sneezy, Dopey, Snow Queen, Doc]
[Grumpy, Happy, Sleepy, Bashful, Sneezy]
[Happy, Doc, Grumpy, Dopey, Snow Queen]
[Sleepy, Bashful, Sneezy, Dopey, Snow Queen]
[Bashful, Doc, Grumpy, Happy, Sleepy]
 */