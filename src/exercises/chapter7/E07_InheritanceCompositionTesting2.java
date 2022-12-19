package exercises.chapter7;

/**
 * Exercise 7
 * Modify Exercise 5 so A and B have constructors
 * with arguments instead of default constructors.
 * Write a constructor for C and perform all
 * initialization within it.
 */
class A2 {
    A2(String s) {
        System.out.println("A(" + s + ")");
    }
}

class B2 {
    B2(int i) {
        System.out.println("B(" + i + ")");
    }
}

class C2 extends A2 {
    B2 b;

    public C2(String s, int i) {
        super(s);
        this.b = new B2(i);
        System.out.println("C2(" + s + ", " + i + ")");
    }
}

public class E07_InheritanceCompositionTesting2 {
    public static void main(String[] args) {
        C2 c = new C2("Hello", 47);
    }
}
/* Output:
A(Hello)
B(47)
C2(Hello, 47)
 */