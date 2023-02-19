package exercises.chapter16;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

/**
 * Exercise 24
 * Show that the class from Exercise 19 can be searched.
 */
public class E24_ArraySearch {
    public static void main(String[] args) {
        Random rand = new Random(47);
        WithoutEquals[] a1 = new WithoutEquals[10];
        for (int i = 0; i < a1.length; i++) {
            a1[i] = new WithoutEquals(rand.nextInt(100));
        }
        Comparator<WithoutEquals> comparator1 = new Comparator<>() {
            @Override
            public int compare(WithoutEquals o1, WithoutEquals o2) {
                return (o1.i < o2.i ? -1 : (o1.i == o2.i ? 0 : 1));
            }
        };
        Arrays.sort(a1, comparator1);
        int index = Arrays.binarySearch(a1, a1[3], comparator1);
        System.out.println("Found: WithoutEquals - Index: " + index + " = " + a1[index]);

        WithEquals[] a2 = new WithEquals[10];
        for (int i = 0; i < a2.length; i++) {
            a2[i] = new WithEquals(rand.nextInt(100));
        }
        Comparator<WithEquals> comparator2 = new Comparator<>() {
            @Override
            public int compare(WithEquals o1, WithEquals o2) {
                return (o1.i < o2.i ? -1 : (o1.i == o2.i ? 0 : 1));
            }
        };
        Arrays.sort(a2, comparator2);
        index = Arrays.binarySearch(a2, a2[2], comparator2);
        System.out.println("Found: WithEquals - Index: " + index + " = " + a2[index]);
    }
}
/* Output:
Found: WithoutEquals - Index: 3 = 29
Found: WithEquals - Index: 2 = 28
 */