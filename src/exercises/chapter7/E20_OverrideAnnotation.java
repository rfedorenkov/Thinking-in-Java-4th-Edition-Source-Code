package exercises.chapter7;

import static net.mindview.util.Print.print;

/**
 * Exercise 20
 * Show that the @Override annotation solves the problem
 * from the "final and private" section.
 */
class WithFinals {
    // Identical to "private" alone:
    private final void f() {
        print("WithFinals.f()");
    }
    // Also automatically "final":
    private void g() {
        print("WithFinals.g()");
    }
}

class OverridingPrivate extends WithFinals {
    //! @Override // Error: method does not override or implement a method from a supertype
    private final void f() {
        print("OverridingPrivate.f()");
    }

    //! @Override // Error: method does not override or implement a method from a supertype
    private void g() {
        print("OverridingPrivate.g()");
    }
}

class OverridingPrivate2 extends OverridingPrivate {
    //! @Override // Error: method does not override or implement a method from a supertype
    public final void f() {
        print("OverridingPrivate2.f()");
    }

    //! @Override // Error: method does not override or implement a method from a supertype
    public void g() {
        print("OverridingPrivate2.g()");
    }
}

public class E20_OverrideAnnotation {
    public static void main(String[] args) {
        OverridingPrivate2 op2 = new OverridingPrivate2();
        op2.f();
        op2.g();
    }
}