package exercises.chapter6;

import exercises.chapter6.e04package.Cookie2;

/**
 * Exercise 4
 * Show that protected methods have package
 * access but are not public.
 */
public class E04_CookTest {
    public static void main(String[] args) {
        E04_Cookie cookie1 = new E04_Cookie();
        cookie1.bite(); // package access to protected method

        Cookie2 cookie2 = new Cookie2();
        //! cookie2.bite(); // Can't access bite
    }
}
/* Output:
Cookie constructor
bite()
Cookie2 constructor
 */