package exercises.chapter17;

import net.mindview.util.Countries;

import java.util.*;

import static net.mindview.util.Print.print;

/**
 * Exercise 25
 * Instead of using a ListIterator for each bucket, modify MapEntry
 * so it is a self-contained singly-linked list (each MapEntry should
 * have a forward link to the next MapEntry). Modify the rest of the
 * code in SimpleHashMap.java so this new approach works correctly.
 */
class SimpleHashMap6<K, V> extends AbstractMap<K, V> {
    static class Entry<K, V> implements Map.Entry<K, V> {
        private K key;
        private V value;
        Entry<K, V> next;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        @Override
        public V setValue(V value) {
            V oldValue = this.value;
            this.value = value;
            return oldValue;
        }

        @Override
        public int hashCode() {
            return key.hashCode() ^ (value == null ? 0 : value.hashCode());
        }

        @Override
        @SuppressWarnings("unchecked")
        public boolean equals(Object obj) {
            if (obj instanceof Entry) {
                Entry<K, V> entry = (Entry<K, V>) obj;
                return key.equals(entry.getKey()) && value.equals(entry.getValue());
            }
            return false;
        }

        @Override
        public String toString() {
            return key + " = " + value;
        }
    }

    static final int SIZE = 997;

    @SuppressWarnings("unchecked")
    Entry<K, V>[] buckets = new Entry[SIZE];

    @Override
    public V put(K key, V value) {
        V oldValue = null;
        int index = Math.abs(key.hashCode()) % SIZE;
        Entry<K, V> newEntry = new Entry<>(key, value);
        if (buckets[index] == null)
            buckets[index] = newEntry;
        Entry<K, V> previous = null;
        boolean found = false;
        for (Entry<K, V> pair = buckets[index]; pair != null; pair = pair.next) {
            if (pair.getKey().equals(key)) {
                oldValue = pair.getValue();
                if (previous != null)
                    previous.next = newEntry;
                else
                    buckets[index] = newEntry;
                newEntry.next = pair.next;
                found = true;
                break;
            }
            previous = pair;
        }
        if (!found)
            previous.next = newEntry;
        return oldValue;
    }

    @Override
    public V get(Object key) {
        int index = Math.abs(key.hashCode()) % SIZE;
        for (Entry<K, V> pair = buckets[index]; pair != null; pair = pair.next) {
            if (pair.getKey().equals(key))
                return pair.getValue();
        }
        return null;
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        Set<Map.Entry<K, V>> set = new HashSet<>();
        for (Entry<K, V> bucket : buckets) {
            for (Entry<K, V> pair = bucket; pair != null; pair = pair.next) {
                set.add(pair);
            }
        }
        return set;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void clear() {
        buckets = new Entry[SIZE];
    }

    @Override
    public V remove(Object key) {
        int index = Math.abs(key.hashCode()) % SIZE;
        Entry<K, V> previous = null;
        for (Entry<K, V> pair = buckets[index]; pair != null; pair = pair.next) {
            if (pair.getKey().equals(key)) {
                V value = pair.getValue();
                if (previous != null)
                    previous.next = pair.next;
                else
                    buckets[index] = null;
                return value;
            }
            previous = pair;
        }
        return null;
    }

    @Override
    public int size() {
        int size = 0;
        for (Entry<K, V> entry : buckets) {
            for (Entry<K, V> pair = entry; pair != null; pair = pair.next) {
                size++;
            }
        }
        return size;
    }

    @Override
    public boolean isEmpty() {
        for (Entry<K, V> bucket : buckets)
            if (bucket != null)
                return false;
        return true;
    }

    @Override
    public boolean containsKey(Object key) {
        Objects.requireNonNull(key);
        for (Entry<K, V> entry : buckets) {
            for (Entry<K, V> pair = entry; pair != null; pair = pair.next) {
                if (pair.key.equals(key))
                    return true;
            }
        }
        return false;
    }
}

public class E25_FullSimpleHashMap2 {
    public static void main(String[] args) {
        SimpleHashMap6<String, String> m = new SimpleHashMap6<>();
        print("m.isEmpty() = " + m.isEmpty());
        m.putAll(Countries.capitals(6));
        print(m);
        print("m.size() = " + m.size());
        print("m.get(\"BURKINA FASO\") = " + m.get("BURKINA FASO"));
        print(m.entrySet());
        print("m.containsKey(\"BURKINA FASO\") = " + m.containsKey("BURKINA FASO"));
        print("m.remove(\"BURKINA FASO\") = " + m.remove("BURKINA FASO"));
        print("m.get(\"BURKINA FASO\") = " + m.get("BURKINA FASO"));
        print("m.containsKey(\"BURKINA FASO\") = " + m.containsKey("BURKINA FASO"));
        print("m.size() = " + m.size());
        print("m.isEmpty() = " + m.isEmpty());
        print("m.clear()");
        m.clear();
        print("m.size() = " + m.size());
        print("m.isEmpty() = " + m.isEmpty());
    }
}
/* Output:
m.isEmpty() = true
{ANGOLA=Luanda, BURKINA FASO=Ouagadougou, BURUNDI=Bujumbura, BENIN=Porto-Novo, ALGERIA=Algiers, BOTSWANA=Gaberone}
m.size() = 6
m.get("BURKINA FASO") = Ouagadougou
[ANGOLA = Luanda, BURKINA FASO = Ouagadougou, BURUNDI = Bujumbura, BENIN = Porto-Novo, ALGERIA = Algiers, BOTSWANA = Gaberone]
m.containsKey("BURKINA FASO") = true
m.remove("BURKINA FASO") = Ouagadougou
m.get("BURKINA FASO") = null
m.containsKey("BURKINA FASO") = false
m.size() = 5
m.isEmpty() = false
m.clear()
m.size() = 0
m.isEmpty() = true
 */