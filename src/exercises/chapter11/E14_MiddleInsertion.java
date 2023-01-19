package exercises.chapter11;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 * Exercise 14
 * Create an empty LinkedList<Integer>. Using a ListIterator,
 * add Integers to the List by always inserting them in
 * the middle of the List.
 */
public class E14_MiddleInsertion {
    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        ListIterator<Integer> it = list.listIterator();
        for (int i = 0; i < 7; i++) {
            it.add(i);
            if (i % 2 == 0)
                it.previous();
        }
        System.out.println(list);
    }
}
/* Output:
[1, 3, 5, 6, 4, 2, 0]
 */