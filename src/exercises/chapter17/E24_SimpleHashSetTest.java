package exercises.chapter17;

import net.mindview.util.Countries;

import java.util.AbstractSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

import static net.mindview.util.Print.print;

/**
 * Exercise 24
 * Following the example in SimpleHashMap.java
 * create and test a SimpleHashSet.
 */
class SimpleHashSet<K> extends AbstractSet<K> {
    static final int SIZE = 997;

    @SuppressWarnings("unchecked")
    LinkedList<K>[] buckets = new LinkedList[SIZE];

    @Override
    public boolean add(K k) {
        int index = Math.abs(k.hashCode()) % SIZE;
        if (buckets[index] == null)
            buckets[index] = new LinkedList<>();
        LinkedList<K> bucket = buckets[index];
        ListIterator<K> it = bucket.listIterator();
        while (it.hasNext()) {
            K key = it.next();
            if (key.equals(k))
                return false;
        }
        it.add(k);
        return true;
    }

    @Override
    public boolean contains(Object o) {
        int index = Math.abs(o.hashCode()) % SIZE;
        if (buckets[index] != null) {
            return buckets[index].contains(o);
        }
        return false;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            int count = -1;
            int index = 0;
            int position = 0;

            @Override
            public boolean hasNext() {
                return count < size() - 1;
            }

            @Override
            public K next() {
                for (int i = position; i < buckets.length; i++) {
                    if (buckets[i] == null) {
                        position++;
                    } else {
                        try {
                            count++;
                            position = i;
                            return buckets[i].get(index++);
                        } catch (IndexOutOfBoundsException e) {
                            index = 0;
                            --count;
                        }
                    }
                }
                return null;
            }

            @Override
            public void remove() {
                buckets[position].remove(--index);
                count--;
            }
        };
    }

    @Override
    public int size() {
        int size = 0;
        for (LinkedList<K> bucket : buckets) {
            if (bucket != null)
                size += bucket.size();
        }
        return size;
    }
}

public class E24_SimpleHashSetTest {
    public static void main(String[] args) {
        SimpleHashSet<String> set = new SimpleHashSet<>();
        set.addAll(Countries.capitals(10).keySet());
        print("set.size() = " + set.size());
        print(set);
        Iterator<String> it = set.iterator();
        print(it.next());
        it.remove();
        print(set);
        print("set.size() = " + set.size());
    }
}
/* Output:
set.size() = 10
[BOTSWANA, BENIN, ALGERIA, BURKINA FASO, CAMEROON, CENTRAL AFRICAN REPUBLIC, CAPE VERDE, CHAD, ANGOLA, BURUNDI]
BOTSWANA
[BENIN, ALGERIA, BURKINA FASO, CAMEROON, CENTRAL AFRICAN REPUBLIC, CAPE VERDE, CHAD, ANGOLA, BURUNDI]
set.size() = 9
 */