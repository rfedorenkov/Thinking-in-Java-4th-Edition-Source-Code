package exercises.chapter21;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

/**
 * Exercise 14
 * Demonstrate that java.util.Timer scales to large numbers
 * by creating a program that generates many Timer objects
 * that perform some simple task when the timeout completes.
 */
public class E14_ManyTimersTest {
    public static void main(String[] args) throws Exception {
        Random rand = new Random(47);
        for (int i = 0; i < 20; i++) {
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName() + ": " + System.currentTimeMillis());
                }
            }, rand.nextInt(1000));
        }
        TimeUnit.SECONDS.sleep(1);
        System.exit(0);
    }
}
/* Output:
Timer-11: 1679249399319
Timer-7: 1679249399392
Timer-9: 1679249399399
Timer-19: 1679249399449
Timer-0: 1679249399450
Timer-15: 1679249399471
Timer-10: 1679249399480
Timer-5: 1679249399621
Timer-8: 1679249399710
Timer-18: 1679249399713
Timer-12: 1679249399742
Timer-1: 1679249399747
Timer-13: 1679249399782
Timer-2: 1679249399885
Timer-14: 1679249400002
Timer-3: 1679249400051
Timer-17: 1679249400055
Timer-6: 1679249400060
Timer-4: 1679249400153
Timer-16: 1679249400191
 */