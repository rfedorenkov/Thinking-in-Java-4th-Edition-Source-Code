package exercises.chapter12;

/**
 * Exercise 4
 * Create your own exception class using the extends keyword.
 * Write a constructor for this class that takes a String
 * argument and stores it inside the object with a String
 * reference. Write a method that prints out the stored
 * String. Create a try-catch clause to exercise your new exception.
 */
class MyException extends Exception {
    private String msg;

    MyException(String msg) {
        this.msg = msg;
    }

    public void printMessage() {
        System.out.println("printMessage() msg: " + msg);
    }
}

public class E04_MyExceptionClassTest {
    public static void f() throws MyRuntimeException {
        System.out.println("Inside method f()");
        throw new MyRuntimeException("Originated in f()");
    }

    public static void main(String[] args) {
        try {
            f();
        } catch (MyRuntimeException e) {
            e.printMessage();
        }
    }
}
/* Output:
Inside method f()
printMessage() msg: Originated in f()
 */