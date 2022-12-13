package exercises.chapter5;

/**
 * Exercise 8
 * Create a class with two methods. Within the first method,
 * call the second method twice: the first time without using this,
 * and the second time using this - just to see it working;
 * you should not use this form in practice.
 */
class TestMethod {
    void a() {
        System.out.println("a()");
        b();
        this.b();
    }

    void b() {
        System.out.println("b()");
    }
}

public class E08_ThisMethodCall {
    public static void main(String[] args) {
        TestMethod method = new TestMethod();
        method.a();
    }
}
/* Output:
a()
b()
b()
 */