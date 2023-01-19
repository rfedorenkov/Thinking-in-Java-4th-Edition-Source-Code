package exercises.chapter11;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * Exercise 19
 * Repeat Exercise 18 with a HashSet and LinkedHashSet.
 */
public class E19_SetOrder {
    public static void main(String[] args) {
        Set<String> hashSet = new HashSet<>();
        hashSet.add("Zero");
        hashSet.add("One");
        hashSet.add("Two");
        hashSet.add("Three");
        hashSet.add("Four");
        hashSet.add("Five");
        hashSet.add("Six");
        hashSet.add("Seven");
        hashSet.add("Eight");
        hashSet.add("Nine");
        System.out.println("hashSet: " + hashSet);
        Set<String> sortedSet = new TreeSet<>(hashSet);
        System.out.println("sortedSet: " + sortedSet);
        Set<String> linkedHashSet = new LinkedHashSet<>(sortedSet);
        System.out.println("linkedHashSet: " + linkedHashSet);
    }
}
/* Output:
hashSet: [Zero, Eight, Five, Six, One, Four, Nine, Seven, Two, Three]
sortedSet: [Eight, Five, Four, Nine, One, Seven, Six, Three, Two, Zero]
linkedHashSet: [Eight, Five, Four, Nine, One, Seven, Six, Three, Two, Zero]
 */