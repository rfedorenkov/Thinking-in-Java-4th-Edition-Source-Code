package exercises.chapter17;

import containers.MapEntry;
import net.mindview.util.Countries;

import java.util.*;

import static net.mindview.util.Print.print;

/**
 * Exercise 23
 * Implement the rest of the Map interface for SimpleHashMap.
 */
class SimpleHashMap5<K, V> implements Map<K, V> {
    // Choose a prime number for the hash table
    // size, to achieve a uniform distribution:
    static final int SIZE = 997;

    // You can't have a physical array of generics,
    // but you can upcast to one:
    @SuppressWarnings("unchecked")
    LinkedList<MapEntry<K, V>>[] buckets = new LinkedList[SIZE];
    private int size = 0;

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
        if (!found) {
            buckets[index].add(pair);
            size++;
        }
        return oldValue;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        Objects.requireNonNull(key);
        return get(key) != null;
    }

    @Override
    public boolean containsValue(Object value) {
        Objects.requireNonNull(value);
        return values().contains(value);
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
                size--;
                return value;
            }
        }
        return null;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        for (Entry<? extends K, ? extends V> entry : m.entrySet())
            put(entry.getKey(), entry.getValue());
    }

    @Override
    @SuppressWarnings("unchecked")
    public void clear() {
        buckets = new LinkedList[SIZE];
        size = 0;
    }

    @Override
    public Set<K> keySet() {
        Set<K> set = new HashSet<>();
        for (Entry<K, V> entry : entrySet())
            set.add(entry.getKey());
        return set;
    }

    @Override
    public Collection<V> values() {
        Collection<V> col = new ArrayList<>();
        for (Entry<K, V> entry : entrySet())
            col.add(entry.getValue());
        return col;
    }

    @Override
    public String toString() {
        return entrySet().toString();
    }
}

public class E23_FullSimpleHashMap {
    public static void main(String[] args) {
        SimpleHashMap5<String, String> m = new SimpleHashMap5<>();
        print("m.isEmpty() = " + m.isEmpty());
        m.putAll(Countries.capitals(10));
        print("m = " + m);
        print("m.size() = " + m.size());
        print("m.isEmpty() = " + m.isEmpty());
        print("m.containsKey(\"BURKINA FASO\") = " + m.containsKey("BURKINA FASO"));
        print("m.containsValue(\"Praia\") = " + m.containsValue("Praia"));
        print("m.entrySet() = " + m.entrySet());
        print("m.keySet() = " + m.keySet());
        print("m.values() = " + m.values());
        print("m.remove(\"BURKINA FASO\") = " + m.remove("BURKINA FASO"));
        print("m.containsKey(\"BURKINA FASO\") = " + m.containsKey("BURKINA FASO"));
        print("m.size() = " + m.size());
        print("m.isEmpty() " + m.isEmpty());
        print("m.clear()");
        m.clear();
        print("m.isEmpty() " + m.isEmpty());
        print("m = " + m);
    }
}
/* Output:
m.isEmpty() = true
m = [CAPE VERDE=Praia, ANGOLA=Luanda, CENTRAL AFRICAN REPUBLIC=Bangui, BURKINA FASO=Ouagadougou, CHAD=N'djamena, BURUNDI=Bujumbura, BENIN=Porto-Novo, ALGERIA=Algiers, CAMEROON=Yaounde, BOTSWANA=Gaberone]
m.size() = 10
m.isEmpty() = false
m.containsKey("BURKINA FASO") = true
m.containsValue("Praia") = true
m.entrySet() = [CAPE VERDE=Praia, ANGOLA=Luanda, CENTRAL AFRICAN REPUBLIC=Bangui, BURKINA FASO=Ouagadougou, CHAD=N'djamena, BURUNDI=Bujumbura, BENIN=Porto-Novo, ALGERIA=Algiers, CAMEROON=Yaounde, BOTSWANA=Gaberone]
m.keySet() = [BENIN, BOTSWANA, CENTRAL AFRICAN REPUBLIC, CHAD, CAMEROON, CAPE VERDE, ANGOLA, BURKINA FASO, BURUNDI, ALGERIA]
m.values() = [Praia, Luanda, Bangui, Ouagadougou, N'djamena, Bujumbura, Porto-Novo, Algiers, Yaounde, Gaberone]
m.remove("BURKINA FASO") = Ouagadougou
m.containsKey("BURKINA FASO") = false
m.size() = 9
m.isEmpty() false
m.clear()
m.isEmpty() true
m = []
 */