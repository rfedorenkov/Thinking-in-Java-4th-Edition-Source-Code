package exercises.chapter12;

import java.util.Random;

/**
 * Exercise 22
 * Create a class Called FailingConstructor with a constructor
 * that might fail partway through the construction process
 * and throw an exception. In main(), write code that properly
 * guards against this failure.
 */
class FailingConstructor {
    public FailingConstructor(boolean b) throws ConstructorException {
        if (b)
            throw new ConstructorException("Thrown in FailConstructor");
        System.out.println("FailingConstructor created");
    }
}

public class E22_FailingConstructorTest {
    public static void main(String[] args) {
        Random rand = new Random(47);
        for (int i = 0; i < 5; i++) {
            try {
                System.out.println("Creating...");
                new FailingConstructor(rand.nextBoolean());
            } catch (ConstructorException e) {
                System.out.println("Construction failed: " + e.getMessage());
            }
        }
    }
}
/* Output:
Creating...
Construction failed: Thrown in FailConstructor
Creating...
FailingConstructor created
Creating...
Construction failed: Thrown in FailConstructor
Creating...
FailingConstructor created
Creating...
FailingConstructor created
 */