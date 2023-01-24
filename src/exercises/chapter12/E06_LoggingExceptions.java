package exercises.chapter12;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Logger;

/**
 * Exercise 6
 * Create two exception classes, each of which
 * performs its own logging automatically.
 * Demonstrate that these work.
 */
class OpenException extends Exception {
    private static Logger logger = Logger.getLogger("OpenException");

    public OpenException() {
        StringWriter trace = new StringWriter();
        printStackTrace(new PrintWriter(trace));
        logger.severe(trace.toString());
    }
}

class CloseException extends Exception {
    private static Logger logger = Logger.getLogger("CloseException");

    public CloseException() {
        StringWriter trace = new StringWriter();
        printStackTrace(new PrintWriter(trace));
        logger.severe(trace.toString());
    }
}

public class E06_LoggingExceptions {
    public static void main(String[] args) {
        try {
            throw new OpenException();
        } catch (OpenException e) {
            System.err.println("Caught " + e);
        }

        try {
            throw new CloseException();
        } catch (CloseException e) {
            System.err.println("Caught " + e);
        }
    }
}
/* Output:
Jan 22, 2023 1:19:44 PM exercises.chapter12.OpenException <init>
SEVERE: exercises.chapter12.OpenException
	at exercises.chapter12.E06_LoggingExceptions.main(E06_LoggingExceptions.java:36)

Caught exercises.chapter12.OpenException
Jan 22, 2023 1:19:45 PM exercises.chapter12.CloseException <init>
SEVERE: exercises.chapter12.CloseException
	at exercises.chapter12.E06_LoggingExceptions.main(E06_LoggingExceptions.java:42)

Caught exercises.chapter12.CloseException
 */