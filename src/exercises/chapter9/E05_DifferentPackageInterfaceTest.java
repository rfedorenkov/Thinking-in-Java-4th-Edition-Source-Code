package exercises.chapter9;

import exercises.chapter9.e05ownpackage.OwnPackageInterface;

/**
 * Exercise 5
 * Create an interface with three methods in its own package.
 * Implement the interface in different package.
 */
class ImplementInterface implements OwnPackageInterface {

    @Override
    public void f() {
        System.out.println("ImplementInterface.f()");
    }

    @Override
    public void g() {
        System.out.println("ImplementInterface.g()");
    }

    @Override
    public void h() {
        System.out.println("ImplementInterface.h()");
    }
}
public class E05_DifferentPackageInterfaceTest {
    public static void main(String[] args) {
        ImplementInterface test = new ImplementInterface();
        test.f();
        test.g();
        test.h();
    }
}
/* Output:
ImplementInterface.f()
ImplementInterface.g()
ImplementInterface.h()
 */