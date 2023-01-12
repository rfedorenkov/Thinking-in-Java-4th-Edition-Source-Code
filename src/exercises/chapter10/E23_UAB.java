package exercises.chapter10;

/**
 * Exercise 23
 * Create an interface U with three methods.
 * Create a class A with a method that produces a reference
 * to a U by building an anonymous inner class. Create
 * a second class B that contains an array of U. B should
 * have one method that accepts and stores a reference to
 * a U in the array, a second method that sets a reference
 * in the array, a second method that sets a reference
 * in the array (specified by the method argument) to null,
 * and a third method that moves through the array and calls
 * the methods in U. In main(), create a group of A objects
 * and a single B. Fill the B with U references produced
 * by the A objects. Use the B to call back into all the
 * A objects. Remove some of the U references from the B.
 */
interface U {
    void f();
    void g();
    void h();
}

class A {
    U makeU() {
        return new U() {
            @Override
            public void f() {
                System.out.println("A.f()");
            }

            @Override
            public void g() {
                System.out.println("A.g()");
            }

            @Override
            public void h() {
                System.out.println("A.h()");
            }
        };
    }
}

class B {
    private U[] array;

    B(int size) {
        array = new U[size];
    }

    public boolean add(U u) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == null) {
                array[i] = u;
                return true;
            }
        }
        return false;
    }

    public boolean remove(int index) {
        if (0 <= index && index < array.length) {
            array[index] = null;
            return true;
        }
        return false;
    }

    public void callAllMethods() {
        for (U u : array) {
            if (u != null) {
                u.f();
                u.g();
                u.h();
            }
        }
    }
}

public class E23_UAB {
    public static void main(String[] args) {
        A a1 = new A();
        A a2 = new A();
        A a3 = new A();
        B b = new B(3);
        b.add(a1.makeU());
        b.add(a2.makeU());
        b.add(a3.makeU());
        b.callAllMethods();
        System.out.println("b.remove(0)");
        b.remove(0);
        b.callAllMethods();
    }
}
/* Output:
A.f()
A.g()
A.h()
A.f()
A.g()
A.h()
A.f()
A.g()
A.h()
b.remove(0)
A.f()
A.g()
A.h()
A.f()
A.g()
A.h()
 */