package exercises.chapter21;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static net.mindview.util.Print.print;

/**
 * Exercise 17
 * Create a radiation counter that can have
 * any number of remove sensors.
 */
class Count {
    private int count = 0;
    private Random rand = new Random(47);

    public synchronized int increment() {
        int temp = count;
        if (rand.nextBoolean())
            Thread.yield();
        return (count = ++temp);
    }

    public synchronized int value() {
        return count;
    }
}

class Sensor implements Runnable {
    private static Count count = new Count();
    private static List<Sensor> sensors = new ArrayList<>();
    private final int id;
    private int number;
    private static volatile boolean canceled = false;

    public Sensor(int id) {
        this.id = id;
        sensors.add(this);
    }

    public synchronized int getValue() {
        return number;
    }

    public static void cancel() {
        canceled = true;
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

    @Override
    public String toString() {
        return "Sensor " + id + ": " + getValue();
    }

    public static int getTotalCount() {
        return count.value();
    }

    public static int sumSensors() {
        int sum = 0;
        for (Sensor sensor : sensors)
            sum += sensor.getValue();
        return sum;
    }
}

public class E17_RadiationCounter {
    public static void main(String[] args) throws Exception {
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++)
            exec.execute(new Sensor(i));
        TimeUnit.SECONDS.sleep(3);
        Sensor.cancel();
        exec.shutdown();
        if (!exec.awaitTermination(250, TimeUnit.MILLISECONDS))
            print("Some tasks were not terminated!");
        print("Total: " + Sensor.getTotalCount());
        print("Sum of Sensors: " + Sensor.sumSensors());
    }
}
/* Output: (Sample) {
Sensor 3: 1 Total: 4
Sensor 0: 1 Total: 1
Sensor 2: 1 Total: 2
Sensor 1: 1 Total: 3
Sensor 4: 1 Total: 5
Sensor 2: 2 Total: 8
Sensor 0: 2 Total: 7
Sensor 3: 2 Total: 6
Sensor 4: 2 Total: 10
Sensor 1: 2 Total: 9
Sensor 2: 3 Total: 12
...
Sensor 2: 28 Total: 137
Sensor 0: 28 Total: 138
Sensor 1: 28 Total: 139
Sensor 3: 28 Total: 140
Sensor 4: 29 Total: 141
Sensor 3: 29 Total: 143
Sensor 2: 29 Total: 142
Sensor 1: 29 Total: 145
Sensor 0: 29 Total: 144
Stopping Sensor 1: 29
Stopping Sensor 0: 29
Stopping Sensor 4: 29
Stopping Sensor 3: 29
Stopping Sensor 2: 29
Total: 145
Sum of Sensors: 145
 */