package exercises.chapter21;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Exercise 12
 * Repair AtomicityTest.java using the synchronized keyword.
 * Can you demonstrate that it is now correct?
 */
public class E12_AtomicityTest2 implements Runnable {
    private int i = 0;

    public synchronized int getValue() {
        return i;
    }

    private synchronized void evenIncrement() {
        i++;
        i++;
    }

    @Override
    public void run() {
        while (true)
            evenIncrement();
    }

    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        E12_AtomicityTest2 at = new E12_AtomicityTest2();
        exec.execute(at);
        while (true) {
            int val = at.getValue();
            if (val % 2 != 0) {
                System.out.println(val);
                System.exit(0);
            }
        }
    }
}