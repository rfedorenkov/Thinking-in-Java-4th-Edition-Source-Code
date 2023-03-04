package exercises.chapter18;

import net.mindview.util.BinaryFile;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

import static net.mindview.util.Print.print;

/**
 * Exercise 19
 * Using BinaryFile and a Map<Byte, Integer>, create a program
 * that counts the occurrence of all the different bytes in a file.
 */
public class E19_BytesInfo {
    public static void main(String[] args) throws IOException {
        String fileName = "src/exercises/chapter18/E19_BytesInfo.java";
        Map<Byte, Integer> map = new TreeMap<>();
        for (byte b : BinaryFile.read(fileName)) {
            Integer value = map.get(b);
            map.put(b, value == null ? 1 : value + 1);
        }
        for (Map.Entry<Byte, Integer> entry : map.entrySet())
            print(entry.getKey() + " = " + entry.getValue());
    }
}
/* Output:
10 = 26
32 = 164
34 = 4
40 = 11
41 = 11
42 = 6
43 = 3
44 = 5
46 = 23
47 = 5
49 = 7
56 = 2
57 = 3
58 = 3
59 = 11
60 = 4
61 = 6
62 = 4
63 = 1
66 = 8
69 = 6
70 = 3
73 = 8
75 = 1
77 = 6
78 = 2
79 = 2
80 = 1
83 = 3
84 = 2
85 = 1
86 = 1
91 = 1
93 = 1
95 = 2
97 = 45
98 = 7
99 = 19
100 = 6
101 = 67
102 = 10
103 = 13
104 = 6
105 = 42
106 = 4
107 = 1
108 = 21
109 = 15
110 = 34
111 = 18
112 = 26
114 = 41
115 = 17
116 = 53
117 = 14
118 = 10
119 = 4
120 = 5
121 = 16
123 = 3
125 = 3
 */