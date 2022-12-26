package exercises.chapter9;

import interfaces.Months;

/**
 * Exercise 17
 * Prove that the fields in an interface
 * are implicitly static and final.
 */
public class E17_InterfaceStaticFinalTest {
    public static void main(String[] args) {
        System.out.println("Months.DECEMBER = " + Months.DECEMBER);
        //! Months.DECEMBER = 1; // Can't assign a value to final variable
    }
}
/* Output:
Months.DECEMBER = 12
 */