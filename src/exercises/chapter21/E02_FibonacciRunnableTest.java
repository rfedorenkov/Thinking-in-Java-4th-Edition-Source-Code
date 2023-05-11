package exercises.chapter21;

import net.mindview.util.Generator;

import java.util.Arrays;

/**
 * Exercise 2
 * Following the form of generics/Fibonacci.java create a task
 * that produces a sequence of n Fibonacci numbers, where n is
 * provided to the constructor of the task. Create a number of
 * these tasks and drive them using threads.
 */
class Fibonacci implements Generator<Integer>, Runnable {
    private int count = 0;
    private final int n;

    public Fibonacci(int n) {
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
    public void run() {
        int[] numbers = new int[n];
        for (int i = 0; i < n; i++)
            numbers[i] = next();
        System.out.println(Arrays.toString(numbers));
    }
}

public class E02_FibonacciRunnableTest {
    public static void main(String[] args) {
        for (int i = 1; i < 6; i++)
            new Thread(new Fibonacci(i)).start();
    }
}
/* Output: (Sample)
[1]
[1, 1, 2]
[1, 1]
[1, 1, 2, 3]
[1, 1, 2, 3, 5]
 */

/* Output:
1 1 2 3 5 8 13 21 34 55 89 144 233 377 610 987 1597 2584
 */