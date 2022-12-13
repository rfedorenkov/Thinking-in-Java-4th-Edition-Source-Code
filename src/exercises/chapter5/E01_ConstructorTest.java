package exercises.chapter5;

/**
 * Exercise 1
 * Create a class with an uninitialized String reference.
 * Demonstrate that this reference is initialized by Java to null.
 */
class Test {
    String s;
}

public class E01_ConstructorTest {
    public static void main(String[] args) {
        Test test = new Test();
        System.out.println("s = " + test.s);
    }
}
/* Output:
s = null
 */