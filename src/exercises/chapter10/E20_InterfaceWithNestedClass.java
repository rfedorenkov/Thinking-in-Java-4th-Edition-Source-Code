package exercises.chapter10;

/**
 * Exercise 20
 * Create an interface containing a nested class.
 * Implement this interface and create an instance
 * of the nested class.
 */
interface WithNestedClass {

    void f();

    class Nested implements WithNestedClass {
        @Override
        public void f() {
            System.out.println("Nested.f()");
        }
    }
}

public class E20_InterfaceWithNestedClass {
    public static void main(String[] args) {
        WithNestedClass n = new WithNestedClass.Nested();
        n.f();
    }
}
/* Output:
Nested.f()
 */