package exercises.chapter11;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static net.mindview.util.Print.printnb;

/**
 * Exercise 17
 * Move the Gerbil class from Exercise 1 into a Map,
 * and associate each Gerbil (the value) with it's name
 * as a String (the key). Use an Iterator for the keySet()
 * to move through the Map, look up the Gerbil for each key,
 * print out the key, and tell the Gerbil to hop().
 */
public class E17_GerbilMap {
    public static Map<String, Gerbil> gerbilMap = new HashMap<>();

    static {
        gerbilMap.put("Dawn", new Gerbil(1));
        gerbilMap.put("Molly", new Gerbil(2));
        gerbilMap.put("Spot", new Gerbil(3));
        gerbilMap.put("Kate", new Gerbil(4));
        gerbilMap.put("Shackleton", new Gerbil(5));
    }

    public static void main(String[] args) {
        Iterator<String> it = gerbilMap.keySet().iterator();
        while (it.hasNext()) {
            String name = it.next();
            printnb(name + ": ");
            gerbilMap.get(name).hop();
        }
    }
}
/* Output:
Dawn: Gerbil 1 is hopping
Kate: Gerbil 4 is hopping
Spot: Gerbil 3 is hopping
Molly: Gerbil 2 is hopping
Shackleton: Gerbil 5 is hopping
 */