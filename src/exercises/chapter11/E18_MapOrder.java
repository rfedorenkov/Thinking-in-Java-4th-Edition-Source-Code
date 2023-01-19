package exercises.chapter11;

import java.util.*;

/**
 * Exercise 18
 * Fill a HashMap with key-value pairs. Print the results
 * to show ordering by hash code. Extract the pairs, sort
 * by key, and place the result into a LinkedHashMap.
 * Show that insertion order is maintained.
 */
public class E18_MapOrder {
    public static void main(String[] args) {
        Map<String, Integer> hashMap = new HashMap<>();
        hashMap.put("Zero", 0);
        hashMap.put("One", 1);
        hashMap.put("Two", 2);
        hashMap.put("Three", 3);
        hashMap.put("Four", 4);
        hashMap.put("Five", 5);
        hashMap.put("Six", 6);
        hashMap.put("Seven", 7);
        hashMap.put("Eight", 8);
        hashMap.put("Nine", 9);
        System.out.println("hashMap: " + hashMap);
        Set<String> sortedKeys = new TreeSet<>(hashMap.keySet());
        System.out.println("sortedKeys: " + sortedKeys);
        Map<String, Integer> linkedHashMap = new LinkedHashMap<>();
        for (String key : sortedKeys)
            linkedHashMap.put(key, hashMap.get(key));
        System.out.println("linkedHashMap: " + linkedHashMap);
    }
}
/* Output:
hashMap: {Zero=0, Eight=8, Five=5, Six=6, One=1, Four=4, Nine=9, Seven=7, Two=2, Three=3}
sortedKeys: [Eight, Five, Four, Nine, One, Seven, Six, Three, Two, Zero]
linkedHashMap: {Eight=8, Five=5, Four=4, Nine=9, One=1, Seven=7, Six=6, Three=3, Two=2, Zero=0}
 */