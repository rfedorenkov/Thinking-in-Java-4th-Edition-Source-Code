package exercises.chapter17;

import containers.MapEntry;
import net.mindview.util.Countries;

import java.util.*;

import static net.mindview.util.Print.print;

/**
 * Exercise 20
 * Modify SimpleHashMap so it reports collisions, and test
 * this by adding the same data set twice so you collisions.
 */
class SimpleHashMap2<K, V> extends AbstractMap<K, V> {
    // Choose a prime number for the hash table
    // size, to achieve a uniform distribution:
    static final int SIZE = 997;

    // You can't have a physical array of generics,
    // but you can upcast to one:
    @SuppressWarnings("unchecked")
    LinkedList<MapEntry<K, V>>[] buckets = new LinkedList[SIZE];

    @Override
    public V put(K key, V value) {
        V oldValue = null;
        int index = Math.abs(key.hashCode()) % SIZE;
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
                print("Collision (oldValue = " + iPair + "), newValue(" + pair + ")");
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
        for (LinkedList<MapEntry<K, V>> bucket : buckets) {
            if (bucket == null) continue;
            for (MapEntry<K, V> mpair : bucket) {
                set.add(mpair);
            }
        }
        return set;
    }
}

public class E20_SimpleHashMapCollisionsTest {
    public static void main(String[] args) {
        SimpleHashMap2<String, String> m = new SimpleHashMap2<>();
        m.putAll(Countries.capitals(6));
        System.out.println(m);
        m.putAll(Countries.capitals(3));
    }
}
/* Output:
{ANGOLA=Luanda, BURKINA FASO=Ouagadougou, BURUNDI=Bujumbura, BENIN=Porto-Novo, ALGERIA=Algiers, BOTSWANA=Gaberone}
Collision (oldValue = ALGERIA=Algiers), newValue(ALGERIA=Algiers)
Collision (oldValue = ANGOLA=Luanda), newValue(ANGOLA=Luanda)
Collision (oldValue = BENIN=Porto-Novo), newValue(BENIN=Porto-Novo)
 */