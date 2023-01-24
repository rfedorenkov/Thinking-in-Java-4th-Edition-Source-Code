package exercises.chapter12;

import java.util.Random;

/**
 * Exercise 23
 * Add a class with a dispose() method to the previous exercise.
 * Modify FailConstructor so that the constructor creates one
 * of these disposable objects as a member object, after which
 * the constructor might throw an exception, after which it
 * creates a second disposable member object. Write code to
 * properly guard against failure, and in main() verify that
 * all possible failure situations are covered.
 */
class WithDispose {
    private static int counter = 1;
    private final int id = counter++;

    public void dispose() {
        System.out.println("WithDispose " + id + " disposed");
    }
}
class FailingConstructor2 {
    private WithDispose wd1, wd2;
    public FailingConstructor2(boolean b) throws ConstructorException {
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
}

public class E23_FailingConstructorTest2 {
    public static void main(String[] args) {
        Random rand = new Random(47);
        for (int i = 0; i < 5; i++) {
            try {
                System.out.println("Creating...");
                FailingConstructor2 fc = new FailingConstructor2(rand.nextBoolean());
                System.out.println("Processing...");
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
Creating...
Construction failed: Thrown in FailConstructor
WithDispose 4 disposed
Construction failed: Thrown in FailConstructor
Creating...
FailingConstructor created
Processing...
Creating...
FailingConstructor created
Processing...
 */