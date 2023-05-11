package exercises.chapter21;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Exercise 4
 * Repeat Exercise 2 using the different types
 * of executors shown in this section.
 */
public class E04_FibonacciRunnableTest2 {
    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 1; i < 6; i++)
            exec.execute(new Fibonacci(i));
        exec.shutdown();
        Thread.yield();
        exec = Executors.newFixedThreadPool(5);
        for (int i = 1; i < 6; i++)
            exec.execute(new Fibonacci(i));
        exec.shutdown();
        Thread.yield();
        exec = Executors.newSingleThreadExecutor();
        for (int i = 1; i < 6; i++)
            exec.execute(new Fibonacci(i));
        exec.shutdown();
        Thread.yield();
    }
}
/* Output: (Sample)
[1, 1, 2, 3]
[1, 1, 2, 3, 5]
[1, 1]
[1, 1, 2]
[1]
[1]
[1, 1]
[1, 1, 2]
[1, 1, 2, 3]
[1, 1, 2, 3, 5]
[1]
[1, 1]
[1, 1, 2]
[1, 1, 2, 3]
[1, 1, 2, 3, 5]
 */