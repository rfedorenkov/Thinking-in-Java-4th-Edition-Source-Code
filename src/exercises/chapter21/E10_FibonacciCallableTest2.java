package exercises.chapter21;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Exercise 10
 * Modify Exercise 5 following the example of the ThreadMethod
 * class, so that runTask() takes an argument of the number
 * of Fibonacci numbers to sum, and each time you call runTask()
 * it returns the Future produced by the call to submit().
 */
class Fibonacci3 {
    private static ExecutorService exec;

    public static synchronized void create() {
        if (exec == null)
            exec = Executors.newCachedThreadPool();
    }

    private static int fib(int n) {
        if (n < 2) return 1;
        return fib(n - 2) + fib(n - 1);
    }

    public static synchronized Future<Integer> runTask(final int n) {
        return exec.submit(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                int result = 0;
                for (int i = 0; i < n; i++)
                    result += fib(i);
                return result;
            }
        });
    }

    public static synchronized void shutdown() {
        if (exec != null)
            exec.shutdown();
        exec = null;
    }
}

public class E10_FibonacciCallableTest2 {
    public static void main(String[] args) {
        List<Future<Integer>> results = new ArrayList<>();
        Fibonacci3.create();
        for (int i = 1; i < 6; i++)
            results.add(Fibonacci3.runTask(i));

        for (Future<Integer> fs : results)
            try {
                System.out.println(fs.get());
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            } finally {
                Fibonacci3.shutdown();
            }
    }
}
/* Output:
1
2
4
7
12
 */