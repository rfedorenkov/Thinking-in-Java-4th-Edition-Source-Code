package exercises.chapter12;

/**
 * Exercise 10
 * Create a class with two methods, f() and g().
 * In g(), throw an exception of a new type that
 * you define. In f(), call g(), catch its exception
 * and, in the catch clause, throw a different exception
 * (of a second type that you define). Test your code in main().
 */
class MainException extends Exception {
}

class OtherException extends Exception {
}

class ChangeException {
    void f() throws OtherException {
        try {
            g();
        } catch (MainException e) {
            throw new OtherException();
        }
    }

    void g() throws MainException {
        throw new MainException();
    }
}
public class E10_ChangeExceptionTest {
    public static void main(String[] args) {
        ChangeException ce = new ChangeException();
        try {
            ce.f();
        } catch (OtherException e) {
            System.out.println("Caught: " + e);
        }
    }
}
/* Output:
Caught: exercises.chapter12.OtherException
 */