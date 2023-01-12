package exercises.chapter10;

/**
 * Exercise 8
 * Determine whether an outer class has access
 * to the private elements of its inner class.
 */
class Outer4 {
    private class Inner4 {
        private int x;

        private void f() {
            System.out.println("Call Inner.f() x = " + x);
        }
    }

    public void testInnerAccess() {
        Inner4 i = new Inner4();
        i.f();
        i.x = 47;
        i.f();
    }
}
public class E08_PrivateInnerAccessTest {
    public static void main(String[] args) {
        Outer4 o = new Outer4();
        o.testInnerAccess();
    }
}
/* Output:
Call Inner.f() x = 0
Call Inner.f() x = 47
 */