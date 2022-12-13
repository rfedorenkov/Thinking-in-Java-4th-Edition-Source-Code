package exercises.chapter5;

/**
 * Exercise 14
 * Create a class with a static String field that is initialized
 * at the point of definition, and another one initialized
 * by the static block. Add a static method that prints both fields
 * and demonstrates that they are both initialized before they are used.
 */
class StaticString {
    static String s1 = "Initialized when defining";
    static String s2;

    static {
        s2 = "Initialized in a static block";
    }

    static void print() {
        System.out.println("s1 = " + s1 + "; s2 = " + s2);
    }
}

public class E14_StaticStringInitialization {
    public static void main(String[] args) {
        StaticString.print();
    }
}
/* Output:
s1 = Initialized when defining; s2 = Initialized in a static block
 */