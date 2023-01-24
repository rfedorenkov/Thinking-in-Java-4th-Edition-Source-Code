package exercises.chapter12;

/**
 * Exercise 2
 * Define an object reference and initialize it to null.
 * Try to call a method through this reference. Now wrap
 * the code in a try-catch clause to catch the exception.
 */
public class E02_NullReference {
    public static void main(String[] args) {
        String s = null;
        try {
            char[] arr = s.toCharArray();
        } catch (NullPointerException e) {
            System.out.println("Caught NullPointerException");
            e.printStackTrace();
        }
    }
}
/* Output:
Caught NullPointerException
java.lang.NullPointerException
	at exercises.chapter12.E02_NullReference.main(E02_NullReference.java:13)
 */