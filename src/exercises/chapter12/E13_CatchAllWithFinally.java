package exercises.chapter12;

/**
 * Exercise 13
 * Modify Exercise 9 by adding a finally clause.
 * Verify that your finally clause is executed,
 * even if a NullPointerException is thrown.
 * {ThrowsException}
 */
public class E13_CatchAllWithFinally {
    public static void throwNPE() {
        throw new NullPointerException();
    }

    public static void main(String[] args) {
        try {
            ThrowsAll.f();
        } catch (BaseException e) {
            System.out.println("Caught: " + e);
        } finally {
            System.out.println("finally part one");
        }

        try {
            throwNPE();
            ThrowsAll.f();
        } catch (BaseException e) {
            System.out.println("Caught: " + e);
        } finally {
            System.out.println("finally part two");
        }
    }
}
/* Output:
Caught: exercises.chapter12.SmallException: Value of 0
finally part one
finally part two
Exception in thread "main" java.lang.NullPointerException
	at exercises.chapter12.E13_CatchAllWithFinally.throwNPE(E13_CatchAllWithFinally.java:12)
	at exercises.chapter12.E13_CatchAllWithFinally.main(E13_CatchAllWithFinally.java:24)
 */