package exercises.chapter8;

/**
 * Exercise 10
 * Create a base class with two methods. In the first method,
 * call the second method. Create an object of te derived class,
 * upcast it to the base type, and call the first method.
 * Explain what happens.
 */
class A {
    void firstMethod() {
        System.out.println("A.firstMethod()");
        secondMethod();
    }

    void secondMethod() {
        System.out.println("A.secondMethod()");
    }
}

class B extends A {
    @Override
    void secondMethod() {
        System.out.println("B.secondMethod()");
    }
}

public class E10_TwoMethodCall {
    public static void main(String[] args) {
        A b = new B();
        b.firstMethod();
    }
}
/* Output:
A.firstMethod()
B.secondMethod()
 */