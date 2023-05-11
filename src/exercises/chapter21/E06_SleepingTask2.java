package exercises.chapter21;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Exercise 6
 * Create a task that sleeps for a random amount of time
 * between 1 and 10 seconds, then displays its sleep time and exits.
 * Create and run a quantity (given on the command line) of these tasks.
 * {Args: 5}
 */
public class E06_SleepingTask2 implements Runnable {
    private static final Random rand = new Random();
    private final int sleeping_time = rand.nextInt(10) + 1;

    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(sleeping_time);
        } catch (InterruptedException e) {
            System.err.println("Interrupted");
        }
        System.out.println(sleeping_time);
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Specify the number of tasks to run the program");
            System.exit(1);
        }
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < Integer.parseInt(args[0]); i++)
            exec.execute(new E06_SleepingTask2());
        Thread.yield();
        exec.shutdown();
    }
}
/* Output: (Sample)
2
3
4
7
9
 */