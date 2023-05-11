package exercises.chapter21;

import concurrency.LiftOff;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Exercise 20
 * Modify CachedThreadPool.java so that all task receive
 * an interrupt() before they are completed.
 */
class LiftOff2 implements Runnable {
    protected int countDown = 10; // Default
    private static int taskCount = 0;
    private final int id = taskCount++;

    public LiftOff2() {
    }

    public LiftOff2(int countDown) {
        this.countDown = countDown;
    }

    public String status() {
        return "#" + id + "(" +
                (countDown > 0 ? countDown : "Liftoff!") + "), ";
    }

    @Override
    public void run() {
        while (countDown-- > 0) {
            if (Thread.currentThread().isInterrupted()) {
                System.out.println("Interrupted: #" + id);
                return;
            }
            System.out.print(status());
            Thread.yield();
        }
    }
}

public class E20_CachedThreadPool {
        public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++)
            exec.execute(new LiftOff2());
        Thread.yield();
        exec.shutdownNow();
    }
}
/* Output: (Sample)
Interrupted: #4
#1(9), Interrupted: #1
#0(9), #2(9), Interrupted: #0
#3(9), Interrupted: #2
Interrupted: #3
 */