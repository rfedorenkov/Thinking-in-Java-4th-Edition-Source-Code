package exercises.chapter10;

/**
 * Exercise 26
 * Create a class with an inner class that has a
 * non-default constructor (one that takes arguments).
 * Create a second class with an inner class that
 * inherits from the first inner class.
 */

class WithInner {
    class Inner {
        private String data;

        public Inner(String data) {
            this.data = data;
        }

        public void f() {
            System.out.println("WithInner.Inner.f(), data = " + data);
        }
    }
}

class SecondWithInner {
    class SecondInner extends WithInner.Inner {
        public SecondInner(WithInner wi, String data) {
            wi.super(data);
        }

        @Override
        public void f() {
            System.out.println("SecondWithInner.SecondInner.f()");
            super.f();
        }
    }
}

public class E26_InnerClassInheritance {
    public static void main(String[] args) {
        WithInner wi = new WithInner();
        SecondWithInner swi = new SecondWithInner();
        SecondWithInner.SecondInner inner = swi.new SecondInner(wi, "Hello world");
        inner.f();
    }
}
/* Output:
SecondWithInner.SecondInner.f()
WithInner.Inner.f(), data = Hello world
 */