package exercises.chapter11;

import java.util.*;

/**
 * Exercise 24
 * Fill al LinkedHashMap with String keys and objects.
 * Extract the pairs, sort them based on the keys,
 * and re-insert them into the Map.
 */
public class E24_MapOrder2 {
    public static void main(String[] args) {
        Map<String, Integer> linkedHashMap = new LinkedHashMap<>();
        linkedHashMap.put("Zero", 0);
        linkedHashMap.put("One", 1);
        linkedHashMap.put("Two", 2);
        linkedHashMap.put("Three", 3);
        linkedHashMap.put("Four", 4);
        linkedHashMap.put("Five", 5);
        linkedHashMap.put("Six", 6);
        linkedHashMap.put("Seven", 7);
        linkedHashMap.put("Eight", 8);
        linkedHashMap.put("Nine", 9);
        System.out.println("LinkedHashMap: " + linkedHashMap);
        Set<String> sortedKeys = new TreeSet<>(linkedHashMap.keySet());
        System.out.println("sortedKeys: " + sortedKeys);
        Map<String, Integer> linkedHashMap2 = new LinkedHashMap<>();
        for (String key : sortedKeys)
            linkedHashMap2.put(key, linkedHashMap.get(key));
        System.out.println("linkedHashMap2: " + linkedHashMap2);
    }
}