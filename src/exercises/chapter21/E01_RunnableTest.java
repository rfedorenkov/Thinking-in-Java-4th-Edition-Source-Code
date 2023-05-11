package exercises.chapter21;

/**
 * Exercise 1
 * Implement a Runnable. Inside run(), print a message,
 * and then call yield(). Repeat this three times, and
 * then return from run(). Put a startup message in the
 * constructor and a shutdown message when the task
 * terminates. Create a number of these tasks and drive
 * them using threads.
 */
class Task implements Runnable {
    private static int taskCount = 0;
    private final int id = taskCount++;

    public Task() {
        System.out.println("Created, id = " + id);
    }

    @Override
    public void run() {
        for (int i = 1; i <= 3; i++) {
            System.out.println("Stage #" + i + ", id = " + id);
            Thread.yield();
        }
        System.out.println("End run()");
    }
}
public class E01_RunnableTest {
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++)
            new Thread(new Task()).start();
    }
}
/* Output: (Sample)
Created, id = 0
Created, id = 1
Created, id = 2
Created, id = 3
Created, id = 4
Stage #1, id = 2
Stage #2, id = 2
Stage #3, id = 2
End run()
Stage #1, id = 1
Stage #2, id = 1
Stage #1, id = 4
Stage #1, id = 0
Stage #1, id = 3
Stage #2, id = 4
Stage #3, id = 4
Stage #2, id = 3
End run()
Stage #3, id = 1
End run()
Stage #2, id = 0
Stage #3, id = 3
Stage #3, id = 0
End run()
End run()
 */