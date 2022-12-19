package exercises.chapter7;

/**
 * Exercise 21
 * Create a class with a final method.
 * Inherit from that class and attempt
 * to override that method.
 */
class WithFinalMethod {
    public final void f() {
        System.out.println("WithFinalMethod.f()");
    }
}

class WithFinalMethod2 extends WithFinalMethod {
    //! public void f() {} // overridden method is final
}

public class E21_FinalMethod {
    public static void main(String[] args) {
        WithFinalMethod m = new WithFinalMethod();
        WithFinalMethod2 m2 = new WithFinalMethod2();
        m.f();
        m2.f();
    }
}
/* Output:
WithFinalMethod.f()
WithFinalMethod.f()
 */