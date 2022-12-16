package exercises.chapter6;

import exercises.chapter6.e01package.E01_MyClass;

/**
 * Exercise 1
 * Create a class in a package.
 * Create an instance of your class outside of that package.
 */
public class E01_PackagedMyClass {
    public static void main(String[] args) {
        E01_MyClass m = new E01_MyClass();
    }
}
/* Output:
exercises.chapter6.e01package.E01_MyClass
 */