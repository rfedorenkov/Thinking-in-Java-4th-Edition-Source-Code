package exercises.chapter21;

import net.mindview.util.*;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Exercise 40
 * Following the example of ReaderWriterList.java, create
 * a ReaderWriterMap using a HashMap. Investigate its
 * performance by modifying MapComparisons.java. How does
 * it compare to a synchronized HashMap and a ConcurrentHashMap?
 * {Args: 1 10 10} (Fast verification check during build)
 */
abstract class Tester<C> {
    static int testReps = 10;
    static int testCycles = 1000;
    static int containerSize = 1000;

    abstract C containerInitializer();
    abstract void startReadersAndWriters();

    C testContainer;
    String testId;
    int nReaders;
    int nWriters;
    volatile long readResult = 0;
    volatile long readTime = 0;
    volatile long writeTime = 0;
    CountDownLatch endLatch;
    static ExecutorService exec = Executors.newCachedThreadPool();
    Integer[] writeData;

    Tester(String testId, int nReaders, int nWriters) {
        this.testId = testId + " " +
                nReaders + "r " + nWriters + "w";
        this.nReaders = nReaders;
        this.nWriters = nWriters;
        writeData = Generated.array(Integer.class,
                new RandomGenerator.Integer(), containerSize);
        for (int i = 0; i < testReps; i++) {
            runTest();
            readTime = 0;
            writeTime = 0;
        }
    }

    void runTest() {
        endLatch = new CountDownLatch(nReaders + nWriters);
        testContainer = containerInitializer();
        startReadersAndWriters();
        try {
            endLatch.await();
        } catch (InterruptedException e) {
            System.out.println("endLatch interrupted");
        }
        System.out.printf("%-27s %14d %14d\n", testId, readTime, writeTime);
        if (readTime != 0 && writeTime != 0)
            System.out.printf("%-27s %14d\n", "readTime + writeTime = ", readTime + writeTime);
    }

    abstract class TestTask implements Runnable {
        abstract void test();
        abstract void putResults();

        long duration;

        @Override
        public void run() {
            long startTime = System.nanoTime();
            test();
            duration = System.nanoTime() - startTime;
            synchronized (Tester.this) {
                putResults();
            }
            endLatch.countDown();
        }
    }

    public static void initMain(String[] args) {
        if (args.length > 0)
            testReps = new Integer(args[0]);
        if (args.length > 1)
            testCycles = new Integer(args[1]);
        if (args.length > 2)
            containerSize = new Integer(args[2]);
        System.out.printf("%-27s %14s %14s\n",
                "Type", "Read time", "Write time");
    }
}

abstract class MapTest extends Tester<Map<Integer, Integer>> {
    MapTest(String testId, int nReaders, int nWriters) {
        super(testId, nReaders, nWriters);
    }

    class Reader extends TestTask {
        long result = 0;

        @Override
        void test() {
            for (long i = 0; i < testCycles; i++)
                for (int index = 0; index < containerSize; index++)
                    result += testContainer.get(index);
        }

        @Override
        void putResults() {
            readResult += result;
            readTime += duration;
        }
    }

    class Writer extends TestTask {
        @Override
        void test() {
            for (long i = 0; i < testCycles; i++)
                for (int index = 0; index < containerSize; index++)
                    testContainer.put(index, writeData[index]);
        }

        @Override
        void putResults() {
            writeTime += duration;
        }
    }

    @Override
    void startReadersAndWriters() {
        for (int i = 0; i < nReaders; i++)
            exec.execute(new Reader());
        for (int i = 0; i < nWriters; i++)
            exec.execute(new Writer());
    }
}

class SynchronizedHashMapTest extends MapTest {
    @Override
    Map<Integer, Integer> containerInitializer() {
        return Collections.synchronizedMap(MapData.map(
                new CountingGenerator.Integer(),
                new CountingGenerator.Integer(),
                containerSize));
    }

    SynchronizedHashMapTest(int nReaders, int nWriters) {
        super("Synched HashMap", nReaders, nWriters);
    }
}

class ConcurrentHashMapTest extends MapTest {
    @Override
    Map<Integer, Integer> containerInitializer() {
        return new ConcurrentHashMap<>(MapData.map(
                new CountingGenerator.Integer(),
                new CountingGenerator.Integer(),
                containerSize));
    }

    ConcurrentHashMapTest(int nReaders, int nWriters) {
        super("ConcurrentHashMap", nReaders, nWriters);
    }
}

class ReaderWriterMap<K, V> extends AbstractMap<K, V> {
    private HashMap<K, V> lockedMap;
    // Make the ordering fair:
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock(true);

    public ReaderWriterMap(Generator<K> gen, int size, V initialValue) {
        lockedMap = new HashMap<>(MapData.map(gen, initialValue, size));
    }

    @Override
    public V put(K key, V value) {
        Lock wlock = lock.writeLock();
        wlock.lock();
        try {
            return lockedMap.put(key, value);
        } finally {
            wlock.unlock();
        }
    }

    @Override
    public V get(Object key) {
        Lock rlock = lock.readLock();
        rlock.lock();
        try {
            // Uncomment if you want to show that
            // multiple readers can acquire a read lock:
            // if (lock.getReadLockCount() > 1)
            //    print(lock.getReadLockCount());
            return lockedMap.get(key);
        } finally {
            rlock.unlock();
        }
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return lockedMap.entrySet();
    }
}

class ReaderWriterMapTest extends MapTest {
    @Override
    Map<Integer, Integer> containerInitializer() {
        return new ReaderWriterMap<Integer, Integer>(
                new CountingGenerator.Integer(), containerSize, 1);
    }

    ReaderWriterMapTest(int nReaders, int nWriters) {
        super("ReaderWriterMap", nReaders, nWriters);
    }
}

public class E40_MapComparisons2 {
    public static void main(String[] args) {
        Tester.initMain(args);
        new SynchronizedHashMapTest(10, 0);
        new SynchronizedHashMapTest(9, 1);
        new SynchronizedHashMapTest(1, 5);
        new ConcurrentHashMapTest(10, 0);
        new ConcurrentHashMapTest(9, 1);
        new ConcurrentHashMapTest(5, 5);
        new ReaderWriterMapTest(10, 0);
        new ReaderWriterMapTest(9, 1);
        new ReaderWriterMapTest(5, 5);
        Thread.yield();
        Tester.exec.shutdown();
    }
}
/* Output: (Sample)
Type                             Read time     Write time
Synched HashMap 10r 0w             2384957              0
Synched HashMap 9r 1w              3924708         151000
readTime + writeTime =             4075708
Synched HashMap 1r 5w                 8000        1399625
readTime + writeTime =             1407625
ConcurrentHashMap 10r 0w            359667              0
ConcurrentHashMap 9r 1w            1304875         202834
readTime + writeTime =             1507709
ConcurrentHashMap 5r 5w             500250        3709209
readTime + writeTime =             4209459
ReaderWriterMap 10r 0w            14829958              0
ReaderWriterMap 9r 1w             67533874        7709958
readTime + writeTime =            75243832
ReaderWriterMap 5r 5w             27722001       27645917
readTime + writeTime =            55367918
 */