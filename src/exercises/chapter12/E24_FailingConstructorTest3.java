package exercises.chapter12;

import java.util.Random;

/**
 * Exercise 24
 * Add a dispose() method to the FailingConstructor class
 * and write code to properly use this class.
 */
class FailingConstructor3 {
    private WithDispose wd1, wd2;
    public FailingConstructor3(boolean b) throws ConstructorException {
        wd1 = new WithDispose();
        try {
            if (b)
                throw new ConstructorException("Thrown in FailConstructor");
        } catch (ConstructorException e) {
            System.out.println("Construction failed: " + e.getMessage());
            wd1.dispose();
            throw e;
        }
        wd2 = new WithDispose();
        System.out.println("FailingConstructor created");
    }

    public void dispose() {
        wd2.dispose();
        wd1.dispose();
    }
}

public class E24_FailingConstructorTest3 {
    public static void main(String[] args) {
        Random rand = new Random(47);
        for (int i = 0; i < 5; i++) {
            try {
                System.out.println("Creating...");
                FailingConstructor3 fc = new FailingConstructor3(rand.nextBoolean());
                try {
                    System.out.println("Processing...");
                } finally {
                    fc.dispose();
                }
            } catch (ConstructorException e) {
                System.out.println("Construction failed: " + e.getMessage());
            }
        }
    }
}
/* Output:
Creating...
Construction failed: Thrown in FailConstructor
WithDispose 1 disposed
Construction failed: Thrown in FailConstructor
Creating...
FailingConstructor created
Processing...
WithDispose 3 disposed
WithDispose 2 disposed
Creating...
Construction failed: Thrown in FailConstructor
WithDispose 4 disposed
Construction failed: Thrown in FailConstructor
Creating...
FailingConstructor created
Processing...
WithDispose 6 disposed
WithDispose 5 disposed
Creating...
FailingConstructor created
Processing...
WithDispose 8 disposed
WithDispose 7 disposed
 */