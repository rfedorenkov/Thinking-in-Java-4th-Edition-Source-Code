package exercises.chapter21;

import java.util.concurrent.TimeUnit;

/**
 * Exercise 18
 * Create a non-task class with a method that calls sleep()
 * for a long interval. Create a task that calls the method
 * in the non-task class. In main(), start the task, then call
 * interrupt() to terminate it. Make sure that the task shuts
 * down safely.
 */
class NonTask {
    static void sleepLong() throws InterruptedException {
        System.out.println("Sleep long...");
        TimeUnit.SECONDS.sleep(100);
        System.out.println("Wake up!");
    }
}

class Task2 implements Runnable {
    @Override
    public void run() {
        try {
            NonTask.sleepLong();
        } catch (InterruptedException e) {
            System.out.println("Sleep interrupted");
        }
    }
}

public class E18_Interrupting2 {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new Task2());
        t.start();
        TimeUnit.SECONDS.sleep(1);
        t.interrupt();
    }
}
/* Output:
Sleep long...
Sleep interrupted
 */