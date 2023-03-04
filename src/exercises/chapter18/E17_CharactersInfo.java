package exercises.chapter18;

import net.mindview.util.TextFile;

import java.util.Map;
import java.util.TreeMap;

import static net.mindview.util.Print.print;

/**
 * Exercise 17
 * Using TextFile and a Map<Character, Integer>, create
 * a program that counts the occurrence of all the different
 * characters in a file. (So if there are 12 occurrences
 * of the letter 'a' in the file, the Integer associated
 * with the Character containing 'a' in the Map contains '12').
 */
public class E17_CharactersInfo {
    public static void main(String[] args) {
        String fileName = "src/exercises/chapter18/E17_CharactersInfo.java";
        Map<Character, Integer> map = new TreeMap<>();
        for (String string : new TextFile(fileName, "\\W+")) {
            for (char c : string.toCharArray()) {
                Integer value = map.get(c);
                map.put(c, value == null ? 1 : value + 1);
            }
        }
        for (Map.Entry<Character, Integer> entry : map.entrySet())
            print(entry.getKey() + " = " + entry.getValue());
    }
}
/* Output:
1 = 9
2 = 2
7 = 3
8 = 2
A = 1
C = 7
E = 4
F = 3
I = 6
K = 1
M = 7
N = 2
P = 1
S = 5
T = 5
U = 1
V = 1
W = 1
_ = 2
a = 62
b = 2
c = 36
d = 6
e = 80
f = 14
g = 18
h = 21
i = 47
j = 3
k = 1
l = 20
m = 15
n = 41
o = 21
p = 25
r = 62
s = 22
t = 69
u = 12
v = 6
w = 5
x = 6
y = 7
 */