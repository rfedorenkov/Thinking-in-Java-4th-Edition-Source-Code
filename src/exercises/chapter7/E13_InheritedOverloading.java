package exercises.chapter7;

/**
 * Exercise 13
 * Create a class with a method that is overloaded three times.
 * Inherit a new class, add a new overloading of the method,
 * and show that all four methods are available in the derived class.
 */
class ThreeOverloads {
    void method() {
        System.out.println("method()");
    }

    void method(int i) {
        System.out.println("method(int " + i + ")");
    }

    void method(String s) {
        System.out.println("method(String " + s + ")");
    }
}

class MoreOverloads extends ThreeOverloads {
    void method(float f) {
        System.out.println("method(float " + f + "f)");
    }
}

public class E13_InheritedOverloading {
    public static void main(String[] args) {
        MoreOverloads x = new MoreOverloads();
        x.method();
        x.method(1);
        x.method("Hello");
        x.method(1.0f);
    }
}
/* Output:
method()
method(int 1)
method(String Hello)
method(float 1.0f)
 */