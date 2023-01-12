package exercises.chapter10;

import java.util.Random;

/**
 * Exercise 17
 * Use anonymous inner classes to modify the solution
 * to Exercise 19 from Interfaces chapter.
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

    public static TossingFactory factory = new TossingFactory() {
        @Override
        public Tossing getTossing() {
            return new CoinToss();
        }
    };
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

    public static TossingFactory factory = new TossingFactory() {
        @Override
        public Tossing getTossing() {
            return new DiceToss();
        }
    };
}

public class E17_AnonymousTossingFramework {
    public static void playGame(TossingFactory factory) {
        Tossing tossing = factory.getTossing();
        while (tossing.move());
    }

    public static void main(String[] args) {
        playGame(CoinToss.factory);
        playGame(DiceToss.factory);
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