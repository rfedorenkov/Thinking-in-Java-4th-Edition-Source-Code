package exercises.chapter17;

import containers.MapEntry;
import containers.Maps;

import java.util.*;

/**
 * Exercise 17
 * Implement the rest of the Map interface for SlowMap.
 */
class SlowMap3<K, V> implements Map<K, V> {
    private List<K> keys = new ArrayList<>();
    private List<V> values = new ArrayList<>();

    @Override
    public V put(K key, V value) {
        V oldValue = get(key); // The old value or null
        if (!keys.contains(key)) {
            keys.add(key);
            values.add(value);
        } else
            values.set(keys.indexOf(key), value);
        return oldValue;
    }

    @Override
    public V remove(Object key) {
        Objects.requireNonNull(key);
        int index = keys.indexOf(key);
        keys.remove(key);
        return values.remove(index);
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        for (Entry<? extends K, ? extends V> entry : m.entrySet())
            put(entry.getKey(), entry.getValue());
    }

    @Override
    public int size() {
        return keys.size();
    }

    @Override
    public boolean isEmpty() {
        return keys.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        Objects.requireNonNull(key);
        return keys.contains(key);
    }

    @Override
    public boolean containsValue(Object value) {
        Objects.requireNonNull(value);
        return values.contains(value);
    }

    @Override
    public V get(Object key) { // key is type Object, not K
        if (!keys.contains(key))
            return null;
        return values.get(keys.indexOf(key));
    }

    private Set<Entry<K, V>> entrySet = new AbstractSet<>() {
        @Override
        public Iterator<Entry<K, V>> iterator() {
            return new Iterator<>() {
                private int index = -1;

                @Override
                public boolean hasNext() {
                    return index < size() - 1;
                }

                @Override
                public Entry<K, V> next() {
                    return new MapEntry<>(keys.get(++index), values.get(index));
                }

                @Override
                public void remove() {
                    keys.remove(index);
                    values.remove(index--);
                }
            };
        }

        @Override
        public int size() {
            return keys.size();
        }
    };

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        return entrySet;
    }

    @Override
    public void clear() {
        keys.clear();
        values.clear();
    }

    private Set<K> keySet = new AbstractSet<K>() {
        @Override
        public Iterator<K> iterator() {
            return new Iterator<K>() {
                private int index = -1;

                @Override
                public boolean hasNext() {
                    return index < size() - 1;
                }

                @Override
                public K next() {
                    return keys.get(++index);
                }

                @Override
                public void remove() {
                    keys.remove(index--);
                }
            };
        }

        @Override
        public int size() {
            return keys.size();
        }
    };

    @Override
    public Set<K> keySet() {
        return keySet;
    }

    @Override
    public Collection<V> values() {
        return values;
    }

    @Override
    public int hashCode() {
        return entrySet.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof SlowMap3) {
            return entrySet.equals(((SlowMap3<?, ?>) o).entrySet);
        }
        return false;
    }

    @Override
    public String toString() {
        return entrySet.toString();
    }
}

public class E17_SlowMapTest2 {
    public static void main(String[] args) {
        Maps.test(new SlowMap3<>());
    }
}
/* Output:
SlowMap3
Size = 25, Keys: [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24]
Values: [A0, B0, C0, D0, E0, F0, G0, H0, I0, J0, K0, L0, M0, N0, O0, P0, Q0, R0, S0, T0, U0, V0, W0, X0, Y0]
[0=A0, 1=B0, 2=C0, 3=D0, 4=E0, 5=F0, 6=G0, 7=H0, 8=I0, 9=J0, 10=K0, 11=L0, 12=M0, 13=N0, 14=O0, 15=P0, 16=Q0, 17=R0, 18=S0, 19=T0, 20=U0, 21=V0, 22=W0, 23=X0, 24=Y0]
map.containsKey(11): true
map.get(11): L0
map.containsValue("FO"): true
First key in map: 0
Size = 24, Keys: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24]
map.isEmpty(): true
map.isEmpty(): true
 */