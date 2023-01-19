package exercises.chapter11;

import java.util.*;

import static net.mindview.util.Print.print;

/**
 * Exercise 6
 * Using Strings instead of Pets, modify ListFeatures.java.
 * Explain any difference in results.
 */
public class E06_StringListFeatures {
    public static void main(String[] args) {
        Random rand = new Random(47);
        List<String> strings = new ArrayList<>(Arrays.asList("A", "B", "C", "D", "E", "F", "G"));

        print("1: " + strings);
        String s = "H";
        strings.add(s); // Automatically resizes;
        print("2: " + strings);
        print("3: " + strings.contains(s));
        strings.remove(s); // Remove by object
        String s2 = strings.get(2);
        print("4: " + s2 + " " + strings.indexOf(s2));
        Integer x = 4;
        print("5: " + strings.indexOf(x));
        print("6: " + strings.remove(x));
        // Must be the exact object:
        print("7: " + strings.remove(s2));
        print("8: " + strings);
        strings.add(3, "Z"); // Insert at an index
        print("9: " + strings);
        List<String> sub = strings.subList(1, 4);
        print("subList: " + sub);
        print("10: " + strings.containsAll(sub));
        Collections.sort(sub); // In-place sort
        print("sorted subList: " + sub);
        // Order is not important in containsAll():
        print("11: " + strings.containsAll(sub));
        Collections.shuffle(sub, rand); // Mix it up
        print("shuffled subList: " + sub);
        print("12: " + strings.containsAll(sub));
        List<String> copy = new ArrayList<>(strings);
        sub = Arrays.asList(strings.get(1), strings.get(4));
        print("sub: " + sub);
        copy.retainAll(sub);
        print("13: " + copy);
        copy = new ArrayList<>(strings); // Get a fresh copy
        copy.remove(2); // Remove by index;
        print("14: " + copy);
        copy.removeAll(sub); // Only removes exact objects
        print("15: " + copy);
        copy.set(1, "2"); // Replace an element
        print("16: " + copy);
        copy.addAll(2, sub); // Insert a list in the middle
        print("17: " + copy);
        print("18: " + strings.isEmpty());
        strings.clear(); // Remove all elements
        print("19: " + strings);
        print("20: " + strings.isEmpty());
        strings.addAll(new ArrayList<>(Arrays.asList("A", "B", "C", "D")));
        print("21: " + strings);
        Object[] o = strings.toArray();
        print("22: " + o[3]);
        String[] pa = strings.toArray(new String[0]);
        print("23: " + pa[3]);
    }
}
/* Output:
1: [A, B, C, D, E, F, G]
2: [A, B, C, D, E, F, G, H]
3: true
4: C 2
5: -1
6: false
7: true
8: [A, B, D, E, F, G]
9: [A, B, D, Z, E, F, G]
subList: [B, D, Z]
10: true
sorted subList: [B, D, Z]
11: true
shuffled subList: [D, B, Z]
12: true
sub: [D, E]
13: [D, E]
14: [A, D, Z, E, F, G]
15: [A, Z, F, G]
16: [A, 2, F, G]
17: [A, 2, D, E, F, G]
18: false
19: []
20: true
21: [A, B, C, D]
22: D
23: D
 */