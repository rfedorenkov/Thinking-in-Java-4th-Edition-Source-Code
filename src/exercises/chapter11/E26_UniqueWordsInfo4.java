package exercises.chapter11;

import net.mindview.util.TextFile;

import java.util.*;

/**
 * Exercise 26
 * Take the resulting Map from the previous exercise and recreate
 * the order of the words as they appeared in the original file.
 */
public class E26_UniqueWordsInfo4 {
    public static void main(String[] args) {
        Map<String, ArrayList<Integer>> m = new HashMap<>();
        int wordCount = 0;
        List<String> originalWords = new TextFile("src/exercises/chapter11/E26_UniqueWordsInfo4.java", "\\W");
        originalWords.removeIf(s -> s.equals(""));
        for (String s : originalWords) {
            ArrayList<Integer> list = m.get(s);
            if (list == null) {
                list = new ArrayList<>();
                m.put(s, list);
            }
            list.add(++wordCount);
        }
        Map<Integer, String> m2 = new TreeMap<>();
        for (Map.Entry<String, ArrayList<Integer>> entry : m.entrySet()) {
            ArrayList<Integer> list = entry.getValue();
            String s = entry.getKey();
            for (Integer i : list) {
                m2.put(i, s);
            }
        }
        System.out.println(originalWords);
        System.out.println(m2.values());
    }
}
/* Output:
[package, exercises, chapter11, import, net, mindview, util, TextFile, import, java, util, Exercise, 26,
Take, the, resulting, Map, from, the, previous, exercise, and, recreate, the, order, of, the, words, as,
they, appeared, in, the, original, file, public, class, E26_UniqueWordsInfo4, public, static, void, main,
String, args, Map, String, ArrayList, Integer, m, new, HashMap, int, wordCount, 0, List, String, originalWords,
new, TextFile, src, exercises, chapter11, E26_UniqueWordsInfo4, java, W, originalWords, removeIf, s, s,
equals, for, String, s, originalWords, ArrayList, Integer, list, m, get, s, if, list, null, list, new,
ArrayList, m, put, s, list, list, add, wordCount, Map, Integer, String, m2, new, TreeMap, for, Map, Entry,
String, ArrayList, Integer, entry, m, entrySet, ArrayList, Integer, list, entry, getValue, String, s, entry,
getKey, for, Integer, i, list, m2, put, i, s, System, out, println, originalWords, System, out, println, m2, values]
[package, exercises, chapter11, import, net, mindview, util, TextFile, import, java, util, Exercise, 26,
Take, the, resulting, Map, from, the, previous, exercise, and, recreate, the, order, of, the, words, as,
they, appeared, in, the, original, file, public, class, E26_UniqueWordsInfo4, public, static, void, main,
String, args, Map, String, ArrayList, Integer, m, new, HashMap, int, wordCount, 0, List, String, originalWords,
new, TextFile, src, exercises, chapter11, E26_UniqueWordsInfo4, java, W, originalWords, removeIf, s, s,
equals, for, String, s, originalWords, ArrayList, Integer, list, m, get, s, if, list, null, list, new,
ArrayList, m, put, s, list, list, add, wordCount, Map, Integer, String, m2, new, TreeMap, for, Map, Entry,
String, ArrayList, Integer, entry, m, entrySet, ArrayList, Integer, list, entry, getValue, String, s, entry,
getKey, for, Integer, i, list, m2, put, i, s, System, out, println, originalWords, System, out, println, m2, values]
 */