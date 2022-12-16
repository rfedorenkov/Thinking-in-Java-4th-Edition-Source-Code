package exercises.chapter6;

import static exercises.chapter6.e03package.debug.Debug.debug;

/**
 * Exercise 3
 * Create two packages: debug and debugoff, containing
 * an identical class with a debug() method. The first
 * version displays its String argument to the console,
 * the second does nothing. Import the class into a test
 * program using a static import line, and demonstrate
 * the conditional compilation effect.
 */
public class E03_DebugTest1 {
    public static void main(String[] args) {
        debug("Debug");
    }
}
/* Output:
Message: Debug
 */