package exercises.chapter4;

import java.util.Random;

/**
 * Exercise 3
 * Modify exercise 2 so your code is surrounded by an "infinity" while loop.
 * It will then run until you interrupt it, typically with Ctrl-C.
 */
public class E03_CompareInts2 {
    static void compare(int x, int y) {
        if (x < y)
            System.out.println(x + " < " + y);
        else if (x > y)
            System.out.println(x + " > " + y);
        else
            System.out.println(x + " = " + y);
    }

    public static void main(String[] args) {
        Random rand = new Random(47);
        while (true) {
            int x = rand.nextInt(20);
            int y = rand.nextInt(20);
            compare(x, y);
        }
    }
}
/* Output:
Execute to see output
 */