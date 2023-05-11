package exercises.chapter21;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static net.mindview.util.Print.print;

/**
 * Exercise 21
 * Create two Runnables, one wit a run() that starts and calls wait().
 * The second class should capture the reference of the first Runnable
 * object. Its run() should call notifyAll() for the first task after
 * some number of seconds have passed so that the first task can display
 * a message. Test your classes using an Executor.
 */
class Waiter implements Runnable {
    @Override
    public void run() {
        print("Waiter call wait()");
        synchronized (this) {
            try {
                wait();
            } catch (InterruptedException e) {
                print("Exiting via interrupt");
            }
        }
        print("Waiter.run() ended");
    }
}

class Notifier implements Runnable {
    private Waiter waiter;

    public Notifier(Waiter waiter) {
        this.waiter = waiter;
    }

    @Override
    public void run() {
        print("Notifier sleep 2 seconds...");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            print("Exiting via interrupt");
        }
        print("Notifier call notifyAll()");
        synchronized (waiter) {
            waiter.notifyAll();
        }
        print("Notifier.run() ended");
    }
}

public class E21_WaiterAndNotifierTest {
    public static void main(String[] args) throws InterruptedException {
        Waiter waiter = new Waiter();
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(waiter);
        exec.execute(new Notifier(waiter));
        TimeUnit.SECONDS.sleep(3);
        exec.shutdownNow(); // Interrupt all tasks
    }
}
/* Output:
Waiter call wait()
Notifier sleep 2 seconds...
Notifier call notifyAll()
Notifier.run() ended
Waiter.run() ended
 */