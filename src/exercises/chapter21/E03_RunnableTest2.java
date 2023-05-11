package exercises.chapter21;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Exercise 3
 * Repeat Exercise 1 using the different types
 * of executors shown in this section.
 */
public class E03_RunnableTest2 {
    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++)
            exec.execute(new Task());
        exec.shutdown();
        Thread.yield();
        exec = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++)
            exec.execute(new Task());
        exec.shutdown();
        Thread.yield();
        exec = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 5; i++)
            exec.execute(new Task());
        exec.shutdown();
        Thread.yield();
    }
}
/* Output: (Sample)
Created, id = 0
Created, id = 1
Created, id = 2
Created, id = 3
Created, id = 4
Created, id = 5
Created, id = 6
Created, id = 7
Created, id = 8
Created, id = 9
Created, id = 10
Created, id = 11
Created, id = 12
Created, id = 13
Created, id = 14
Stage #1, id = 9
Stage #1, id = 3
Stage #1, id = 0
Stage #1, id = 8
Stage #1, id = 10
Stage #1, id = 7
Stage #1, id = 1
Stage #2, id = 9
Stage #2, id = 7
Stage #2, id = 10
Stage #2, id = 0
Stage #2, id = 3
Stage #1, id = 6
Stage #3, id = 0
Stage #3, id = 3
Stage #1, id = 5
Stage #3, id = 10
End run()
Stage #3, id = 7
End run()
Stage #2, id = 1
Stage #2, id = 8
Stage #3, id = 1
End run()
Stage #3, id = 9
Stage #3, id = 8
Stage #1, id = 11
Stage #1, id = 4
Stage #2, id = 4
Stage #3, id = 4
End run()
End run()
Stage #2, id = 6
Stage #2, id = 5
Stage #3, id = 6
End run()
End run()
Stage #1, id = 2
Stage #3, id = 5
Stage #2, id = 2
Stage #3, id = 2
Stage #2, id = 11
End run()
End run()
End run()
End run()
Stage #3, id = 11
End run()
Stage #1, id = 12
Stage #2, id = 12
Stage #3, id = 12
End run()
Stage #1, id = 13
Stage #2, id = 13
Stage #3, id = 13
End run()
Stage #1, id = 14
Stage #2, id = 14
Stage #3, id = 14
End run()
 */