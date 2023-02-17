package exercises.chapter15;

import generics.SimpleQueue;
import net.mindview.util.Generator;
import typeinfo.pets.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static net.mindview.util.Print.print;

/**
 * Exercise 41
 * Modify Fill2.java to use the classes in typeinfo.pets
 * instead of the Coffee classes.
 */
interface Addable<T> {
    void add(T t);
}

public class E41_Fill3 {
    // Classtoken version:
    public static <T> void fill(Addable<T> addable,
                                Class<? extends T> classToken, int size) {
        for (int i = 0; i < size; i++) {
            try {
                addable.add(classToken.newInstance());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    // Generator version:
    public static <T> void fill(Addable<T> addable,
                                Generator<T> generator, int size) {
        for (int i = 0; i < size; i++)
            addable.add(generator.next());
    }
}

// To adapt a base type, you must use composition.
// Make any Collection Addable using composition:
class AddableCollectionAdapter<T> implements Addable<T> {
    private Collection<T> c;

    public AddableCollectionAdapter(Collection<T> c) {
        this.c = c;
    }

    @Override
    public void add(T item) {
        c.add(item);
    }
}

// A Helper to capture the type automatically:
class Adapter {
    public static <T> Addable<T> collectionAdapter(Collection<T> c) {
        return new AddableCollectionAdapter<>(c);
    }
}

// To adapt a specific type, you can use inheritance.
// Make a SimpleQueue Addable using inheritance:
class AddableSimpleQueue<T> extends SimpleQueue<T> implements Addable<T> {
    @Override
    public void add(T item) {
        super.add(item);
    }
}

class Fill3Test {
    public static void main(String[] args) {
        // Adapt a Collection:
        List<Pet> pets = new ArrayList<>();
        E41_Fill3.fill(new AddableCollectionAdapter<>(pets), Dog.class, 3);
        // Helper method captures the type:
        E41_Fill3.fill(Adapter.collectionAdapter(pets), Cat.class, 2);
        for (Pet p : pets)
            print(p);
        print("----------------------");
        // Use an adapted class:
        AddableSimpleQueue<Pet> petsQueue = new AddableSimpleQueue<>();
        E41_Fill3.fill(petsQueue, Mouse.class, 4);
        E41_Fill3.fill(petsQueue, Manx.class, 1);
        for (Pet p : petsQueue)
            print(p);
    }
}
/* Output:
Dog
Dog
Dog
Cat
Cat
----------------------
Mouse
Mouse
Mouse
Mouse
Manx
 */