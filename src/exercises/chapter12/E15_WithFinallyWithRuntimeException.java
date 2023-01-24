package exercises.chapter12;

import exceptions.OnOffException1;
import exceptions.OnOffException2;
import exceptions.Switch;

/**
 * Exercise 15
 * Show that WithFinally.java doesn't fail by throwing
 * a RuntimeException inside the try block.
 * {ThrowsException}
 */
public class E15_WithFinallyWithRuntimeException {
    static Switch sw = new Switch();

    public static void f() throws OnOffException1, OnOffException2 {
        throw new RuntimeException("Inside f()");
    }

    public static void main(String[] args) {
        try {
            sw.on();
            // Code that can throw Exceptions...
            f();
        } catch (OnOffException1 e) {
            System.out.println("OnOffException1");
        } catch (OnOffException2 e) {
            System.out.println("OnOffException2");
        } finally {
            sw.off();
        }
    }
}
/* Output:
on
off
Exception in thread "main" java.lang.RuntimeException: Inside f()
	at exercises.chapter12.E15_WithFinallyWithRuntimeException.f(E15_WithFinallyWithRuntimeException.java:17)
	at exercises.chapter12.E15_WithFinallyWithRuntimeException.main(E15_WithFinallyWithRuntimeException.java:24)
 */