package exercises.chapter10;

/**
 * Exercise 15
 * Create a class with a non-default constructor
 * (one with arguments) and no default constructor
 * (no "no-arg" constructor). Create a second class
 * with a method that returns a reference to
 * an object of the first class. Create the object
 * you return by making an anonymous inner class
 * inherit from the first class.
 */
class First {
    private int i;

    public First(int i) {
        this.i = i;
    }

    public void f() {
        System.out.println("First.f(), i = " + i);
    }
}

class Second {
    First getFirst(int i) {
        return new First(i) {
            @Override
            public void f() {
                System.out.println("AnonymousFirst.f(), i = " + i);
            }
        };
    }
}

public class E15_ReturnMakingAnonymousInnerClass {
    public static void main(String[] args) {
        Second second = new Second();
        First first = second.getFirst(47);
        first.f();
    }
}
/* Output:
AnonymousFirst.f(), i = 47
 */