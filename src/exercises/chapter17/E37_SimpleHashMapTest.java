package exercises.chapter17;

import containers.MapEntry;
import containers.SimpleHashMap;
import containers.Tester;

import java.util.*;

/**
 * Exercise 37
 * Modify SimpleHashMap to use ArrayLists instead
 * of LinkedLists. Modify MapPerformance.java to
 * compare the performance of the two implementations.
 */
class SimpleHashMap7<K, V> extends AbstractMap<K, V> {
    // Choose a prime number for the hash table
    // size, to achieve a uniform distribution:
    static final int SIZE = 997;

    // You can't have a physical array of generics,
    // but you can upcast to one:
    @SuppressWarnings("unchecked")
    ArrayList<MapEntry<K, V>>[] buckets = new ArrayList[SIZE];

    @Override
    public V put(K key, V value) {
        V oldValue = null;
        int index = Math.abs(key.hashCode()) % SIZE;
        if (buckets[index] == null)
            buckets[index] = new ArrayList<>();
        ArrayList<MapEntry<K, V>> bucket = buckets[index];
        MapEntry<K, V> pair = new MapEntry<>(key, value);
        boolean found = false;
        ListIterator<MapEntry<K, V>> it = bucket.listIterator();
        while (it.hasNext()) {
            MapEntry<K, V> iPair = it.next();
            if (iPair.getKey().equals(key)) {
                oldValue = iPair.getValue();
                it.set(pair); // Replace old with new
                found = true;
                break;
            }
        }
        if (!found)
            buckets[index].add(pair);
        return oldValue;
    }

    @Override
    public V get(Object key) {
        int index = Math.abs(key.hashCode()) % SIZE;
        if (buckets[index] == null) return null;
        for (MapEntry<K, V> iPair : buckets[index]) {
            if (iPair.getKey().equals(key))
                return iPair.getValue();
        }
        return null;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> set = new HashSet<>();
        for (ArrayList<MapEntry<K, V>> bucket : buckets) {
            if (bucket == null) continue;
            for (MapEntry<K, V> mpair : bucket) {
                set.add(mpair);
            }
        }
        return set;
    }
}

public class E37_SimpleHashMapTest {
    public static void main(String[] args) {
        Tester.run(new SimpleHashMap<>(), E35_SlowMapPerformance.tests);
        Tester.run(new SimpleHashMap7<>(), E35_SlowMapPerformance.tests);
    }
}
/* Output:
------- SimpleHashMap -------
 size     put     get iterate
   10    5228      38    7259
  100     790       3     860
 1000      90       6      86
10000      16      15       8
------- SimpleHashMap7 -------
 size     put     get iterate
   10    4335      47    7096
  100     666       4     746
 1000      79       8      76
10000      15      14       7
 */