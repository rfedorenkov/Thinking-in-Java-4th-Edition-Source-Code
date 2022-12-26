package exercises.chapter9;

/**
 * Exercise 14
 * Create three interfaces, each with two methods.
 * Inherit a new interface from each, adding a new method.
 * Use the new interface to create a class, and inherit
 * from a concrete class. Now write four methods, each
 * of which takes one of the four interfaces as an argument.
 * Create an object of your class in main(), and pass it
 * to each of the methods.
 */
interface InterfaceOne {
    void a();
    void b();
}

interface InterfaceTwo {
    void c();
    void d();
}

interface InterfaceThree {
    void e();
    void f();
}

interface MultiplyInterface extends InterfaceOne, InterfaceTwo, InterfaceThree {
    void g();
}

class Base {

}

class ReleaseBase extends Base implements MultiplyInterface {

    @Override
    public void a() {
        System.out.println("ReleaseBase.a()");
    }

    @Override
    public void b() {
        System.out.println("ReleaseBase.b()");
    }

    @Override
    public void c() {
        System.out.println("ReleaseBase.c()");
    }

    @Override
    public void d() {
        System.out.println("ReleaseBase.d()");
    }

    @Override
    public void e() {
        System.out.println("ReleaseBase.e()");
    }

    @Override
    public void f() {
        System.out.println("ReleaseBase.f()");
    }

    @Override
    public void g() {
        System.out.println("ReleaseBase.g()");
    }
}

public class E14_InterfaceInheritance {
    static void testOne(InterfaceOne i) {
        System.out.println("testOne()");
        i.a();
        i.b();
    }

    static void testTwo(InterfaceTwo i) {
        System.out.println("testTwo()");
        i.c();
        i.d();
    }

    static void testThree(InterfaceThree i) {
        System.out.println("testThree()");
        i.e();
        i.f();
    }

    static void testMultiply(MultiplyInterface i) {
        System.out.println("testMultiply()");
        i.a();
        i.b();
        i.c();
        i.d();
        i.e();
        i.f();
        i.g();
    }

    public static void main(String[] args) {
        ReleaseBase test = new ReleaseBase();
        testOne(test);
        testTwo(test);
        testThree(test);
        testMultiply(test);
    }
}
/* Output:
testOne()
ReleaseBase.a()
ReleaseBase.b()
testTwo()
ReleaseBase.c()
ReleaseBase.d()
testThree()
ReleaseBase.e()
ReleaseBase.f()
testMultiply()
ReleaseBase.a()
ReleaseBase.b()
ReleaseBase.c()
ReleaseBase.d()
ReleaseBase.e()
ReleaseBase.f()
ReleaseBase.g()
 */