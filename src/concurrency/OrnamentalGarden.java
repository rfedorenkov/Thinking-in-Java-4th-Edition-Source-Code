package concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static net.mindview.util.Print.print;

class Count {
    private int count = 0;
    private Random rand = new Random(47);

    // Remove the synchronized keyword to see counting fail:
    public synchronized int increment() {
        int temp = count;
        if (rand.nextBoolean()) // Yield half the time
            Thread.yield();
        return (count = ++temp);
    }

    public synchronized int value() {
        return count;
    }
}

class Entrance implements Runnable {
    private static Count count = new Count();
    private static List<Entrance> entrances = new ArrayList<>();
    private int number = 0;
    // Doesn't need synchronization to read:
    private final int id;
    private static volatile boolean canceled = false;

    // Atomic operation on a volatile field:
    public static void cancel() {
        canceled = true;
    }

    public Entrance(int id) {
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
        for (Entrance entrance : entrances)
            sum += entrance.getValue();
        return sum;
    }
}

public class OrnamentalGarden {
    public static void main(String[] args) throws Exception {
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++)
            exec.execute(new Entrance(i));
        // Run for a while, then stop and collect the data:
        TimeUnit.SECONDS.sleep(3);
        Entrance.cancel();
        exec.shutdown();
        if (!exec.awaitTermination(250, TimeUnit.MILLISECONDS))
            print("Some tasks were not terminated!");
        print("Total: " + Entrance.getTotalCount());
        print("Sum of Entrances: " + Entrance.sumEntrances());
    }
}
/* Output: (Sample)
Entrance 2: 1 Total: 2
Entrance 3: 1 Total: 5
Entrance 1: 1 Total: 3
Entrance 0: 1 Total: 1
Entrance 4: 1 Total: 4
Entrance 3: 2 Total: 7
Entrance 0: 2 Total: 9
Entrance 2: 2 Total: 6
Entrance 1: 2 Total: 8
Entrance 4: 2 Total: 10
Entrance 3: 3 Total: 11
Entrance 1: 3 Total: 13
Entrance 0: 3 Total: 12
Entrance 4: 3 Total: 14
Entrance 2: 3 Total: 15
Entrance 4: 4 Total: 16
Entrance 3: 4 Total: 17
Entrance 2: 4 Total: 18
Entrance 1: 4 Total: 19
...
Entrance 3: 28 Total: 138
Entrance 2: 28 Total: 139
Entrance 0: 28 Total: 140
Entrance 4: 29 Total: 141
Entrance 1: 29 Total: 142
Entrance 3: 29 Total: 143
Entrance 2: 29 Total: 144
Entrance 0: 29 Total: 145
Stopping Entrance 4: 29
Stopping Entrance 1: 29
Stopping Entrance 3: 29
Stopping Entrance 0: 29
Stopping Entrance 2: 29
Total: 145
Sum of Entrances: 145
 */