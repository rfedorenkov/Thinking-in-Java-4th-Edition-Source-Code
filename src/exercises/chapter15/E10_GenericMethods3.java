package exercises.chapter15;

/**
 * Exercise 10
 * Modify the previous exercise so that one
 * of f()'s arguments is non-parameterized.
 */
///****************** Exercise 10 *****************
//          * Modify the previous exercise so that one of
//          * f()'s arguments is non-parameterized.
//          ************************************************/
public class E10_GenericMethods3 {
    public <T, S> void f(Object x, T y, S z) {
        System.out.println(x.getClass().getName());
        System.out.println(y.getClass().getName());
        System.out.println(z.getClass().getName());
    }

    public static void main(String[] args) {
        E10_GenericMethods3 gm = new E10_GenericMethods3();
        gm.f("", 1, 1.0);
        gm.f(1.0F, 'c', gm);
    }
}
/* Output:
java.lang.String
java.lang.Integer
java.lang.Double
java.lang.Float
java.lang.Character
exercises.chapter15.E10_GenericMethods3
 */