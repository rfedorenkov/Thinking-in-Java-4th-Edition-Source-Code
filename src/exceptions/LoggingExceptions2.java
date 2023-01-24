package exceptions;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Logger;

/**
 * Logging caught exceptions.
 */
public class LoggingExceptions2 {
    private static Logger logger = Logger.getLogger("LoggingExceptions2");

    static void logException(Exception e) {
        StringWriter trace = new StringWriter();
        e.printStackTrace(new PrintWriter(trace));
        logger.severe(trace.toString());
    }

    public static void main(String[] args) {
        try {
            throw new NullPointerException();
        } catch (NullPointerException e) {
            logException(e);
        }
    }
}
/* Output: (90% match)
Jan 22, 2023 12:57:11 PM exceptions.LoggingExceptions2 logException
SEVERE: java.lang.NullPointerException
	at exceptions.LoggingExceptions2.main(LoggingExceptions2.java:21)
 */