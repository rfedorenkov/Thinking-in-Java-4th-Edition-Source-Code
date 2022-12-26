package exercises.chapter9;

import java.util.Random;

/**
 * Exercise 19
 * Create a framework using Factory Methods that
 * performs both coin tossing and dice tossing.
 */
interface Tossing {
    boolean move();
}

interface TossingFactory {
    Tossing getTossing();
}

class CoinToss implements Tossing {
    private int moves = 0;
    private static final int MOVES = 5;
    private static Random rand = new Random(47);
    CoinToss() {} // Package access

    @Override
    public boolean move() {
        //Heads and Tails
        System.out.println("Coin flip #" + moves + ": " + flip());
        return ++moves != MOVES;
    }

    private String flip() {
        return rand.nextBoolean() ? "Heads" : "Tails";
    }
}

class CoinTossFactory implements TossingFactory {
    @Override
    public Tossing getTossing() {
        return new CoinToss();
    }
}

class DiceToss implements Tossing {
    private int moves = 0;
    private static final int MOVES = 5;
    private static Random rand = new Random(47);

    DiceToss() {} // Package access

    @Override
    public boolean move() {
        System.out.println("Dice roll #" + moves + ": " + roll());
        return ++moves != MOVES;
    }

    private int roll() {
        return rand.nextInt(6) + 1;
    }
}

class DiceTossFactory implements TossingFactory {

    @Override
    public Tossing getTossing() {
        return new DiceToss();
    }
}

public class E19_TossingFramework {
    public static void playGame(TossingFactory factory) {
        Tossing tossing = factory.getTossing();
        while (tossing.move());
    }

    public static void main(String[] args) {
        playGame(new CoinTossFactory());
        playGame(new DiceTossFactory());
    }
}
/* Output:
Coin flip #0: Heads
Coin flip #1: Tails
Coin flip #2: Heads
Coin flip #3: Tails
Coin flip #4: Tails
Dice roll #0: 3
Dice roll #1: 6
Dice roll #2: 2
Dice roll #3: 6
Dice roll #4: 2
 */