package exercises.chapter9;

/**
 * Exercise 15
 * Modify Exercise 14 by creating an abstract class
 * and inheriting it into the derived class.
 */
abstract class Base2 {
}

class ReleaseBase2 extends Base2 implements MultiplyInterface {

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

public class E15_AbstractInterfaceInheritance {
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
        ReleaseBase2 test = new ReleaseBase2();
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