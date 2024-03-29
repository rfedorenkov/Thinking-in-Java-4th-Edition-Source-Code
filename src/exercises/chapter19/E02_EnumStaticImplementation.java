package exercises.chapter19;

import java.util.Random;

/**
 * Exercise 2
 * Instead of implementing an interface, make next()
 * a static method. What are two benefits and drawbacks
 * of this approach?
 */
enum CartoonCharacter {
    SLAPPY, SPANKY, PUNCHY, SILLY, BOUNCY, NUTTY, BOB;

    private static Random rand = new Random(47);

    public static CartoonCharacter next() {
        return values()[rand.nextInt(values().length)];
    }
}

public class E02_EnumStaticImplementation {
    public static void printNext() {
        System.out.print(CartoonCharacter.next() + ", ");
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++)
            printNext();
    }
}
/* Output:
BOB, PUNCHY, BOB, SPANKY, NUTTY, PUNCHY, SLAPPY, NUTTY, NUTTY, SLAPPY,
 */