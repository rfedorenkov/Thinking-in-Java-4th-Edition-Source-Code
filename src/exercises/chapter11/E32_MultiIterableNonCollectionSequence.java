package exercises.chapter11;

import holding.InterfaceVsIterator;
import typeinfo.pets.Pet;
import typeinfo.pets.Pets;

import java.util.*;

/**
 * Exercise 32
 * Following the example to MultiIterableClass,
 * add reversed() and randomized() methods to
 * NonCollectionSequence.java, as well as making
 * NonCollectionSequence implement Iterable, and
 * show that all the approaches work in foreach
 * statements.
 */
class PetSequence {
    protected Pet[] pets = Pets.createArray(8);
}

class NonCollectionSequence extends PetSequence implements Iterable<Pet> {
    @Override
    public Iterator<Pet> iterator() {
        return new Iterator<Pet>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < pets.length;
            }

            @Override
            public Pet next() {
                return pets[index++];
            }

            @Override
            public void remove() { // Not implemented
                throw new UnsupportedOperationException();
            }
        };
    }

    public Iterable<Pet> reversed() {
        return new Iterable<Pet>() {
            @Override
            public Iterator<Pet> iterator() {
                return new Iterator<Pet>() {
                    private int index = pets.length - 1;

                    @Override
                    public boolean hasNext() {
                        return index > -1;
                    }

                    @Override
                    public Pet next() {
                        return pets[index--];
                    }

                    @Override
                    public void remove() { // Not implemented
                        throw new UnsupportedOperationException();
                    }
                };
            }
        };
    }

    public Iterable<Pet> randomized() {
        return new Iterable<Pet>() {
            @Override
            public Iterator<Pet> iterator() {
                List<Pet> list = new ArrayList<>(Arrays.asList(pets));
                Collections.shuffle(list, new Random(47));
                return list.iterator();
            }
        };
    }
}

public class E32_MultiIterableNonCollectionSequence {
    public static void main(String[] args) {
        NonCollectionSequence nc = new NonCollectionSequence();
        for (Pet p : nc)
            System.out.print(p + " ");
        System.out.println();
        for (Pet p : nc.reversed())
            System.out.print(p + " ");
        System.out.println();
        for (Pet p : nc.randomized())
            System.out.print(p + " ");
        System.out.println();
    }
}
/* Output:
Rat Manx Cymric Mutt Pug Cymric Pug Manx
Manx Pug Cymric Pug Mutt Cymric Manx Rat
Pug Mutt Pug Rat Manx Manx Cymric Cymric
 */