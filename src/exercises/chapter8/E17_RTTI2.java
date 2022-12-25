package exercises.chapter8;

/**
 * Exercise 17
 * Add a balance() method to Unicycle and Bicycle
 * but not to Tricycle, using the Cycle hierarchy
 * from Exercise 1. Upcast instances of all three
 * types to an array of Cycle. Try to call balance()
 * on each element to the array and observe the results.
 * Downcast and call balance() and observe what happens.
 */
class Cycle3 {
    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}

class Unicycle3 extends Cycle3 {
    public void balance() {
        System.out.println("Unicycle.balance()");
    }
}

class Bicycle3 extends Cycle3 {
    public void balance() {
        System.out.println("Bicycle.balance()");
    }
}

class Tricycle3 extends Cycle3 {

}

public class E17_RTTI2 {
    public static void main(String[] args) {
        Cycle3[] cycles = {
                new Unicycle3(),
                new Bicycle3(),
                new Tricycle3()
        };
        // Compile time: method not found in Cycle:
        //! cycles[0].balance();
        //! cycles[1].balance();
        //! cycles[2].balance();

        ((Unicycle3) cycles[0]).balance(); // Downcast/RTTI
        ((Bicycle3) cycles[1]).balance(); // Downcast/RTTI
        ((Unicycle3) cycles[2]).balance(); // ClassCastException
    }
}
/* Output:
Unicycle.balance()
Bicycle.balance()
Exception in thread "main" java.lang.ClassCastException:
 */