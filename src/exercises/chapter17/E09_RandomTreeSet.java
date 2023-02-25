package exercises.chapter17;

import net.mindview.util.CollectionData;
import net.mindview.util.RandomGenerator;

import java.util.Set;
import java.util.TreeSet;

import static net.mindview.util.Print.print;

/**
 * Exercise 9
 * Use RandomGenerator.String to fill a TreeSet, but use
 * alphabetic ordering. Print the TreeSet to verify the sort order.
 */
public class E09_RandomTreeSet {
    public static void main(String[] args) {
        Set<String> set = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
        set.addAll(CollectionData.list(new RandomGenerator.String(), 10));
        print(set);
    }
}
/* Output:
[ahKcxrE, GcFOWZn, GZMmJMR, naMesbt, oEsuEcU, OneOEdL, qUCBbkI, smwHLGE, TcQrGse, YNzbrny]
 */