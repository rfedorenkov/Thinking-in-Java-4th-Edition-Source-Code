package exercises.chapter3;

import static net.mindview.util.Print.print;

/**
 * Exercise 13
 * Write a method that displays char values in binary form.
 * Demonstrate it using several different characters.
 */
public class E13_BinaryChar {
    public static void main(String[] args) {
        for (char c = 'a'; c <= 'z'; c++) {
            printBinaryChar(c);
        }
    }

    static void printBinaryChar(char c) {
        print("char: " + c + ", binary: " + Integer.toBinaryString(c));
    }
}
/* Output:
char: a, binary: 1100001
char: b, binary: 1100010
char: c, binary: 1100011
char: d, binary: 1100100
char: e, binary: 1100101
char: f, binary: 1100110
char: g, binary: 1100111
char: h, binary: 1101000
char: i, binary: 1101001
char: j, binary: 1101010
char: k, binary: 1101011
char: l, binary: 1101100
char: m, binary: 1101101
char: n, binary: 1101110
char: o, binary: 1101111
char: p, binary: 1110000
char: q, binary: 1110001
char: r, binary: 1110010
char: s, binary: 1110011
char: t, binary: 1110100
char: u, binary: 1110101
char: v, binary: 1110110
char: w, binary: 1110111
char: x, binary: 1111000
char: y, binary: 1111001
char: z, binary: 1111010
 */