package exceptions;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Logger;

/**
 * An exception that reports through a Logger.
 */
class LoggingException extends Exception {
    private static Logger logger = Logger.getLogger("LoggingException");

    public LoggingException() {
        StringWriter trace = new StringWriter();
        printStackTrace(new PrintWriter(trace));
        logger.severe(trace.toString());
    }
}

public class LoggingExceptions {
    public static void main(String[] args) {
        try {
            throw new LoggingException();
        } catch (LoggingException e) {
            System.err.println("Caught " + e);
        }

        try {
            throw new LoggingException();
        } catch (LoggingException e) {
            System.err.println("Caught " + e);
        }
    }
}
/* Output: (85% match)
Jan 22, 2023 12:50:19 PM exceptions.LoggingException <init>
SEVERE: exceptions.LoggingException
	at exceptions.LoggingExceptions.main(LoggingExceptions.java:23)

Caught exceptions.LoggingException
Jan 22, 2023 12:50:19 PM exceptions.LoggingException <init>
SEVERE: exceptions.LoggingException
	at exceptions.LoggingExceptions.main(LoggingExceptions.java:29)
 */