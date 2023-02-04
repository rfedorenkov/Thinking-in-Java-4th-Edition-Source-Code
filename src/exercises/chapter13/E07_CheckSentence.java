package exercises.chapter13;

import java.util.regex.Pattern;

/**
 * Exercise 7
 * Using the documentation for java.util.regex.Pattern as a resource,
 * write and test a regular expression that checks a sentence to see
 * that it begins with a capital letter and ends with a period.
 */
public class E07_CheckSentence {
    private static Pattern pattern = Pattern.compile("^[A-Z].*[.$]");

    public static boolean check(String text) {
        return text.matches("\\p{javaUpperCase}.*\\.");
    }

    public static void main(String[] args) {
        System.out.println(check("Hello world.")); // true
        System.out.println(check("Hello world")); // false
        System.out.println(check("hello world.")); // false
        System.out.println(check("Hello World.")); // true
        System.out.println(check("hello World.")); // false
    }
}
/* Output:
true
false
false
true
false
 */