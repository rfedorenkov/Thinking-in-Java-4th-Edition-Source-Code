package exercises.chapter21;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static net.mindview.util.Print.print;

/**
 * Exercise 22
 * Create an example of a busy wait. One task sleeps for a while
 * and then sets a flag to true. The second task watches that flag
 * inside a while loop (this is the busy wait) and when the flag
 * becomes true, sets it back to false and reports the change
 * to the console. Note how much wasted time the program spends
 * inside the busy wait, and create a second version of the program
 * that uses wait() instead of the busy wait.
 */
class BusyWait {
    private static volatile boolean flag = false;

    public static void test() throws InterruptedException {
        print("BusyWait.test() started");
        Runnable task1 = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        TimeUnit.MILLISECONDS.sleep(100);
                        flag = true;
                    } catch (InterruptedException e) {
                        print("Exiting via interrupted");
                        return;
                    }
                }
            }
        };

        Runnable task2 = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    long start = System.currentTimeMillis();
                    while (!flag && !Thread.currentThread().isInterrupted()) {
                    }
                    long end = System.currentTimeMillis();
                    print("Busy waiting " + (end - start) + " ms");
                    if (Thread.interrupted())
                        return;
                    flag = false;
                }
            }
        };

        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(task1);
        exec.execute(task2);
        TimeUnit.SECONDS.sleep(1);
        exec.shutdownNow();
        print("BusyWait.test() ended");
    }
}

class WaitNotify {
    public static void test() throws InterruptedException {
        print("WaitNotify.test() started");
        final Runnable task1 = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        TimeUnit.MILLISECONDS.sleep(100);
                        synchronized (this) {
                            notifyAll();
                        }
                    } catch (InterruptedException e) {
                        print("Exiting via interrupted");
                        return;
                    }
                }
            }
        };

        final Runnable task2 = new Runnable() {
            @Override
            public void run() {
                while (true) {
                    long start = System.currentTimeMillis();
                    synchronized (task1) {
                        try {
                            task1.wait();
                        } catch (InterruptedException e) {
                            print("Exiting via interrupted");
                            return;
                        }
                    }
                    long end = System.currentTimeMillis();
                    print("Busy waiting " + (end - start) + " ms");
                    if (Thread.interrupted())
                        return;
                }
            }
        };

        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(task1);
        exec.execute(task2);
        TimeUnit.SECONDS.sleep(1);
        exec.shutdownNow();
        print("WaitNotify.test() ended");
    }
}

public class E22_BusyWaitVSWaitNotify {
    public static void main(String[] args) throws Exception {
        BusyWait.test();
        TimeUnit.MILLISECONDS.sleep(1100);
        print();
        WaitNotify.test();
    }
}
/* Output:
BusyWait.test() started
Busy waiting 103 ms
Busy waiting 98 ms
Busy waiting 102 ms
Busy waiting 103 ms
Busy waiting 100 ms
Busy waiting 105 ms
Busy waiting 104 ms
Busy waiting 105 ms
Busy waiting 101 ms
BusyWait.test() ended
Busy waiting 77 ms
Exiting via interrupted

WaitNotify.test() started
Busy waiting 104 ms
Busy waiting 103 ms
Busy waiting 102 ms
Busy waiting 101 ms
Busy waiting 105 ms
Busy waiting 105 ms
Busy waiting 105 ms
Busy waiting 100 ms
Busy waiting 100 ms
WaitNotify.test() ended
Exiting via interrupted
Exiting via interrupted
 */