package exercises.chapter12;

/**
 * Exercise 28
 * Modify Exercise 4 so that the custom exception class
 * inherits from RuntimeException, and show that the
 * compiler allows you to leave out the try block.
 * {ThrowsException}
 */
class MyRuntimeException extends RuntimeException {
    private String msg;

    MyRuntimeException(String msg) {
        this.msg = msg;
    }

    public void printMessage() {
        System.out.println("printMessage() msg: " + msg);
    }
}
public class E28_MyRuntimeExceptionClassTest {
    public static void f() {
        System.out.println("Inside method f()");
        throw new MyRuntimeException("Originated in f()");
    }

    public static void main(String[] args) {
        f();
    }
}
/* Output:
Inside method f()
Exception in thread "main" exercises.chapter12.MyRuntimeException
	at exercises.chapter12.E28_MyRuntimeExceptionClassTest.f(E28_MyRuntimeExceptionClassTest.java:24)
	at exercises.chapter12.E28_MyRuntimeExceptionClassTest.main(E28_MyRuntimeExceptionClassTest.java:28)
 */