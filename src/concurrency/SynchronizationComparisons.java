package concurrency;

import java.util.Random;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static net.mindview.util.Print.print;
import static net.mindview.util.Print.printf;

/**
 * Comparing the performance of explicit Locks
 * and Atomics versus the synchronized keyword.
 */
abstract class Accumulator {
    public static long cycles = 50000L;
    // Number of Modifiers and Readers during each test:
    private static final int N = 4;
    public static ExecutorService exec = Executors.newFixedThreadPool(N * 2);
    private static CyclicBarrier barrier = new CyclicBarrier(N * 2 + 1);
    protected volatile int index = 0;
    protected volatile long value = 0;
    protected long duration = 0;
    protected String id = "error";
    protected final static int SIZE = 100000;
    protected static int[] preLoaded = new int[SIZE];

    static {
        // Load the array of random numbers:
        Random rand = new Random(47);
        for (int i = 0; i < SIZE; i++)
            preLoaded[i] = rand.nextInt();
    }

    public abstract void accumulate();

    public abstract long read();

    private class Modifier implements Runnable {
        @Override
        public void run() {
            for (long i = 0; i < cycles; i++)
                accumulate();
            try {
                barrier.await();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    private class Reader implements Runnable {
        private volatile long value;

        @Override
        public void run() {
            for (long i = 0; i < cycles; i++)
                value = read();
            try {
                barrier.await();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void timedTest() {
        long start = System.nanoTime();
        for (int i = 0; i < N; i++) {
            exec.execute(new Modifier());
            exec.execute(new Reader());
        }
        try {
            barrier.await();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        duration = System.nanoTime() - start;
        printf("%-13s: %13d\n", id, duration);
    }

    public static void report(Accumulator acc1, Accumulator acc2) {
        printf("%-22s: %.2f\n", acc1.id + "/" + acc2.id,
                (double) acc1.duration / (double) acc2.duration);
    }
}

class BaseLine extends Accumulator {
    {
        id = "BaseLine";
    }

    @Override
    public void accumulate() {
        value += preLoaded[index];
        if (index >= SIZE) index = 0;
    }

    @Override
    public long read() {
        return value;
    }
}

class SynchronizedTest extends Accumulator {
    {
        id = "synchronized";
    }

    @Override
    public synchronized void accumulate() {
        value += preLoaded[index++];
        if (index >= SIZE) index = 0;
    }

    @Override
    public synchronized long read() {
        return value;
    }
}

class LockTest extends Accumulator {
    {
        id = "Lock";
    }

    private Lock lock = new ReentrantLock();

    @Override
    public void accumulate() {
        lock.lock();
        try {
            value += preLoaded[index++];
            if (index >= SIZE) index = 0;
        } finally {
            lock.unlock();
        }
    }

    @Override
    public long read() {
        lock.lock();
        try {
            return value;
        } finally {
            lock.unlock();
        }
    }
}

class AtomicTest extends Accumulator {
    {
        id = "Atomic";
    }

    private AtomicInteger index = new AtomicInteger(0);
    private AtomicLong value = new AtomicLong(0);

    @Override
    public void accumulate() {
        // Oops! Relying on more than one Atomic at
        // a time doesn't work. But it still gives us
        // a performance indicator:
        int i = index.getAndIncrement();
        if (++i >= SIZE) {
            index.set(0);
            i = 0;
        }
        value.getAndAdd(preLoaded[i]);
    }

    @Override
    public long read() {
        return value.get();
    }
}

public class SynchronizationComparisons {
    static BaseLine baseLine = new BaseLine();
    static SynchronizedTest synch = new SynchronizedTest();
    static LockTest lock = new LockTest();
    static AtomicTest atomic = new AtomicTest();

    static void test() {
        print("============================");
        printf("%-12s : %13d\n", "Cycles", Accumulator.cycles);
        baseLine.timedTest();
        synch.timedTest();
        lock.timedTest();
        atomic.timedTest();
        Accumulator.report(synch, baseLine);
        Accumulator.report(lock, baseLine);
        Accumulator.report(atomic, baseLine);
        Accumulator.report(synch, lock);
        Accumulator.report(synch, atomic);
        Accumulator.report(lock, atomic);
    }

    public static void main(String[] args) {
        int iterations = 5; // Default
        if (args.length > 0) // Optionally change iterations
            iterations = new Integer(args[0]);
        // The first time fills the thread pool:
        print("Warmup");
        baseLine.timedTest();
        // Now the initial test doesn't include the cost
        // of starting the threads for the first time.
        // Produce multiple data points:
        for (int i = 0; i < iterations; i++) {
            test();
            Accumulator.cycles *= 2;
        }
        Accumulator.exec.shutdownNow();
    }
}
/* Output: (Sample)
Warmup
BaseLine     :      22243792
============================
Cycles       :         50000
BaseLine     :      14077875
synchronized :      31309208
Lock         :      15985542
Atomic       :      19127833
synchronized/BaseLine : 2.22
Lock/BaseLine         : 1.14
Atomic/BaseLine       : 1.36
synchronized/Lock     : 1.96
synchronized/Atomic   : 1.64
Lock/Atomic           : 0.84
============================
Cycles       :        100000
BaseLine     :      18236042
synchronized :      97442291
Lock         :      17683084
Atomic       :      28912750
synchronized/BaseLine : 5.34
Lock/BaseLine         : 0.97
Atomic/BaseLine       : 1.59
synchronized/Lock     : 5.51
synchronized/Atomic   : 3.37
Lock/Atomic           : 0.61
============================
Cycles       :        200000
BaseLine     :      37857375
synchronized :     183312500
Lock         :      30822833
Atomic       :      57938333
synchronized/BaseLine : 4.84
Lock/BaseLine         : 0.81
Atomic/BaseLine       : 1.53
synchronized/Lock     : 5.95
synchronized/Atomic   : 3.16
Lock/Atomic           : 0.53
============================
Cycles       :        400000
BaseLine     :      72036500
synchronized :     354549542
Lock         :      59060875
Atomic       :     127289875
synchronized/BaseLine : 4.92
Lock/BaseLine         : 0.82
Atomic/BaseLine       : 1.77
synchronized/Lock     : 6.00
synchronized/Atomic   : 2.79
Lock/Atomic           : 0.46
============================
Cycles       :        800000
BaseLine     :     143909959
synchronized :     709624708
Lock         :     118987041
Atomic       :     242489708
synchronized/BaseLine : 4.93
Lock/BaseLine         : 0.83
Atomic/BaseLine       : 1.69
synchronized/Lock     : 5.96
synchronized/Atomic   : 2.93
Lock/Atomic           : 0.49
 */