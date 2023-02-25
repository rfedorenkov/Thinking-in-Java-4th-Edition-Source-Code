package exercises.chapter17;

import containers.MapEntry;
import net.mindview.util.Countries;

import java.util.*;

import static net.mindview.util.Print.print;

/**
 * Exercise 21
 * Modify SimpleHashMap so that it reports the number
 * of "probes" necessary when collisions occur. That is,
 * how many calls to next() must be made on the Iterators
 * that walk the LinkedList looking for matches?
 */
class SimpleHashMap3<K, V> extends AbstractMap<K, V> {
    // Choose a prime number for the hash table
    // size, to achieve a uniform distribution:
    static final int SIZE = 7;

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
        int probes = 0;
        while (it.hasNext()) {
            probes++;
            MapEntry<K, V> iPair = it.next();
            if (iPair.getKey().equals(key)) {
                oldValue = iPair.getValue();
                it.set(pair); // Replace old with new
                found = true;
                print("Collision (oldValue = " + iPair + "), newValue(" + pair + "), probes = " + probes);
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
        int probes = 0;
        for (MapEntry<K, V> iPair : buckets[index]) {
            probes++;
            if (iPair.getKey().equals(key)) {
                print(iPair + " probes = " + probes);
                return iPair.getValue();
            }
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

public class E21_SimpleHashMapCollisionsTest2 {
    public static void main(String[] args) {
        SimpleHashMap3<String, String> m = new SimpleHashMap3<>();
        m.putAll(Countries.capitals(5));
        print(m);
        m.putAll(Countries.capitals(5));
        print(m.get("BURKINA FASO"));
    }
}
/* Output:
{ANGOLA=Luanda, BURKINA FASO=Ouagadougou, BENIN=Porto-Novo, ALGERIA=Algiers, BOTSWANA=Gaberone}
Collision (oldValue = ALGERIA=Algiers), newValue(ALGERIA=Algiers), probes = 1
Collision (oldValue = ANGOLA=Luanda), newValue(ANGOLA=Luanda), probes = 1
Collision (oldValue = BENIN=Porto-Novo), newValue(BENIN=Porto-Novo), probes = 1
Collision (oldValue = BOTSWANA=Gaberone), newValue(BOTSWANA=Gaberone), probes = 2
Collision (oldValue = BURKINA FASO=Ouagadougou), newValue(BURKINA FASO=Ouagadougou), probes = 2
BURKINA FASO=Ouagadougou probes = 2
Ouagadougou
 */