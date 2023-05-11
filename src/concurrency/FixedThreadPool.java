package concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FixedThreadPool {
    public static void main(String[] args) {
        // Constructor argument is number of threads:
        ExecutorService exec = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++)
            exec.execute(new LiftOff());
        exec.shutdown();
    }
}
/* Output: (Sample)
#4(9), #3(9), #4(8), #1(9), #0(9), #0(8), #4(7), #3(8), #0(7), #2(9), #2(8), #0(6), #0(5), #4(6), #2(7), #4(5), #4(4), #4(3), #3(7), #3(6), #1(8), #3(5), #3(4), #4(2), #2(6), #0(4), #2(5), #0(3), #2(4), #3(3), #4(1), #1(7), #3(2), #4(Liftoff!), #0(2), #0(1), #0(Liftoff!), #2(3), #3(1), #3(Liftoff!), #1(6), #2(2), #1(5), #1(4), #2(1), #2(Liftoff!), #1(3), #1(2), #1(1), #1(Liftoff!),
 */