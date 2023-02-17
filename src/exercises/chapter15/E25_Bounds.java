package exercises.chapter15;

/**
 * Exercise 25
 * Create two interfaces and a class that implements both.
 * Create two generic methods, one whose argument parameter
 * is bounded by the first interface and one whose argument
 * parameter is bounded by the second interface. Create an
 * instance of the class that implements both interfaces,
 * and show that it can be used with both generic methods.
 */
interface One {
    void a();
}

interface Two {
    void b();
}

class OneTwoImpl implements One, Two {

    @Override
    public void a() {
        System.out.println("One.a()");
    }

    @Override
    public void b() {
        System.out.println("Two.a()");
    }
}

public class E25_Bounds {
    static <T extends One> void testOne(T t) {
        t.a();
    }

    static <T extends Two> void testTwo(T t) {
        t.b();
    }

    public static void main(String[] args) {
        OneTwoImpl impl = new OneTwoImpl();
        testOne(impl);
        testTwo(impl);
    }
}
/* Output:
One.a()
Two.a()
 */