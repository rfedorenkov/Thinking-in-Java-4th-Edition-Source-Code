package exercises.chapter17;

import net.mindview.util.Countries;

import java.util.HashMap;
import java.util.Map;

import static net.mindview.util.Print.print;

/**
 * Exercise 38
 * Look up the HashMap class in the JDK documentation.
 * Create a HashMap, fill it with elements, and determine
 * the load factor. Test the lookup speed with this map, then
 * attempt to increase the speed by making a new HashMap with
 * a larger initial capacity and copying the old map into the
 * new one, then run your lookup speed test again on the new map.
 */
public class E38_HashMapLoadFactor {
    public static void testGet(Map<String, String> map) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 1000000; i++) {
            for (int j = 0; j < Countries.DATA.length; j++) {
                String key = Countries.DATA[j][0];
                map.get(key);
            }
        }
        long end = System.currentTimeMillis();
        print("End test: time - " + (end - start));
    }

    public static void main(String[] args) {
        HashMap<String, String> map1 = new HashMap<>();
        map1.putAll(Countries.capitals(11)); // Load factor: 11/16 = 0.69
        print("Start test one");
        testGet(map1);

        // Load factor: 11/64 = 0.17
        HashMap<String, String> map2 = new HashMap<>(64);
        map2.putAll(map1);
        print("Start test two");
        testGet(map2);
    }
}
/* Output:
Start test one
End test: time - 397
Start test two
End test: time - 340
 */