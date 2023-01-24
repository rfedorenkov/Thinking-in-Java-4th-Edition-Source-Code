package exercises.chapter12;

import exceptions.OnOffException1;
import exceptions.OnOffException2;
import exceptions.Switch;

/**
 * Exercise 14
 * Show that OnOffSwitch.java can fail by throwing
 * a RuntimeException inside the try block.
 * {ThrowsException}
 */
public class E14_OnOffSwitchWithRuntimeException {
    private static Switch sw = new Switch();

    public static void f() throws OnOffException1, OnOffException2 {
        throw new RuntimeException("Inside f()");
    }

    public static void main(String[] args) {
        try {
            sw.on();
            // Code that can throw exceptions...
            f();
            sw.off();
        } catch (OnOffException1 e) {
            System.out.println("OnOffException1");
            sw.off();
        } catch (OnOffException2 e) {
            System.out.println("OnOffException2");
            sw.off();
        }
    }
}
/* Output:
on
Exception in thread "main" java.lang.RuntimeException: Inside f()
	at exercises.chapter12.E14_OnOffSwitchWithRuntimeException.f(E14_OnOffSwitchWithRuntimeException.java:16)
	at exercises.chapter12.E14_OnOffSwitchWithRuntimeException.main(E14_OnOffSwitchWithRuntimeException.java:23)
 */