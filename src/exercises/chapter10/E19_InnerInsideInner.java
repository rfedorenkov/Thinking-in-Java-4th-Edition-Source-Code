package exercises.chapter10;

/**
 * Exercise 19
 * Create a class containing an inner class that itself contains
 * an inner class. Repeat this using static inner class.
 * Note the names of the .class files produced by the compiler.
 */
public class E19_InnerInsideInner {
    class Inner1 {
        class Inner2 {
        }
    }

    static class Nested1 {
        static class Nested2 {
        }
    }

    public static void main(String[] args) {
        E19_InnerInsideInner i = new E19_InnerInsideInner();
        Inner1 i1 = i.new Inner1();
        Inner1.Inner2 i2 = i1.new Inner2();

        Nested1 nested1 = new Nested1();
        Nested1.Nested2 nested2 = new Nested1.Nested2();
    }
}
/* compiler produces:
E19_InnerInsideInner.class
E19_InnerInsideInner$Inner1.class
E19_InnerInsideInner$Inner1$Inner2.class
E19_InnerInsideInner$Nested1.class
E19_InnerInsideInner$Nested1$Nested2.class
 */