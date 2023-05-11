package exercises.chapter21;

import concurrency.LiftOff;

/**
 * Exercise 8
 * Modify MoreBasicThreads.java so that all the threads
 * are daemon thread, and verify that the program ends
 * as soon as main() is able to exit.
 */
public class E08_MoreBasicDaemonThreads {
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            Thread t = new Thread(new LiftOff());
            t.setDaemon(true);
            t.start();
        }
        System.out.println("Waiting for LiftOff");
    }
}
/* Output:
Waiting for LiftOff
 */