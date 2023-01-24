package exercises.chapter12;

/**
 * Exercise 21
 * Demonstrate that a derived-class constructor cannot
 * catch exceptions thrown by its base-class constructor.
 */
class ConstructorException extends Exception {
    ConstructorException(String msg) {
        super(msg);
    }
}

class Base {
    public Base() throws ConstructorException {
        throw new ConstructorException("Thrown in Base constructor");
    }
}

class Derived extends Base {
//    public Derived() {
//        try {
//            super();
//        } catch (ConstructorException e) {
//            System.out.println(e);
//        }
//    }

    public Derived() throws ConstructorException {
        super();
    }
}
public class E21_ConstructorExceptions {
    public static void main(String[] args) {
        try {
            Derived derived = new Derived();
        } catch (ConstructorException e) {
            System.out.println("Caught " + e);
        }
    }
}
/* Output:
Caught exercises.chapter12.ConstructorException: Thrown in Base constructor
 */