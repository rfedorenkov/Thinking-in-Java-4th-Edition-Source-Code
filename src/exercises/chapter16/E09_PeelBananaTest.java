package exercises.chapter16;

import java.util.ArrayList;
import java.util.List;

/**
 * Exercise 9
 * Create the classes necessary for the Peel<Banana> example
 * and show that the compiler doesn't accept it. Fix the problem
 * using an ArrayList.
 */
class Banana {
    private static int counter = 0;
    private final int id = counter++;

    @Override
    public String toString() {
        return getClass().getSimpleName() + " #" + id;
    }
}

class Peel<T> {
    T t;

    Peel(T t) {
        this.t = t;
    }

    @Override
    public String toString() {
        return "Peeling " + t;
    }
}

public class E09_PeelBananaTest {
    public static void main(String[] args) {
        // Compile error
        // Peel<Banana> arr = new Peel<>[10];
        List<Peel<Banana>> list = new ArrayList<>();
        for (int i = 0; i < 5; i++)
            list.add(new Peel<>(new Banana()));
        for (Peel<Banana> peel : list)
            System.out.println(peel);
    }
}
/* Output:
Peeling Banana #0
Peeling Banana #1
Peeling Banana #2
Peeling Banana #3
Peeling Banana #4
 */