package exercises.chapter10;

/**
 * Exercise 11
 * Create a private inner class that implements a public interface.
 * Write a method that returns a reference to an instance of the
 * private inner class, upcast to the interface. Show that the inner
 * class is completely hidden by trying to downcast to it.
 */
class Outer5 {
    private class Inner5 implements Flyable {
        @Override
        public void fly() {
            System.out.println("I'm flying!");
        }
    }

    public Flyable getFlyable() {
        return new Inner5();
    }
}

public class E11_PrivateInnerClassTest {
    public static void main(String[] args) {
        Outer5 o = new Outer5();
        Flyable flyable = o.getFlyable();
        flyable.fly();

        // Can't use it here! Out of scope;
        //! flyable = (Inner5) flyable;
        //! Inner5 flyable2 = o.getFlyable();
    }
}
/* Output:
I'm flying!
 */