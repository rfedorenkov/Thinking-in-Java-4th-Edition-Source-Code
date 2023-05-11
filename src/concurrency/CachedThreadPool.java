package concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CachedThreadPool {
    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++)
            exec.execute(new LiftOff());
        exec.shutdown();
    }
}
/* Output: (Sample)
#0(9), #4(9), #4(8), #3(9), #0(8), #0(7), #0(6), #4(7), #2(9), #1(9), #0(5), #3(8), #2(8), #0(4), #1(8), #0(3), #4(6), #4(5), #2(7), #3(7), #4(4), #2(6), #1(7), #0(2), #2(5), #1(6), #3(6), #4(3), #3(5), #1(5), #2(4), #1(4), #0(1), #0(Liftoff!), #1(3), #3(4), #4(2), #2(3), #1(2), #2(2), #3(3), #2(1), #2(Liftoff!), #1(1), #4(1), #4(Liftoff!), #1(Liftoff!), #3(2), #3(1), #3(Liftoff!),
 */