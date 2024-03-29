package exercises.chapter21;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * Exercise 9
 * Modify SimplePriorities.java so that a custom
 * ThreadFactory sets the priorities of the threads.
 */
class SimplePriorities2 implements Runnable {
    private int countDown = 5;
    private volatile double d; // No optimization

    @Override
    public String toString() {
        return Thread.currentThread() + ": " + countDown;
    }

    @Override
    public void run() {
        while (true) {
            // An expensive, interruptable operation:
            for (int i = 1; i < 100000; i++) {
                d += (Math.PI + Math.E) / (double) i;
                if (i % 1000 == 0)
                    Thread.yield();
            }
            System.out.println(this);
            if (--countDown == 0) return;
        }
    }
}

class PriorityThreadFactory implements ThreadFactory {
    private int priority;

    public PriorityThreadFactory(int priority) {
        this.priority = priority;
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread t = new Thread(r);
        t.setPriority(priority);
        return t;
    }
}

public class E09_SimplePrioritiesThreadFactoryTest {
    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool(
                new PriorityThreadFactory(Thread.MIN_PRIORITY));
        for (int i = 0; i < 5; i++)
            exec.execute(new SimplePriorities2());
        exec = Executors.newCachedThreadPool(
                new PriorityThreadFactory(Thread.MAX_PRIORITY));
        exec.execute(new SimplePriorities2());
        exec.shutdown();
    }
}
/* Output:
Thread[Thread-2,1,main]: 5
Thread[Thread-1,1,main]: 5
Thread[Thread-5,10,main]: 5
Thread[Thread-3,1,main]: 5
Thread[Thread-4,1,main]: 5
Thread[Thread-0,1,main]: 5
Thread[Thread-3,1,main]: 4
Thread[Thread-5,10,main]: 4
Thread[Thread-4,1,main]: 4
Thread[Thread-0,1,main]: 4
Thread[Thread-2,1,main]: 4
Thread[Thread-1,1,main]: 4
Thread[Thread-1,1,main]: 3
Thread[Thread-3,1,main]: 3
Thread[Thread-4,1,main]: 3
Thread[Thread-0,1,main]: 3
Thread[Thread-5,10,main]: 3
Thread[Thread-2,1,main]: 3
Thread[Thread-1,1,main]: 2
Thread[Thread-3,1,main]: 2
Thread[Thread-4,1,main]: 2
Thread[Thread-0,1,main]: 2
Thread[Thread-5,10,main]: 2
Thread[Thread-2,1,main]: 2
Thread[Thread-3,1,main]: 1
Thread[Thread-1,1,main]: 1
Thread[Thread-4,1,main]: 1
Thread[Thread-0,1,main]: 1
Thread[Thread-5,10,main]: 1
Thread[Thread-2,1,main]: 1
 */