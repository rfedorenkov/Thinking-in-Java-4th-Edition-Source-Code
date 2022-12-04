package exercises.chapter3;

/**
 * Exercise 14
 * Write a method that takes two String arguments uses all the boolean
 * comparisons to compare the two String and print the results.
 * For the == and !=, also perform the equals() test. In main(), test
 * your method with some different String objects.
 */
public class E14_StringCompare {
    static boolean f(boolean b) {
        return b;
    }

    static void stringTest(String s1, String s2) {
        System.out.println("s1 = " + s1 + ", s2 = " + s2);
        System.out.println("s1 == s2 : " + f(s1 == s2));
        System.out.println("s1 != s2 : " + f(s1 != s2));
        System.out.println("s1.equals(s2) : " + f(s1.equals(s2)));
        //! f(s1 > s2);
        //! f(s1 >= s2);
        //! f(s1 < s2);
        //! f(s1 <= s2);
        //! f(s1 && s2);
        //! f(s1 || s2);
        //! f(!s2);
        System.out.println();
    }

    public static void main(String[] args) {
        stringTest("Hello", "Hello");
        stringTest("Hello", new String("Hello"));
        stringTest("Hello", "World");
    }
}
/* Output:
s1 = Hello, s2 = Hello
s1 == s2 : true
s1 != s2 : false
s1.equals(s2) : true

s1 = Hello, s2 = Hello
s1 == s2 : false
s1 != s2 : true
s1.equals(s2) : true

s1 = Hello, s2 = World
s1 == s2 : false
s1 != s2 : true
s1.equals(s2) : false
 */