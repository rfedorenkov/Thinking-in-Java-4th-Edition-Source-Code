package exercises.chapter12;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Logger;

/**
 * Exercise 7
 * Modify Exercise 3 so that the catch clause logs the results.
 */
public class E07_GenerateArrayIndexOfBoundsException2 {
    private static Logger logger = Logger.getLogger("E07_GenerateArrayIndexOfBoundsException2");

    public static void logException(Exception e) {
        StringWriter trace = new StringWriter();
        e.printStackTrace(new PrintWriter(trace));
        logger.severe(trace.toString());
    }

    public static void main(String[] args) {
        int[] ints = new int[0];
        try {
            ints[0] = 47;
        } catch (ArrayIndexOutOfBoundsException e) {
            logException(e);
        }
    }
}
/* Output:
Jan 22, 2023 1:25:10 PM exercises.chapter12.E07_GenerateArrayIndexOfBoundsException2 logException
SEVERE: java.lang.ArrayIndexOutOfBoundsException: Index 0 out of bounds for length 0
	at exercises.chapter12.E07_GenerateArrayIndexOfBoundsException2.main(E07_GenerateArrayIndexOfBoundsException2.java:23)
 */