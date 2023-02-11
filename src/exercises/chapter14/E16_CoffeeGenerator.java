package exercises.chapter14;

import net.mindview.util.Generator;
import typeinfo.pets.factory.Factory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * Exercise 16
 * Modify the Coffee hierarchy in the Generics
 * chapter to use Registered Factories.
 */
class Coffee {
    private static long counter = 0;
    private final long id = counter++;

    @Override
    public String toString() {
        return getClass().getSimpleName() + " " + id;
    }

    static List<Factory<? extends Coffee>> coffeeFactories = new ArrayList<>();

    static {
        coffeeFactories.add(new Americano.Factory());
        coffeeFactories.add(new Breve.Factory());
        coffeeFactories.add(new Cappuccino.Factory());
        coffeeFactories.add(new Latte.Factory());
        coffeeFactories.add(new Mocha.Factory());
    }

    private static Random rand = new Random(47);

    public static Coffee randomCoffee() {
        int n = rand.nextInt(coffeeFactories.size());
        return coffeeFactories.get(n).create();
    }
}

class Americano extends Coffee {
    public static class Factory implements typeinfo.pets.factory.Factory<Americano> {
        @Override
        public Americano create() {
            return new Americano();
        }
    }
}

class Breve extends Coffee {
    public static class Factory implements typeinfo.pets.factory.Factory<Breve> {
        @Override
        public Breve create() {
            return new Breve();
        }
    }
}

class Cappuccino extends Coffee {
    public static class Factory implements typeinfo.pets.factory.Factory<Cappuccino> {
        @Override
        public Cappuccino create() {
            return new Cappuccino();
        }
    }
}

class Latte extends Coffee {
    public static class Factory implements typeinfo.pets.factory.Factory<Latte> {
        @Override
        public Latte create() {
            return new Latte();
        }
    }
}

class Mocha extends Coffee {
    public static class Factory implements typeinfo.pets.factory.Factory<Mocha> {
        @Override
        public Mocha create() {
            return new Mocha();
        }
    }
}

public class E16_CoffeeGenerator implements Generator<Coffee>, Iterable<Coffee> {
    private static Random rand = new Random(47);

    public E16_CoffeeGenerator() {
    }

    // For iteration:
    private int size = 0;

    public E16_CoffeeGenerator(int sz) {
        size = sz;
    }

    @Override
    public Coffee next() {
        return Coffee.randomCoffee();
    }

    class CoffeeIterator implements Iterator<Coffee> {
        int count = size;

        @Override
        public boolean hasNext() {
            return count > 0;
        }

        @Override
        public Coffee next() {
            count--;
            return E16_CoffeeGenerator.this.next();
        }

        @Override
        public void remove() { // Not implemented
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public Iterator<Coffee> iterator() {
        return new CoffeeIterator();
    }

    public static void main(String[] args) {
        E16_CoffeeGenerator gen = new E16_CoffeeGenerator();
        for (int i = 0; i < 5; i++)
            System.out.println(gen.next());
        for (Coffee c : new E16_CoffeeGenerator(5))
            System.out.println(c);
    }
}
/* Output:
Latte 0
Americano 1
Latte 2
Breve 3
Breve 4
Mocha 5
Latte 6
Americano 7
Cappuccino 8
Cappuccino 9
 */