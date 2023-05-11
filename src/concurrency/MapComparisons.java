package concurrency;

import net.mindview.util.CountingGenerator;
import net.mindview.util.MapData;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * {Args: 1 10 10} (Fast verification check during build)
 * Rough comparison of thread-safe Map performance.
 */
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

public class MapComparisons {
    public static void main(String[] args) {
        Tester.initMain(args);
        new SynchronizedHashMapTest(10, 0);
        new SynchronizedHashMapTest(9, 1);
        new SynchronizedHashMapTest(1, 5);
        new ConcurrentHashMapTest(10, 0);
        new ConcurrentHashMapTest(9, 1);
        new ConcurrentHashMapTest(5, 5);
        Tester.exec.shutdown();
    }
}
/* Output: (Sample)
Type                             Read time     Write time
Synched HashMap 10r 0w             1671082              0
Synched HashMap 9r 1w              3290874         189292
readTime + writeTime =             3480166
Synched HashMap 1r 5w               193000        1212708
readTime + writeTime =             1405708
ConcurrentHashMap 10r 0w            470666              0
ConcurrentHashMap 9r 1w            1176543          10792
readTime + writeTime =             1187335
ConcurrentHashMap 5r 5w             400668        2423167
readTime + writeTime =             2823835
 */