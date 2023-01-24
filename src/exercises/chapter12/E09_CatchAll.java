package exercises.chapter12;

import java.util.Random;

/**
 * Exercise 9
 * Create three new types of exceptions. Write a class
 * with a method that throws all three. In main(), call
 * the method but only use a single catch clause that will
 * catch all three types of exceptions.
 */
abstract class BaseException extends Exception {
    public BaseException() {
    }

    public BaseException(String msg) {
        super(msg);
    }
}
class SmallException extends BaseException {
    public SmallException() {
    }

    public SmallException(String msg) {
        super(msg);
    }
}

class MediumException extends BaseException {
    public MediumException() {
    }

    public MediumException(String msg) {
        super(msg);
    }
}

class HardException extends BaseException {
    public HardException() {
    }

    public HardException(String msg) {
        super(msg);
    }
}

class ThrowsAll {
    public static void f() throws SmallException, MediumException, HardException {
        Random rand = new Random();
        int i = rand.nextInt(3);
        switch (i) {
            case 0:
                throw new SmallException("Value of " + i);
            case 1:
                throw new MediumException("Value of " + i);
            case 2:
                throw new HardException("Value of " + i);
        }
    }
}

public class E09_CatchAll {
    public static void main(String[] args) {
        try {
            ThrowsAll.f();
        } catch (BaseException e) {
            System.out.println("Caught: " + e);
        }
    }
}
/* Output: (Match: 33%)
Caught: exercises.chapter12.HardException: Value of 2
 */