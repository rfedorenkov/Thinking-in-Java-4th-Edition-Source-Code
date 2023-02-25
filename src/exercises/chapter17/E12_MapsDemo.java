package exercises.chapter17;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

import static net.mindview.util.Print.print;

/**
 * Exercise 12
 * Substitute a HashMap, a TreeMap, and a LinkedHashMap
 * in AssociativeArray.java's main().
 */
public class E12_MapsDemo {
    public static void test(Map<String,String> map) {
        map.put("sky", "blue");
        map.put("grass", "green");
        map.put("ocean", "dancing");
        map.put("tree", "tall");
        map.put("earth", "brown");
        map.put("sun", "warm");
        try {
            map.put("extra", "object"); // Past the end
        } catch (ArrayIndexOutOfBoundsException e) {
            print("Too many objects!");
        }
        print(map);
        print(map.get("ocean"));
    }
    public static void main(String[] args) {
        test(new HashMap<>());
        test(new TreeMap<>());
        test(new LinkedHashMap<>());
    }
}
/* Output:
{sky=blue, ocean=dancing, grass=green, earth=brown, extra=object, tree=tall, sun=warm}
dancing
{earth=brown, extra=object, grass=green, ocean=dancing, sky=blue, sun=warm, tree=tall}
dancing
{sky=blue, grass=green, ocean=dancing, tree=tall, earth=brown, sun=warm, extra=object}
dancing
 */