package exercises.chapter14;

import static net.mindview.util.Print.print;

/**
 * Exercise 21
 * Modify SimpleProxyDemo.java so it measures
 * method-call times.
 */
interface Interface {
    void doSomething();
    void somethingElse(String arg);
}

class RealObject implements Interface {
    @Override
    public void doSomething() {
        print("doSomething");
    }

    @Override
    public void somethingElse(String arg) {
        print("somethingElse " + arg);
    }
}

class SimpleProxy implements Interface {
    private Interface proxied;

    public SimpleProxy(Interface proxied) {
        this.proxied = proxied;
    }

    @Override
    public void doSomething() {
        long start = System.nanoTime();
        print("SimpleProxy doSomething");
        proxied.doSomething();
        long duration = System.nanoTime() - start;
        print("Method-call time: " + duration);
    }

    @Override
    public void somethingElse(String arg) {
        long start = System.nanoTime();
        print("SimpleProxy somethingElse " + arg);
        proxied.somethingElse(arg);
        long duration = System.nanoTime() - start;
        print("Method-call time: " + duration);
    }
}

public class E21_SimpleProxyDemo {
    public static void consumer(Interface iface) {
        iface.doSomething();
        iface.somethingElse("bonobo");
    }

    public static void main(String[] args) {
        consumer(new RealObject());
        consumer(new SimpleProxy(new RealObject()));
    }
}
/* Output:
doSomething
somethingElse bonobo
SimpleProxy doSomething
doSomething
Method call: 21666
SimpleProxy somethingElse bonobo
somethingElse bonobo
Method call: 62041
 */