package exercises.chapter9;

/**
 * Exercise 2
 * Create a class as abstract without including any
 * abstract methods, and verify that you cannot create
 * any instances of that class.
 */
abstract class Abstract {

}

public class E02_AbstractTest {
    public static void main(String[] args) {
        //! new Abstract(); // is abstract, cannot be instantiated
    }
}