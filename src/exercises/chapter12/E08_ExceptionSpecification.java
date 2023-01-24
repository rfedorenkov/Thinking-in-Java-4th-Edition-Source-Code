package exercises.chapter12;

/**
 * Exercise 8
 * Write a class with a method that throws an exception
 * of the type created in Exercise 4. Try compiling it
 * without an exception specification to see what the
 * compiler says. Add appropriate exception specification.
 * Try out your class add its exception inside a try-catch clause.
 */
class ThrowsExceptionTest {
    public void f() {
        // unreported exception exercises.chapter12.MyException; must be caught or declared to be thrown
        //! throw new MyException("Originated in f()");
    }

    public void g() throws MyRuntimeException {
        throw new MyRuntimeException("Originated in g()");
    }
}

public class E08_ExceptionSpecification {
    public static void main(String[] args) {
        ThrowsExceptionTest test = new ThrowsExceptionTest();
        //! test.f();
        try {
            test.g();
        } catch (MyRuntimeException e) {
            e.printMessage();
        }
    }
}
/* Output:
printMessage() msg: Originated in g()
 */