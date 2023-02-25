package exercises.chapter17;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static net.mindview.util.Print.print;

/**
 * Exercise 27
 * Modify the hashCode() in CountedString.java by removing
 * the combination with id, and demonstrate that CountedString
 * still works as a key. What is the problem with this approach?
 */
class CountedString3 {
    private static List<String> created = new ArrayList<>();
    private String s;
    private int id = 0;

    public CountedString3(String str) {
        s = str;
        created.add(str);
        // id is the total number of instances
        // of this string in use by CountedString:
        for (String s2 : created)
            if (s2.equals(s))
                id++;
    }

    @Override
    public String toString() {
        return "String: " + s + " id: " + id +
                " hashCode(): " + hashCode();
    }

    @Override
    public int hashCode() {
        // The very simple approach:
        // return s.hashCode() * id;
        // Using Joshua Bloch's recipe:
        int result = 17;
        result = 37 * result + s.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof CountedString3 &&
                s.equals(((CountedString3) o).s) &&
                id == ((CountedString3) o).id;
    }
}

public class E27_CountedStringModifyHashCode {
    public static void main(String[] args) {
        Map<CountedString3, Integer> map = new HashMap<>();
        CountedString3[] cs = new CountedString3[5];
        for (int i = 0; i < cs.length; i++) {
            cs[i] = new CountedString3("hi");
            map.put(cs[i], i); // Autobox int -> Integer
        }
        print(map);
        for (CountedString3 cstring : cs) {
            print("Looking up " + cstring);
            print(map.get(cstring));
        }
    }
}
/* Output:
{String: hi id: 1 hashCode(): 3958=0, String: hi id: 2 hashCode(): 3958=1, String: hi id: 3 hashCode(): 3958=2, String: hi id: 4 hashCode(): 3958=3, String: hi id: 5 hashCode(): 3958=4}
Looking up String: hi id: 1 hashCode(): 3958
0
Looking up String: hi id: 2 hashCode(): 3958
1
Looking up String: hi id: 3 hashCode(): 3958
2
Looking up String: hi id: 4 hashCode(): 3958
3
Looking up String: hi id: 5 hashCode(): 3958
4
 */