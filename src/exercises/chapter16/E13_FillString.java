package exercises.chapter16;

import net.mindview.util.CountingGenerator;

/**
 * Exercise 13
 * Fill a String using CountingGenerator.Character.
 */
public class E13_FillString {
    public static void main(String[] args) {
        String s = new CountingGenerator.String(10).next();
        System.out.println(s);
    }
}
/* Output:
abcdefghij
 */