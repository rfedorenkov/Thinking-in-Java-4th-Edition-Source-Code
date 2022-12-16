package exercises.chapter6;

import exercises.chapter6.e05package.OtherTwo;

/**
 * Exercise 5
 * Create a class with public, private, protected,
 * nd package-access fields and method members.
 * Create an object of this class and see what king
 * of compiler messages you get when you try to access
 * all the class members.
 * Remember that classes in the same directory are part
 * of the "default" package.
 */
class OtherOne {
    private String a = "private";
    String b = "default";
    protected String c = "protected";
    public String d = "public";

    private String getA() {
        return a;
    }

    String getB() {
        return b;
    }

    protected String getC() {
        return c;
    }

    public String getD() {
        return d;
    }
}

public class E05_PackageTest {
    private String a = "private";
    String b = "default";
    protected String c = "protected";
    public String d = "public";

    private String getA() {
        return a;
    }

    String getB() {
        return b;
    }

    protected String getC() {
        return c;
    }

    public String getD() {
        return d;
    }

    public static void main(String[] args) {
        System.out.println("Test 1");
        E05_PackageTest test1 = new E05_PackageTest();
        System.out.println("test1.a = " + test1.a);
        System.out.println("test1.b = " + test1.b);
        System.out.println("test1.c = " + test1.c);
        System.out.println("test1.d = " + test1.d);
        System.out.println("test1.getA() = " + test1.getA());
        System.out.println("test1.getB() = " + test1.getB());
        System.out.println("test1.getC() = " + test1.getC());
        System.out.println("test1.getD() = " + test1.getD());

        System.out.println("Test 2");
        OtherOne test2 = new OtherOne();
        //! System.out.println("test2.a = " + test2.a); // has private access
        System.out.println("test2.b = " + test2.b);
        System.out.println("test2.c = " + test2.c);
        System.out.println("test2.d = " + test2.d);
        //! System.out.println("test2.getA() = " + test2.getA()); // has private access
        System.out.println("test2.getB() = " + test2.getB());
        System.out.println("test2.getC() = " + test2.getC());
        System.out.println("test2.getD() = " + test2.getD());

        System.out.println("Test 3");
        OtherTwo test3 = new OtherTwo();
        //! System.out.println("test3.a = " + test3.a); // has private access
        //! System.out.println("test3.b = " + test3.b); // not public
        //! System.out.println("test3.c = " + test3.c); // has protected access
        System.out.println("test3.d = " + test3.d);
        //! System.out.println("test3.getA() = " + test3.getA()); // has private access
        //! System.out.println("test3.getB() = " + test3.getB()); // not public
        //! System.out.println("test3.getC() = " + test3.getC()); // has protected access
        System.out.println("test3.d = " + test3.getD());
    }
}
/* Output:
Test 1
test1.a = private
test1.b = default
test1.c = protected
test1.d = public
test1.getA() = private
test1.getB() = default
test1.getC() = protected
test1.getD() = public
Test 2
test2.b = default
test2.c = protected
test2.d = public
test2.getB() = default
test2.getC() = protected
test2.getD() = public
Test 3
test3.d = public
test3.d = public
 */