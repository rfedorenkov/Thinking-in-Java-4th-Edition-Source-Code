package exercises.chapter21;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static net.mindview.util.Print.print;

/**
 * Exercise 32
 * Use a CountDownLatch to solve the problem of correlating
 * the results from the Entrances in OrnamentalGarden.java.
 * Remove the unnecessary code from the new version of the example.
 */
class Entrance3 implements Runnable {
    private final CountDownLatch latch;
    private static final Count count = new Count();
    private static final List<Entrance3> entrances = new ArrayList<>();
    private int number = 0;
    // Doesn't need synchronization to read:
    private final int id;
    private static volatile boolean canceled = false;

    // Atomic operation on a volatile field:
    public static void cancel() {
        canceled = true;
    }

    public Entrance3(CountDownLatch latch, int id) {
        this.latch = latch;
        this.id = id;
        // Keep this task in a list. Also prevents
        // garbage collection of dead task:
        entrances.add(this);
    }

    @Override
    public void run() {
        while (!canceled) {
            synchronized (this) {
                ++number;
            }
            print(this + " Total: " + count.increment());
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                print("sleep interrupted");
            }
        }
        latch.countDown();
        print("Stopping " + this);
    }

    public synchronized int getValue() {
        return number;
    }

    @Override
    public String toString() {
        return "Entrance " + id + ": " + getValue();
    }

    public static int getTotalCount() {
        return count.value();
    }

    public static int sumEntrances() {
        int sum = 0;
        for (Entrance3 entrance : entrances)
            sum += entrance.getValue();
        return sum;
    }
}

public class E32_OrnamentalGarden3 {
    public static void main(String[] args) throws Exception {
        CountDownLatch latch = new CountDownLatch(5);
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++)
            exec.execute(new Entrance3(latch, i));
        // Run for a while, then stop and collect the data:
        TimeUnit.SECONDS.sleep(3);
        Entrance3.cancel();
        exec.shutdown();
        latch.await();
        print("Total: " + Entrance3.getTotalCount());
        print("Sum of Entrances: " + Entrance3.sumEntrances());
    }
}
/* Output:
Entrance 4: 1 Total: 5
Entrance 3: 1 Total: 4
Entrance 1: 1 Total: 3
Entrance 2: 1 Total: 2
Entrance 0: 1 Total: 1
Entrance 1: 2 Total: 7
Entrance 3: 2 Total: 8
Entrance 4: 2 Total: 6
Entrance 2: 2 Total: 9
Entrance 0: 2 Total: 10
Entrance 2: 3 Total: 11
Entrance 1: 3 Total: 12
...
Entrance 0: 28 Total: 140
Entrance 1: 29 Total: 141
Entrance 4: 29 Total: 142
Entrance 2: 29 Total: 143
Entrance 3: 29 Total: 144
Entrance 0: 29 Total: 145
Stopping Entrance 1: 29
Stopping Entrance 4: 29
Stopping Entrance 2: 29
Stopping Entrance 3: 29
Stopping Entrance 0: 29
Total: 145
Sum of Entrances: 145
 */