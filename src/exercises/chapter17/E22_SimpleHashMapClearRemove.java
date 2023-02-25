package exercises.chapter17;

import containers.MapEntry;
import net.mindview.util.Countries;

import java.util.*;

import static net.mindview.util.Print.print;

/**
 * Exercise 22
 * Implement the clear() and remove() methods for SimpleHashMap.
 */
class SimpleHashMap4<K, V> extends AbstractMap<K, V> {
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

    @Override
    public V remove(Object key) {
        int index = Math.abs(key.hashCode()) % SIZE;
        if (buckets[index] == null) return null;
        Iterator<MapEntry<K, V>> it = buckets[index].iterator();
        while (it.hasNext()) {
            MapEntry<K, V> next = it.next();
            if (next.getKey().equals(key)) {
                V value = next.getValue();
                it.remove();
                return value;
            }
        }
        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void clear() {
        buckets = new LinkedList[SIZE];
    }
}

public class E22_SimpleHashMapClearRemove {
    public static void main(String[] args) {
        SimpleHashMap4<String, String> m = new SimpleHashMap4<>();
        print("m.isEmpty() " + m.isEmpty());
        m.putAll(Countries.capitals(10));
        print("m = " + m);
        print("m.get(\"BURKINA FASO\") = " + m.get("BURKINA FASO"));
        print("m.entrySet() = " + m.entrySet());
        print("m.remove(\"BURKINA FASO\") = " + m.remove("BURKINA FASO"));
        print("m.get(\"BURKINA FASO\") = " + m.get("BURKINA FASO"));
        print("m = " + m);
        print("m.isEmpty() " + m.isEmpty());
        print("m.clear()");
        m.clear();
        print("m.isEmpty() " + m.isEmpty());
        print("m = " + m);
    }
}
/* Output:
m.isEmpty() true
m = {CAPE VERDE=Praia, ANGOLA=Luanda, CENTRAL AFRICAN REPUBLIC=Bangui, BURKINA FASO=Ouagadougou, CHAD=N'djamena, BURUNDI=Bujumbura, BENIN=Porto-Novo, ALGERIA=Algiers, CAMEROON=Yaounde, BOTSWANA=Gaberone}
m.get("BURKINA FASO") = Ouagadougou
m.entrySet() = [CAPE VERDE=Praia, ANGOLA=Luanda, CENTRAL AFRICAN REPUBLIC=Bangui, BURKINA FASO=Ouagadougou, CHAD=N'djamena, BURUNDI=Bujumbura, BENIN=Porto-Novo, ALGERIA=Algiers, CAMEROON=Yaounde, BOTSWANA=Gaberone]
m.remove("BURKINA FASO") = Ouagadougou
m.get("BURKINA FASO") = null
m = {CAPE VERDE=Praia, ANGOLA=Luanda, CENTRAL AFRICAN REPUBLIC=Bangui, CHAD=N'djamena, BURUNDI=Bujumbura, BENIN=Porto-Novo, ALGERIA=Algiers, CAMEROON=Yaounde, BOTSWANA=Gaberone}
m.isEmpty() false
m.clear()
m.isEmpty() true
m = {}
 */