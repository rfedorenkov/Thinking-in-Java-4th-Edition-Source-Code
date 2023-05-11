package concurrency;

/**
 * Adding more threads.
 */
public class MoreBasicThreads {
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++)
            new Thread(new LiftOff()).start();
        System.out.println("Waiting for LiftOff");
    }
}
/* Output: (Sample)
Waiting for LiftOff
#0(9), #3(9), #3(8), #3(7), #3(6), #3(5), #2(9), #4(9), #2(8), #2(7), #2(6), #2(5), #2(4), #3(4), #1(9), #0(8), #3(3), #3(2), #3(1), #1(8), #2(3), #2(2), #3(Liftoff!), #4(8), #4(7), #4(6), #4(5), #4(4), #4(3), #2(1), #2(Liftoff!), #1(7), #0(7), #0(6), #1(6), #4(2), #0(5), #4(1), #1(5), #1(4), #1(3), #0(4), #0(3), #1(2), #1(1), #4(Liftoff!), #0(2), #0(1), #0(Liftoff!), #1(Liftoff!),
 */