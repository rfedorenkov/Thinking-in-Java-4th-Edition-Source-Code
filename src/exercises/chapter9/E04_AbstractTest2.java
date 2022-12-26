package exercises.chapter9;

/**
 * Exercise 4
 * Create an abstract class with no methods.
 * Derive a class and add a method. Create a static
 * method that downcasts a reference from the base
 * class to the derived class and calls the method.
 * Demonstrate that it works in main(). Eliminate
 * the need for the downcast by moving the abstract
 * declaration to the base class.
 */
abstract class NoMethods {
}

class OneMethod extends NoMethods {
    public void f() {
        System.out.println("OneMethod.f()");
    }
}

abstract class OneAbstractMethod {
    abstract public void f();
}

class OneAbstractMethodRelease extends OneAbstractMethod {
    @Override
    public void f() {
        System.out.println("OneAbstractMethodRelease.f()");
    }
}

public class E04_AbstractTest2 {
    public static void test(NoMethods o) {
        ((OneMethod) o).f();
    }

    public static void test2(OneAbstractMethod o) {
        o.f();
    }

    public static void main(String[] args) {
        test(new OneMethod());
        test2(new OneAbstractMethodRelease());
    }
}
/* Output:
OneMethod.f()
OneAbstractMethodRelease.f()
 */