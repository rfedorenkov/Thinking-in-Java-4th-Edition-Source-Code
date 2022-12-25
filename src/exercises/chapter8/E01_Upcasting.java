package exercises.chapter8;

/**
 * Exercise 1
 * Create a Cycle class, with subclasses
 * Unicycle, Bicycle, and Tricycle. Demonstrate
 * that an instance of each type can be upcast
 * to Cycle via ride() method.
 */
class Cycle {
    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}

class Unicycle extends Cycle {

}

class Bicycle extends Cycle {

}

class Tricycle extends Cycle {

}

public class E01_Upcasting {
    static void ride(Cycle c) {
        System.out.println("ride() " + c);
    }

    public static void main(String[] args) {
        ride(new Cycle());
        ride(new Unicycle());
        ride(new Bicycle());
        ride(new Tricycle());
    }
}
/* Output:
ride() Cycle
ride() Unicycle
ride() Bicycle
ride() Tricycle
 */