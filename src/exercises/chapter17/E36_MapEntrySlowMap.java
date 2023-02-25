package exercises.chapter17;

import containers.MapEntry;
import containers.Test;
import containers.TestParam;
import containers.Tester;

import java.util.*;

/**
 * Exercise 36
 * Modify SlowMap so that instead of two ArrayList, it holds
 * a single ArrayList of MapEntry objects. Verify that the modified
 * version works correctly. Using MapPerformance.java, test the speed
 * of your new Map. Now change the put() method so that it performs
 * a sort() after each pair is entered, and modify get() to use
 * Collections.binarySearch() to look up the key. Compare the
 * performance of the new version with the old ones.
 */
class MapEntrySlowMap1<K, V> extends AbstractMap<K, V> {
    protected List<MapEntry<K, V>> entries = new ArrayList<>();

    public MapEntry<K, V> getEntry(Object key) {
        for (MapEntry<K, V> entry : entries)
            if (entry.getKey().equals(key))
                return entry;
        return null;
    }

    @Override
    public V put(K key, V value) {
        Objects.requireNonNull(key);
        MapEntry<K, V> oldEntry = getEntry(key);
        V oldValue = null;
        if (oldEntry == null)
            entries.add(new MapEntry<>(key, value));
        else {
            oldValue = oldEntry.getValue();
            oldEntry.setValue(value);
        }
        return oldValue;
    }

    @Override
    public V get(Object key) { // key is type Object, not K
        Objects.requireNonNull(key);
        MapEntry<K, V> entry = getEntry(key);
        if (entry != null)
            return entry.getValue();
        return null;
    }

    private EntrySet entrySet = new EntrySet();

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        return entrySet;
    }

    private class EntrySet extends AbstractSet<Map.Entry<K, V>> {

        @Override
        public Iterator<Entry<K, V>> iterator() {
            return new Iterator<Entry<K, V>>() {
                private int index = -1;

                @Override
                public boolean hasNext() {
                    return index < entries.size() - 1;
                }

                @Override
                public Entry<K, V> next() {
                    return entries.get(++index);
                }

                @Override
                public void remove() {
                    entries.remove(index--);
                }
            };
        }

        @Override
        @SuppressWarnings("unchecked")
        public boolean contains(Object o) {
            if (o instanceof MapEntry) {
                MapEntry<K, V> entry = (MapEntry<K, V>) o;
                return entry.equals(getEntry(entry.getKey()));
            }
            return false;
        }

        @Override
        public boolean remove(Object o) {
            if (contains(o))
                return entries.remove(o);
            return false;
        }

        @Override
        public int size() {
            return entries.size();
        }

        @Override
        public void clear() {
            entries.clear();
        }
    }
}

class MapEntrySlowMap2<K, V> extends MapEntrySlowMap1<K, V> {
    private Comparator<MapEntry<K, V>> comp = new Comparator<MapEntry<K, V>>() {
        @Override
        @SuppressWarnings("unchecked")
        public int compare(MapEntry<K, V> o1, MapEntry<K, V> o2) {
            Comparable<K> c1 = (Comparable<K>) o1.getKey();
            return c1.compareTo(o2.getKey());
        }
    };

    @Override
    public MapEntry<K, V> getEntry(Object key) {
        MapEntry<K, V> searchEntry = new MapEntry<>((K) key, null);
        int index = Collections.binarySearch(entries, searchEntry, comp);
        if (index >= 0)
            entries.get(index);
        return null;
    }

    @Override
    public V put(K key, V value) {
        Objects.requireNonNull(key);
        MapEntry<K, V> oldEntry = getEntry(key);
        V oldValue = null;
        if (oldEntry == null) {
            entries.add(new MapEntry<>(key, value));
            Collections.sort(entries, comp);
        } else {
            oldValue = oldEntry.getValue();
            oldEntry.setValue(value);
        }
        return oldValue;
    }
}

public class E36_MapEntrySlowMap {
    static List<Test<Map<Integer, Integer>>> tests = new ArrayList<>();

    static {
        tests.add(new Test<Map<Integer, Integer>>("put") {
            @Override
            public int test(Map<Integer, Integer> map, TestParam tp) {
                int loops = tp.loops;
                int size = tp.size;
                for (int i = 0; i < loops; i++) {
                    map.clear();
                    for (int j = 0; j < size; j++) {
                        map.put(i, j);
                    }
                }
                return loops * size;
            }
        });

        tests.add(new Test<Map<Integer, Integer>>("get") {
            @Override
            public int test(Map<Integer, Integer> map, TestParam tp) {
                int loops = tp.loops;
                int span = tp.size * 2;
                for (int i = 0; i < loops; i++)
                    for (int j = 0; j < span; j++)
                        map.get(j);
                return loops * span;
            }
        });

        tests.add(new Test<Map<Integer, Integer>>("iterate") {
            @Override
            public int test(Map<Integer, Integer> map, TestParam tp) {
                int loops = tp.loops * 10;
                for (int i = 0; i < loops; i++) {
                    Iterator<Map.Entry<Integer, Integer>> it = map.entrySet().iterator();
                    while (it.hasNext())
                        it.next();
                }
                return loops * tp.size;
            }
        });
    }

    public static void main(String[] args) {
        Tester.run(new TreeMap<>(), tests);
        Tester.run(new HashMap<>(), tests);
        Tester.run(new LinkedHashMap<>(), tests);
        Tester.run(new IdentityHashMap<>(), tests);
        Tester.run(new WeakHashMap<>(), tests);
        Tester.run(new Hashtable<>(), tests);
        Tester.run(new SlowMap2<>(), tests);
        Tester.run(new SlowMap3<>(), tests);
        Tester.run(new MapEntrySlowMap1<>(), tests);
        Tester.run(new MapEntrySlowMap2<>(), tests);
    }
}
/* Output:
---------- TreeMap ----------
 size     put     get iterate
   10      65      28       8
  100      13       2       0
 1000       6       3       0
10000       4       3       0
---------- HashMap ----------
 size     put     get iterate
   10      68      41      12
  100      24       2       0
 1000       8       2       0
10000       7       2       0
------- LinkedHashMap -------
 size     put     get iterate
   10     129      25       4
  100      13       7       0
 1000       9       6       0
10000       7       8       0
------ IdentityHashMap ------
 size     put     get iterate
   10     112      16      11
  100      83      33       6
 1000      82      84       7
10000      77      89      10
-------- WeakHashMap --------
 size     put     get iterate
   10      47      13       7
  100       7       7       0
 1000       8       5       0
10000       6       4       0
--------- Hashtable ---------
 size     put     get iterate
   10      50      10       6
  100       6       4       0
 1000       6       4       0
10000       6       8       0
---------- SlowMap2 ----------
 size     put     get iterate
   10      58      17       4
  100      10       5       0
 1000      30       5       0
10000      28       5       0
---------- SlowMap3 ----------
 size     put     get iterate
   10      31       7       4
  100      12       5       0
 1000       9       5       0
10000      19       5       0
------ MapEntrySlowMap1 ------
 size     put     get iterate
   10      38      28       3
  100       6       5       0
 1000       6       5       0
10000       5       5       0
------ MapEntrySlowMap2 ------
 size     put     get iterate
   10     117      59       7
  100     110      11       6
 1000     666      15       5
10000   10653      26       6
 */