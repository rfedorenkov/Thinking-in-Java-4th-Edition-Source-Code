package concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * {ThrowsException}
 */
public class NaiveExceptionHandling {
    public static void main(String[] args) {
        try {
            ExecutorService exec = Executors.newCachedThreadPool();
            exec.execute(new ExceptionThread());
        } catch (RuntimeException ue) {
            // This statement will NOT execute!
            System.out.println("Exception has been handled!");
        }
    }
}
/* Output:
Exception in thread "pool-1-thread-1" java.lang.RuntimeException
	at concurrency.ExceptionThread.run(ExceptionThread.java:12)
	at java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1128)
	at java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:628)
	at java.base/java.lang.Thread.run(Thread.java:830)
 */