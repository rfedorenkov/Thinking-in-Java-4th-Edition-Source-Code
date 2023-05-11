package exercises.chapter21;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static net.mindview.util.Print.print;

/**
 * Exercise 19
 * Modify OrnamentalGarden.java so that it uses interrupt().
 */
class Entrance2 implements Runnable {
    private static Count count = new Count();
    private static List<Entrance2> entrances = new ArrayList<>();
    private int number = 0;
    // Doesn't need synchronization to read:
    private final int id;

    public Entrance2(int id) {
        this.id = id;
        // Keep this task in a list. Also prevents
        // garbage collection of dead task:
        entrances.add(this);
    }

    @Override
    public void run() {
        while (true) {
            synchronized (this) {
                ++number;
            }
            print(this + " Total: " + count.increment());
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                print("Stopping " + this);
                return;
            }
        }
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
        for (Entrance2 entrance : entrances)
            sum += entrance.getValue();
        return sum;
    }
}

public class E19_OrnamentalGarden2 {
    public static void main(String[] args) throws Exception {
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++)
            exec.execute(new Entrance2(i));
        // Run for a while, then stop and collect the data:
        TimeUnit.SECONDS.sleep(3);
        exec.shutdownNow();
        if (!exec.awaitTermination(250, TimeUnit.MILLISECONDS))
            print("Some tasks were not terminated!");
        print("Total: " + Entrance2.getTotalCount());
        print("Sum of Entrances: " + Entrance2.sumEntrances());
    }
}
/* Output: (Sample)
Entrance 4: 1 Total: 5
Entrance 1: 1 Total: 2
Entrance 0: 1 Total: 1
Entrance 2: 1 Total: 3
Entrance 3: 1 Total: 4
Entrance 1: 2 Total: 8
Entrance 0: 2 Total: 7
...
Entrance 2: 26 Total: 129
Entrance 1: 27 Total: 131
Entrance 4: 27 Total: 132
Entrance 3: 27 Total: 133
Entrance 0: 27 Total: 134
Entrance 2: 27 Total: 135
Entrance 1: 28 Total: 136
Entrance 3: 28 Total: 137
Entrance 4: 28 Total: 138
Entrance 0: 28 Total: 139
Entrance 2: 28 Total: 140
Entrance 1: 29 Total: 141
Entrance 3: 29 Total: 142
Entrance 4: 29 Total: 143
Entrance 0: 29 Total: 144
Entrance 2: 29 Total: 145
Stopping Entrance 4: 29
Stopping Entrance 2: 29
Stopping Entrance 0: 29
Stopping Entrance 1: 29
Stopping Entrance 3: 29
Total: 145
Sum of Entrances: 145
 */