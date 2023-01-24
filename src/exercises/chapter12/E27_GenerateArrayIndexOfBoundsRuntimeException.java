package exercises.chapter12;

/**
 * Exercise 27
 * Modify Exercise 3 to convert the exception to a RuntimeException.
 * {ThrowsException}
 */
public class E27_GenerateArrayIndexOfBoundsRuntimeException {
    public static void main(String[] args) {
        int[] ints = new int[0];
        try {
            ints[0] = 47;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new RuntimeException(e);
        }
    }
}
/* Output:
Exception in thread "main" java.lang.RuntimeException: java.lang.ArrayIndexOutOfBoundsException: Index 0 out of bounds for length 0
	at exercises.chapter12.E27_GenerateArrayIndexOfBoundsRuntimeException.main(E27_GenerateArrayIndexOfBoundsRuntimeException.java:14)
Caused by: java.lang.ArrayIndexOutOfBoundsException: Index 0 out of bounds for length 0
	at exercises.chapter12.E27_GenerateArrayIndexOfBoundsRuntimeException.main(E27_GenerateArrayIndexOfBoundsRuntimeException.java:12)
 */