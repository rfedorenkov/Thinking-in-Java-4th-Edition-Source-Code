package concurrency;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Automatically giving each thread its own storage.
 */
class Accessor implements Runnable {
    private final int id;

    public Accessor(int idn) {
        id = idn;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            ThreadLocalVariableHolder.increment();
            System.out.println(this);
            Thread.yield();
        }
    }

    @Override
    public String toString() {
        return "#" + id + ": " + ThreadLocalVariableHolder.get();
    }
}
public class ThreadLocalVariableHolder {
    private static ThreadLocal<Integer> value = new ThreadLocal<>() {
        private Random rand = new Random(47);

        @Override
        protected Integer initialValue() {
            return rand.nextInt(10000);
        }
    };

    public static void increment() {
        value.set(value.get() + 1);
    }

    public static int get() {
        return value.get();
    }

    public static void main(String[] args) throws Exception {
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++)
            exec.execute(new Accessor(i));
        TimeUnit.SECONDS.sleep(3); // Run for a while
        exec.shutdownNow(); // All Accessors will quit
    }

}
/* Output: (Sample)
#0: 10356
#1: 1473
#2: 7606
#4: 2104
#3: 2682
#0: 10357
#1: 1474
#2: 7607
#4: 2105
#3: 2683
#0: 10358
#1: 1475
...
 */