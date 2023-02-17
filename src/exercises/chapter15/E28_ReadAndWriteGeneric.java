package exercises.chapter15;

import typeinfo.pets.*;

/**
 * Exercise 28
 * Create a generic class Generic1<T> with a single method
 * that takes an argument of type T. Create a second generic
 * class Generic2<T> with a single method that returns an argument
 * of type T. Write a generic method with a contravariant argument
 * of the first generic class that calls its method. Write a second
 * generic method with a covariant argument of the second generic
 * class that calls its method. Test using the typeinfo.pets library.
 */
class Generic1<T> {
    T item;

    public void add(T t) {
        item = t;
    }
}

class Generic2<T> {
    T item;

    public T get() {
        return item;
    }
}

public class E28_ReadAndWriteGeneric {
    static <T> void add(Generic1<? super T> generic, T item) {
        generic.add(item);
    }

    static <T> T get(Generic2<? extends T> generic) {
        return generic.get();
    }

    public static void main(String[] args) {
        Generic1<Cat> pet1 = new Generic1<>();
        add(pet1, new Cat()); // OK
        add(pet1, new Manx()); // OK
        // add(pet1, new Pet()); // Error
        Generic2<Cat> pet2 = new Generic2<>();
        Cat cat = get(pet2); // OK
        Pet pet = get(pet2); // OK
    }
}