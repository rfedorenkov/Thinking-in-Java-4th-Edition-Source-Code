package exercises.chapter17;

import containers.MapEntry;
import net.mindview.util.Countries;

import java.util.*;

import static net.mindview.util.Print.print;

/**
 * Exercise 39
 * Invoke a private rehash() method in SimpleHashMap when
 * the load factor exceeds 0.75. During rehash, determine
 * the new number of buckets by finding the first prime
 * number greater than twice the original number of buckets.
 */
class SimpleHashMap8<K, V> extends AbstractMap<K, V>{
    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    private static final float loadFactor = 0.75f;
    private int count;
    private int capacity = DEFAULT_INITIAL_CAPACITY;
    private int threshold = (int) (capacity * loadFactor);

    @SuppressWarnings("unchecked")
    LinkedList<MapEntry<K, V>>[] buckets = new LinkedList[capacity];

    {
        print("Initial capacity: " + capacity);
    }

    @Override
    public V put(K key, V value) {
        V oldValue = null;
        int index = Math.abs(key.hashCode()) % capacity;
        if (buckets[index] == null)
            buckets[index] = new LinkedList<>();
        LinkedList<MapEntry<K, V>> bucket = buckets[index];
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

        if (!found) {
            if (count >= threshold)
                rehash();
            if (buckets[index] == null)
                buckets[index] = new LinkedList<>();
            buckets[index].add(pair);
            count++;
        }
        return oldValue;
    }

    @Override
    public V get(Object key) {
        int index = Math.abs(key.hashCode()) % capacity;
        if (buckets[index] == null) return null;
        for (MapEntry<K, V> iPair : buckets[index]) {
            if (iPair.getKey().equals(key))
                return iPair.getValue();
        }
        return null;
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void clear() {
        buckets = new LinkedList[capacity];
        count = 0;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> set = new HashSet<>();
        for (LinkedList<MapEntry<K, V>> bucket : buckets) {
            if (bucket == null) continue;
            for (MapEntry<K, V> mpair : bucket) {
                set.add(mpair);
            }
        }
        return set;
    }

    private boolean isPrime(int value) {
        for (int i = 2; i < value; i++)
            if (value % i == 0) return false;
        return true;
    }

    private int nextPrime(int value) {
        while (!isPrime(value))
            value++;
        return value;
    }

    @SuppressWarnings("unchecked")
    private void rehash() {
        Iterator<Entry<K, V>> it = entrySet().iterator();
        capacity = nextPrime(capacity * 2);
        print("Rehashing, new capacity = " + capacity);
        buckets = new LinkedList[capacity];
        threshold = (int) (capacity * loadFactor);
        count = 0;
        while (it.hasNext()) {
            Map.Entry<K, V> entry = it.next();
            put(entry.getKey(), entry.getValue());
        }
    }
}

public class E39_RehashSimpleHashMap {
    public static void main(String[] args) {
        SimpleHashMap8<String, String> map = new SimpleHashMap8<>();
        print("map.size(): " + map.size());
        print("map.putAll(Countries.capitals())");
        map.putAll(Countries.capitals());
        print("map.size(): " + map.size());
    }
}
/* Output:
Initial capacity: 16
map.size(): 0
map.putAll(Countries.capitals())
Rehashing, new capacity = 37
Rehashing, new capacity = 79
Rehashing, new capacity = 163
Rehashing, new capacity = 331
map.size(): 194
 */