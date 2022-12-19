package exercises.chapter7;

/**
 * Exercise 8
 * Create a base class with only a non-default constructor,
 * and a derived class with both a default (no-arg) and
 * non-default constructor.
 */
class Base {
    private int i;

    Base(int i) {
        this.i = i;
        System.out.println("Base(" + i + ")");
    }
}

class Derived extends Base {

    Derived() {
        super(47);
        System.out.println("Derived()");
    }

    Derived(int i) {
        super(i);
        System.out.println("Derived(" + i + ")");
    }
}

public class E08_CallBaseConstructor {
    public static void main(String[] args) {
        Derived derived1 = new Derived(123);
        Derived derived2 = new Derived();
    }
}
/* Output:
Base(123)
Derived(123)
Base(47)
Derived()
 */