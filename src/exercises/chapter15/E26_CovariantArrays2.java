package exercises.chapter15;

/**
 * Exercise 26
 * Demonstrate array covariance using Numbers and Integers.
 */
public class E26_CovariantArrays2 {
    public static void main(String[] args) {
        Number[] numbers = new Integer[10];
        numbers[0] = 1; // OK
        // Runtime type is Integer[], not Double[] or Byte[]:
        try {
            // Compiler allows you to add Double:
            numbers[0] = 1.1d; // ArrayStoreException
        } catch (Exception e) {
            System.out.println(e);
        }
        try {
            // Compiler allows you to add Byte:
            numbers[0] = (byte) 123; // ArrayStoreException
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
/* Output:
java.lang.ArrayStoreException: java.lang.Double
java.lang.ArrayStoreException: java.lang.Byte
 */