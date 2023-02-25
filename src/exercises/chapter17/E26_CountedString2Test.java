package exercises.chapter17;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static net.mindview.util.Print.print;

/**
 * Exercise 26
 * Add a char field to CountedString that is also initialized
 * in the constructor, and modify the hashCode() and equals()
 * methods to include the value of this char.
 */
class CountedString2 {
    private static List<String> created = new ArrayList<>();
    private String s;
    private int id = 0;
    private char c;

    public CountedString2(String str, char c) {
        s = str;
        this.c = c;
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
                " char: " + c + " hashCode(): " + hashCode();
    }

    @Override
    public int hashCode() {
        // The very simple approach:
        // return s.hashCode() * id;
        // Using Joshua Bloch's recipe:
        int result = 17;
        result = 37 * result + s.hashCode();
        result = 37 * result + id;
        result = 37 * result + c;
        return result;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof CountedString2 &&
                s.equals(((CountedString2) o).s) &&
                id == ((CountedString2) o).id &&
                c == ((CountedString2) o).c;
    }
}

public class E26_CountedString2Test {
    public static void main(String[] args) {
        Map<CountedString2, Integer> map = new HashMap<>();
        CountedString2[] cs = new CountedString2[5];
        for (int i = 0; i < cs.length; i++) {
            cs[i] = new CountedString2("hi", 'F');
            map.put(cs[i], i); // Autobox int -> Integer
        }
        print(map);
        for (CountedString2 cstring : cs) {
            print("Looking up " + cstring);
            print(map.get(cstring));
        }
    }
}
/* Output: (Sample)
{String: hi id: 4 char: F hashCode(): 5418720=3, String: hi id: 1 char: F hashCode(): 5418609=0, String: hi id: 2 char: F hashCode(): 5418646=1, String: hi id: 5 char: F hashCode(): 5418757=4, String: hi id: 3 char: F hashCode(): 5418683=2}
Looking up String: hi id: 1 char: F hashCode(): 5418609
0
Looking up String: hi id: 2 char: F hashCode(): 5418646
1
Looking up String: hi id: 3 char: F hashCode(): 5418683
2
Looking up String: hi id: 4 char: F hashCode(): 5418720
3
Looking up String: hi id: 5 char: F hashCode(): 5418757
4
 */