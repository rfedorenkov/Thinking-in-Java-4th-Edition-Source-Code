package exercises.chapter21;

import net.mindview.util.Generator;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Exercise 5
 * Modify Exercise 2 so that the task is a Callable
 * that sums the values of all the Fibonacci numbers.
 * Create several task and display the results.
 */
class Fibonacci2 implements Generator<Integer>, Callable<Integer> {
    private int count = 0;
    private final int n;

    public Fibonacci2(int n) {
        this.n = n;
    }

    @Override
    public Integer next() {
        return fib(count++);
    }

    private int fib(int n) {
        if (n < 2) return 1;
        return fib(n - 2) + fib(n - 1);
    }

    @Override
    public Integer call() {
        int result = 0;
        for (int i = 0; i < n; i++)
            result += next();
        return result;
    }
}

public class E05_FibonacciCallableTest {
    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        List<Future<Integer>> results = new ArrayList<>();
        for (int i = 1; i < 6; i++)
            results.add(exec.submit(new Fibonacci2(i)));
        for (Future<Integer> fs : results)
            try {
                System.out.println(fs.get());
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            } finally {
                exec.shutdown();
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