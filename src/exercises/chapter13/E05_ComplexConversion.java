package exercises.chapter13;

import java.math.BigInteger;
import java.util.Formatter;
import java.util.Locale;

/**
 * Exercise 5
 * For each of the basic conversion types in the above table,
 * write the most complex formatting expression possible.
 * That is, use all the possible format specifiers available
 * for that conversion type.
 */
public class E05_ComplexConversion {
    public static void main(String[] args) {
        Formatter f = new Formatter(System.out, Locale.ENGLISH);
        char u = 'a';
        System.out.println("u = 'a'");
        f.format("c: %1$10c\n", u);
        f.format("b: %-10b\n", u);
        f.format("h: %10h\n", u);

        int v = 121;
        System.out.println("v = 121");
        f.format("s: %1$d\n", v);
        f.format("d: %10d\n", v);
        f.format("c: %-10c\n", v);
        f.format("b: %10b\n", v);
        f.format("x: %#x\n", v);
        f.format("h: %-10h\n", v);

        BigInteger w = new BigInteger("50000000000000");
        System.out.println("w = new BigInteger(\"50000000000000\")");
        f.format("s: %10.4s\n", w);
        f.format("d: %-10d\n", w);
        f.format("b: %b\n", w);
        f.format("x: %x\n", w);
        f.format("h: %10h\n", w);

        double x = 179.543;
        System.out.println("x = 179.543");
        f.format("s: %10.6s\n", x);
        f.format("b: %B\n", x);
        f.format("f: %#.5f\n", x);
        f.format("e: %-8.2e\n", x);
        f.format("h: %H\n", x);

        Object y = new Object();
        System.out.println("y = new Object()");
        f.format("s: %S\n", y);
        f.format("b: %2B\n", y);
        f.format("h: %10h\n", y);

        boolean z = false;
        System.out.println("z = false");
        f.format("s: %8S\n", z);
        f.format("b: %.4b\n", z);
        f.format("h: %-2h\n", z);
    }
}
/* Output:
u = 'a'
c:          a
b: true
h:         61
v = 121
s: 121
d:        121
c: y
b:       true
x: 0x79
h: 79
w = new BigInteger("50000000000000")
s:       5000
d: 50000000000000
b: true
x: 2d79883d2000
h:   8842a1a7
x = 179.543
s:     179.54
b: TRUE
f: 179.54300
e: 1.80e+02
h: 1EF462C
y = new Object()
s: JAVA.LANG.OBJECT@59E84876
b: TRUE
h:   59e84876
z = false
s:    FALSE
b: fals
h: 4d5
 */