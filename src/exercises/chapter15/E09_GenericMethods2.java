package exercises.chapter15;

/**
 * Exercise 9
 * Modify GenericMethods.java so that f() accepts three arguments,
 * all of which are of a different parameterized type.
 */
public class E09_GenericMethods2 {
    public <T, S, U> void f(T x, S y, U z) {
        System.out.println(x.getClass().getName());
        System.out.println(y.getClass().getName());
        System.out.println(z.getClass().getName());
    }

    public static void main(String[] args) {
        E09_GenericMethods2 gm = new E09_GenericMethods2();
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
exercises.chapter15.E09_GenericMethods2
 */