package exercises.chapter11;

import java.util.*;

/**
 * Exercise 11
 * Write a method that uses an Iterator to step through
 * a Collection and print the toString() of each object
 * in the container. Fill all the different types of Collections
 * with objects and apply your method to each container.
 */
public class E11_IteratorToStringTest {
    public static void printCollection(Collection<Object> collection) {
        Iterator<Object> iterator = collection.iterator();
        System.out.print(collection.getClass().getSimpleName() + ": ");
        while (iterator.hasNext())
            System.out.print(iterator.next() + " ");
        System.out.println();
    }

    public static void main(String[] args) {
        printCollection(new ArrayList<>(List.of(1, 2, 3, 4, 5, 6)));
        printCollection(new LinkedList<>(List.of(1, 2, 3, 4, 5, 6)));
        printCollection(new HashSet<>(List.of(1, 2, 3, 4, 5, 6)));
        printCollection(new TreeSet<>(List.of(1, 2, 3, 4, 5, 6)));
        printCollection(new LinkedHashSet<>(Arrays.asList(1, 2, 3, 4, 5, 6)));
    }
}
/* Output:
ArrayList: 1 2 3 4 5 6
LinkedList: 1 2 3 4 5 6
HashSet: 1 2 3 4 5 6
TreeSet: 1 2 3 4 5 6
LinkedHashSet: 1 2 3 4 5 6
 */