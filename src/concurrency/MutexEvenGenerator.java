package concurrency;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Preventing thread collisions with mutexes.
 * {RunByHand}
 */
public class MutexEvenGenerator extends IntGenerator {
    private int currentEvenValue = 0;
    private Lock lock = new ReentrantLock();

    @Override
    public int next() {
        lock.lock();
        try {
            ++currentEvenValue;
            Thread.yield(); // Cause failure faster
            ++currentEvenValue;
        } finally {
            lock.unlock();
        }
        return 0;
    }

    public static void main(String[] args) {
        EvenChecker.test(new MutexEvenGenerator());
    }
}