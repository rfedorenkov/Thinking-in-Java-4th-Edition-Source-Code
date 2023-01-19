package exercises.chapter11;

import java.util.*;

/**
 * Exercise 12
 * Create and populate a List<Integer>. Create a second List<Integer>
 * of the same size as the first, and use ListIterator to read elements
 * of the first List and insert them into the second in reverse order.
 * (You may want to explore a number of different ways to solve this problem).
 */
public class E12_ListIterators {
    public static void main(String[] args) {
        List<Integer> list1 = new ArrayList<>(Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));
        List<Integer> list2 = new ArrayList<>(list1.size());
        System.out.println("source: " + list1);
        System.out.println("destination: " + list2);
        ListIterator<Integer> it1 = list1.listIterator(list1.size());
        ListIterator<Integer> it2 = list2.listIterator();
        while (it1.hasPrevious()) {
            it2.add(it1.previous());
        }
        System.out.println("source: " + list1);
        System.out.println("destination: " + list2);
    }
}
/* Output:
source: [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
destination: []
source: [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
destination: [9, 8, 7, 6, 5, 4, 3, 2, 1, 0]
 */