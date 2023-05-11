package concurrency;

import net.mindview.util.DaemonThreadFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static net.mindview.util.Print.print;

/**
 * Using a Thread Factory to create daemons.
 */
public class DaemonFromFactory implements Runnable {
    @Override
    public void run() {
        try {
            while (true) {
                TimeUnit.MILLISECONDS.sleep(100);
                print(Thread.currentThread() + " " + this);
            }
        } catch (InterruptedException e) {
            print("Interrupted");
        }
    }

    public static void main(String[] args) throws Exception {
        ExecutorService exec = Executors.newCachedThreadPool(
                new DaemonThreadFactory());
        for (int i = 0; i < 10; i++)
            exec.execute(new DaemonFromFactory());
        print("All daemons started");
        TimeUnit.MILLISECONDS.sleep(500); // Run for a while
    }
}
/* Output:
All daemons started
Thread[Thread-5,5,main] concurrency.DaemonFromFactory@1bece401
Thread[Thread-8,5,main] concurrency.DaemonFromFactory@360cc6a7
Thread[Thread-0,5,main] concurrency.DaemonFromFactory@356441be
Thread[Thread-3,5,main] concurrency.DaemonFromFactory@6a4dc710
Thread[Thread-7,5,main] concurrency.DaemonFromFactory@5af6b1c8
Thread[Thread-2,5,main] concurrency.DaemonFromFactory@61f75fc7
Thread[Thread-9,5,main] concurrency.DaemonFromFactory@5e06aff6
Thread[Thread-1,5,main] concurrency.DaemonFromFactory@3b4421dd
Thread[Thread-6,5,main] concurrency.DaemonFromFactory@42399c35
Thread[Thread-4,5,main] concurrency.DaemonFromFactory@3f6c10cf
Thread[Thread-9,5,main] concurrency.DaemonFromFactory@5e06aff6
Thread[Thread-8,5,main] concurrency.DaemonFromFactory@360cc6a7
Thread[Thread-5,5,main] concurrency.DaemonFromFactory@1bece401
Thread[Thread-7,5,main] concurrency.DaemonFromFactory@5af6b1c8
Thread[Thread-2,5,main] concurrency.DaemonFromFactory@61f75fc7
Thread[Thread-3,5,main] concurrency.DaemonFromFactory@6a4dc710
Thread[Thread-0,5,main] concurrency.DaemonFromFactory@356441be
Thread[Thread-6,5,main] concurrency.DaemonFromFactory@42399c35
Thread[Thread-4,5,main] concurrency.DaemonFromFactory@3f6c10cf
Thread[Thread-1,5,main] concurrency.DaemonFromFactory@3b4421dd
Thread[Thread-6,5,main] concurrency.DaemonFromFactory@42399c35
Thread[Thread-7,5,main] concurrency.DaemonFromFactory@5af6b1c8
Thread[Thread-8,5,main] concurrency.DaemonFromFactory@360cc6a7
Thread[Thread-9,5,main] concurrency.DaemonFromFactory@5e06aff6
Thread[Thread-4,5,main] concurrency.DaemonFromFactory@3f6c10cf
Thread[Thread-5,5,main] concurrency.DaemonFromFactory@1bece401
Thread[Thread-2,5,main] concurrency.DaemonFromFactory@61f75fc7
Thread[Thread-3,5,main] concurrency.DaemonFromFactory@6a4dc710
Thread[Thread-0,5,main] concurrency.DaemonFromFactory@356441be
Thread[Thread-1,5,main] concurrency.DaemonFromFactory@3b4421dd
Thread[Thread-4,5,main] concurrency.DaemonFromFactory@3f6c10cf
Thread[Thread-7,5,main] concurrency.DaemonFromFactory@5af6b1c8
Thread[Thread-8,5,main] concurrency.DaemonFromFactory@360cc6a7
Thread[Thread-9,5,main] concurrency.DaemonFromFactory@5e06aff6
Thread[Thread-6,5,main] concurrency.DaemonFromFactory@42399c35
Thread[Thread-0,5,main] concurrency.DaemonFromFactory@356441be
Thread[Thread-5,5,main] concurrency.DaemonFromFactory@1bece401
Thread[Thread-2,5,main] concurrency.DaemonFromFactory@61f75fc7
Thread[Thread-3,5,main] concurrency.DaemonFromFactory@6a4dc710
Thread[Thread-1,5,main] concurrency.DaemonFromFactory@3b4421dd
 */