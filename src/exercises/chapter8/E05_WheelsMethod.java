package exercises.chapter8;

/**
 * Exercise 5
 * Starting from Exercise 1, add a wheels() method
 * in Cycle, which returns the number of wheels.
 * Modify ride() to call wheels() and verify
 * that polymorphism works.
 */
class Cycle2 {
    int wheels() {
        return 0;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}

class Unicycle2 extends Cycle2 {
    @Override
    int wheels() {
        return 1;
    }
}

class Bicycle2 extends Cycle2 {
    @Override
    int wheels() {
        return 2;
    }
}

class Tricycle2 extends Cycle2 {
    @Override
    int wheels() {
        return 3;
    }
}

public class E05_WheelsMethod {
    static void ride(Cycle2 c) {
        System.out.println("ride() " + c.wheels());
    }

    public static void main(String[] args) {
        ride(new Unicycle2());
        ride(new Bicycle2());
        ride(new Tricycle2());
    }
}
/* Output:
ride() 1
ride() 2
ride() 3
 */