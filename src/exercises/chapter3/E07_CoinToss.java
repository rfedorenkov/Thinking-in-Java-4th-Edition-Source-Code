package exercises.chapter3;

import java.util.Random;

/**
 * Exercise 7
 * Write a program that simulates coin-flipping.
 */
public class E07_CoinToss {
    public static void main(String[] args) {
        coinFlip();
        coinFlip();
        coinFlip();
    }

    public static void coinFlip() {
        Random rand = new Random();
        System.out.println("Result: " + (rand.nextBoolean() ? "HEADS" : "TAILS"));
    }
}
/* Output:
Result: HEADS
Result: TAILS
Result: HEADS
 */