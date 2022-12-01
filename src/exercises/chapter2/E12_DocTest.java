package exercises.chapter2;

import java.util.Date;

/**
 * Exercise 12
 * Find the code for the second version of HelloDate.java,
 * which is the simple comment documentation example.
 * Execute Javadoc on the file and view the
 * results with you Web browser.
 */

public class E12_DocTest {

}

/**
 * The first Thinking in Java example program.
 * Displays a string and today's date.
 * @author Bruce Eckel
 * @author www.MingView.net
 * @version 4.0
 */
class HelloDate {
    /**
     * Entry point to class & application.
     * @param args array of string arguments
     * @throws exceptions No exceptions thrown
     */
    public static void main(String[] args) {
        System.out.println("Hello, it's:");
        System.out.println(new Date());
    }
}
/* Output: (55% match)
Hello, it's:
Sun Nov 27 22:12:30 MSK 2022
 */