package exercises.chapter3;

import static net.mindview.util.Print.print;

/**
 * Exercise 8
 * Show that hex and octal notation work with long values.
 * Use Long.toBinaryString to display the results.
 */
public class E08_LongValues {
    public static void main(String[] args) {
        long l1 = 0x2f; // Hexadecimal (lowercase)
        print("l1: " + Long.toBinaryString(l1));
        long l2 = 0x2F; // Hexadecimal (uppercase)
        print("l2: " + Long.toBinaryString(l2));
        long l3 = 0177; // Octal (leading zero)
        print("l3: " + Long.toBinaryString(l3));

    }
}
/* Output:
l1: 101111
l2: 101111
l3: 1111111
 */