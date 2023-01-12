package exercises.chapter10;

/**
 * Exercise 21
 * Create an interface with a nested class and a static
 * method that calls the methods of your interface and
 * displays the results. Implement your interface and
 * pass an instance of your implementation to the method.
 */
interface WithNestedClass2 {
    void f();

    void g();

    class Nested2 {
        static void call(WithNestedClass2 impl) {
            impl.f();
            impl.g();
        }
    }
}

class I implements WithNestedClass2 {

    @Override
    public void f() {
        System.out.println("I.f()");
    }

    @Override
    public void g() {
        System.out.println("I.g()");
    }
}

public class E21_InterfaceWithNestedClass2 {
    public static void main(String[] args) {
        WithNestedClass2 b = new I();
        WithNestedClass2.Nested2.call(b);
    }
}
/* Output:
I.f()
I.g()
 */