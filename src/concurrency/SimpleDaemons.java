package concurrency;

import java.util.concurrent.TimeUnit;

import static net.mindview.util.Print.print;

/**
 * Daemon threads don't prevent the program from ending.
 */
public class SimpleDaemons implements Runnable {
    @Override
    public void run() {
        try {
            while (true) {
                TimeUnit.MILLISECONDS.sleep(100);
                print(Thread.currentThread() + " " + this);
            }
        } catch (InterruptedException e) {
            print("sleep() interrupted");
        }
    }

    public static void main(String[] args) throws Exception {
        for (int i = 0; i < 10; i++) {
            Thread daemon = new Thread(new SimpleDaemons());
            daemon.setDaemon(true); // Must call before start()
            daemon.start();
        }
        print("All daemons started");
        TimeUnit.MILLISECONDS.sleep(175);
    }
}
/* Output: (Sample)
All daemons started
Thread[Thread-9,5,main] concurrency.SimpleDaemons@2aeba2fa
Thread[Thread-3,5,main] concurrency.SimpleDaemons@7ff9b034
Thread[Thread-0,5,main] concurrency.SimpleDaemons@654032cb
Thread[Thread-1,5,main] concurrency.SimpleDaemons@4f61f08e
Thread[Thread-2,5,main] concurrency.SimpleDaemons@411fe409
Thread[Thread-6,5,main] concurrency.SimpleDaemons@2de3f350
Thread[Thread-7,5,main] concurrency.SimpleDaemons@67a3c949
Thread[Thread-4,5,main] concurrency.SimpleDaemons@48d8bb3e
Thread[Thread-5,5,main] concurrency.SimpleDaemons@45e775fa
Thread[Thread-8,5,main] concurrency.SimpleDaemons@336d0f29
 */