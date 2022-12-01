package exercises.chapter2;

/**
 * Exercise 15
 * Take the program in Exercise 2 and add comment documentation to it.
 * Extract this comment documentation into an HTML file using Javadoc
 * and view it with your Web browser.
 */
public class E15_HelloWorldDoc {
}

/**
 * Public class that prints: <code>hello, world</code> to the console
 */
class HelloWorld {
    /**
     * Main method executed by java
     * @param args array of string arguments
     */
    public static void main(String[] args) {
        System.out.println("hello, world");
    }
}
/* Output:
hello, world
 */