package exercises.chapter9;

/**
 * Exercise 6
 * Prove that all the methods in an interface are automatically public.
 */
interface PublicInterface {
    void f();
}

public class E06_PublicInterfaceTest implements PublicInterface {
    // attempting to assign weaker access privileges; was public
    //! void f() { }
    //! private void f() { }
    //! protected void f() {}
    @Override
    public void f() {
        System.out.println("E06_PublicInterfaceTest.f()");
    }

    public static void main(String[] args) {
        new E06_PublicInterfaceTest().f();
    }
}
/* Output:
E06_PublicInterfaceTest.f()
 */