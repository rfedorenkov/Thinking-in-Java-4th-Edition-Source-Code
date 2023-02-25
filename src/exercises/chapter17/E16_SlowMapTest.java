package exercises.chapter17;

import containers.MapEntry;
import containers.Maps;

import java.util.*;

/**
 * Exercise 16
 * Apply the tests in Maps.java to SlowMap to verify that
 * it works. Fix anything in SlowMap that doesn't work correctly.
 */
class SlowMap2<K, V> extends AbstractMap<K, V> {
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
    public V get(Object key) { // key is type Object, not K
        if (!keys.contains(key))
            return null;
        return values.get(keys.indexOf(key));
    }

    private Set<Map.Entry<K, V>> entrySet = new AbstractSet<>() {
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
                    index++;
                    return new MapEntry<>(keys.get(index), values.get(index));
                }

                @Override
                public void remove() {
                    keys.remove(index);
                    values.remove(index);
                    index--;
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
}

public class E16_SlowMapTest {
    public static void main(String[] args) {
        Maps.test(new SlowMap2<>());
    }
}
/* Output:
SlowMap2
Size = 25, Keys: [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24]
Values: [A0, B0, C0, D0, E0, F0, G0, H0, I0, J0, K0, L0, M0, N0, O0, P0, Q0, R0, S0, T0, U0, V0, W0, X0, Y0]
{0=A0, 1=B0, 2=C0, 3=D0, 4=E0, 5=F0, 6=G0, 7=H0, 8=I0, 9=J0, 10=K0, 11=L0, 12=M0, 13=N0, 14=O0, 15=P0, 16=Q0, 17=R0, 18=S0, 19=T0, 20=U0, 21=V0, 22=W0, 23=X0, 24=Y0}
map.containsKey(11): true
map.get(11): L0
map.containsValue("FO"): true
First key in map: 0
Size = 24, Keys: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24]
map.isEmpty(): true
map.isEmpty(): true
 */