package exercises.chapter15;

import generics.Generators;
import net.mindview.util.Generator;

import java.util.*;

/**
 * Exercise 18
 * Following the form of BankTeller.java, create an example
 * where BigFish eat LittleFish in the Ocean.
 */
class LittleFish {
    private static long counter = 1;
    private final long id = counter++;

    private LittleFish() {
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " " + id;
    }

    public static Generator<LittleFish> generator() {
        return new Generator<LittleFish>() {
            @Override
            public LittleFish next() {
                return new LittleFish();
            }
        };
    }
}

class BigFish {
    private static long counter = 1;
    private final long id = counter++;

    private BigFish() {
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " " + id;
    }

    public static Generator<BigFish> generator = new Generator<BigFish>() {
        @Override
        public BigFish next() {
            return new BigFish();
        }
    };
}


public class E18_OceanLife {
    public static void eat(BigFish bf, LittleFish lf) {
        System.out.println(bf + " eat " + lf);
    }

    public static void main(String[] args) {
        Random rand = new Random(47);
        Queue<LittleFish> littleFishQueue = new LinkedList<>();
        Generators.fill(littleFishQueue, LittleFish.generator(), 15);
        List<BigFish> bigFishList = new ArrayList<>();
        Generators.fill(bigFishList, BigFish.generator, 4);
        for (LittleFish littleFish : littleFishQueue)
            eat(bigFishList.get(rand.nextInt(bigFishList.size())), littleFish);
    }
}
/* Output:
BigFish 3 eat LittleFish 1
BigFish 2 eat LittleFish 2
BigFish 3 eat LittleFish 3
BigFish 1 eat LittleFish 4
BigFish 1 eat LittleFish 5
BigFish 3 eat LittleFish 6
BigFish 1 eat LittleFish 7
BigFish 2 eat LittleFish 8
BigFish 3 eat LittleFish 9
BigFish 3 eat LittleFish 10
BigFish 2 eat LittleFish 11
BigFish 4 eat LittleFish 12
BigFish 2 eat LittleFish 13
BigFish 1 eat LittleFish 14
BigFish 1 eat LittleFish 15
 */