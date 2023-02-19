package exercises.chapter16;

import net.mindview.util.Generated;
import net.mindview.util.RandomGenerator;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 * Exercise 23
 * Create an array of Integer, fill it with random int values
 * (using autoboxing), and sort it into reverse order using a Comparator.
 */
public class E23_ArraySortReverseOrder {
    public static void main(String[] args) {
        Integer[] array = Generated.array(new Integer[10], new RandomGenerator.Integer(1000));
        System.out.println("before sort: " + Arrays.toString(array));
        Arrays.sort(array, Comparator.reverseOrder());
        System.out.println("after sort: " + Arrays.toString(array));
    }
}
/* Output:
before sort: [258, 555, 693, 861, 961, 429, 868, 200, 522, 207]
after sort: [961, 868, 861, 693, 555, 522, 429, 258, 207, 200]
 */