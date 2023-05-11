package concurrency;

import java.util.concurrent.locks.*;

/**
 * Using explicit Lock objects to create critical sections.
 */
// Synchronize the entire method:
class ExplicitPairManager1 extends PairManager {
    private Lock lock = new ReentrantLock();

    @Override
    public synchronized void increment() {
        lock.lock();
        try {
            p.incrementX();
            p.incrementY();
            store(getPair());
        } finally {
            lock.unlock();
        }
    }
}

// Use a critical section:
class ExplicitPairManager2 extends PairManager {
    private Lock lock = new ReentrantLock();

    @Override
    public void increment() {
        Pair temp;
        lock.lock();
        try {
            p.incrementX();
            p.incrementY();
            temp = getPair();
        } finally {
            lock.unlock();
        }
        store(temp);
    }

    @Override
    public Pair getPair() {
        lock.lock();
        try {
            return new Pair(p.getX(), p.getY());
        } finally {
            lock.unlock();
        }
    }
}

public class ExplicitCriticalSection {
    public static void main(String[] args) {
        PairManager
                pman1 = new ExplicitPairManager1(),
                pman2 = new ExplicitPairManager2();
        CriticalSection.testApproaches(pman1, pman2);
    }
}
/* Output:
pm1: Pair: x: 331, y: 331 checkCounter = 18
pm2: Pair: x: 332, y: 332 checkCounter = 1376088308
 */