package exercises.chapter11;

import java.util.*;

import static net.mindview.util.Print.print;

/**
 * Exercise 5
 * Use Integers instead of Pets to modify
 * ListFeatures.java (remember autoboxing).
 * Explain any difference in results.
 */
public class E05_IntegerListFeatures {
    public static void main(String[] args) {
        Random rand = new Random(47);
        List<Integer> integers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7));

        print("1: " + integers);
        Integer i = 2;
        integers.add(i); // Automatically resizes;
        print("2: " + integers);
        print("3: " + integers.contains(i));
        integers.remove(i); // Remove by object
        Integer j = integers.get(2);
        print("4: " + j + " " + integers.indexOf(j));
        Integer x = 4;
        print("5: " + integers.indexOf(x));
        print("6: " + integers.remove(x));
        // Must be the exact object:
        print("7: " + integers.remove(j));
        print("8: " + integers);
        integers.add(3, 9); // Insert at an index
        print("9: " + integers);
        List<Integer> sub = integers.subList(1, 4);
        print("subList: " + sub);
        print("10: " + integers.containsAll(sub));
        Collections.sort(sub); // In-place sort
        print("sorted subList: " + sub);
        // Order is not important in containsAll():
        print("11: " + integers.containsAll(sub));
        Collections.shuffle(sub, rand); // Mix it up
        print("shuffled subList: " + sub);
        print("12: " + integers.containsAll(sub));
        List<Integer> copy = new ArrayList<>(integers);
        sub = Arrays.asList(integers.get(1), integers.get(4));
        print("sub: " + sub);
        copy.retainAll(sub);
        print("13: " + copy);
        copy = new ArrayList<>(integers); // Get a fresh copy
        copy.remove(2); // Remove by index;
        print("14: " + copy);
        copy.removeAll(sub); // Only removes exact objects
        print("15: " + copy);
        copy.set(1, 2); // Replace an element
        print("16: " + copy);
        copy.addAll(2, sub); // Insert a list in the middle
        print("17: " + copy);
        print("18: " + integers.isEmpty());
        integers.clear(); // Remove all elements
        print("19: " + integers);
        print("20: " + integers.isEmpty());
        integers.addAll(new ArrayList<>(Arrays.asList(1, 2, 3, 4)));
        print("21: " + integers);
        Object[] o = integers.toArray();
        print("22: " + o[3]);
        Integer[] pa = integers.toArray(new Integer[0]);
        print("23: " + pa[3]);
    }
}
/* Output:
1: [1, 2, 3, 4, 5, 6, 7]
2: [1, 2, 3, 4, 5, 6, 7, 2]
3: true
4: 4 2
5: 2
6: true
7: false
8: [1, 3, 5, 6, 7, 2]
9: [1, 3, 5, 9, 6, 7, 2]
subList: [3, 5, 9]
10: true
sorted subList: [3, 5, 9]
11: true
shuffled subList: [5, 3, 9]
12: true
sub: [5, 6]
13: [5, 6]
14: [1, 5, 9, 6, 7, 2]
15: [1, 9, 7, 2]
16: [1, 2, 7, 2]
17: [1, 2, 5, 6, 7, 2]
18: false
19: []
20: true
21: [1, 2, 3, 4]
22: 4
23: 4
 */