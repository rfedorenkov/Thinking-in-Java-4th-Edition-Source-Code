package exercises.chapter12;

/**
 * Exercise 3
 * Write a code to generate and catch an ArrayIndexOutOfBoundsException.
 */
public class E03_GenerateArrayIndexOfBoundsException {
    public static void main(String[] args) {
        int[] ints = new int[0];
        try {
            ints[0] = 47;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Caught ArrayIndexOutOfBoundsException");
            e.printStackTrace();
        }
    }
}
/* Output:
Caught ArrayIndexOutOfBoundsException
java.lang.ArrayIndexOutOfBoundsException: Index 0 out of bounds for length 0
	at exercises.chapter12.E03_GenerateArrayIndexOfBoundsException.main(E03_GenerateArrayIndexOfBoundsException.java:11)
 */