package concurrency;

import net.mindview.util.CountingIntegerList;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * {Args: 1 10 10} (Fast verification check during build)
 * Rough comparison of thread-safe List performance.
 */
abstract class ListTest extends Tester<List<Integer>> {
    ListTest(String testId, int nReaders, int nWriters) {
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
                    testContainer.set(index, writeData[index]);
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

class SynchronizedArrayListTest extends ListTest {
    @Override
    List<Integer> containerInitializer() {
        return Collections.synchronizedList(
                new ArrayList<>(
                        new CountingIntegerList(containerSize)));
    }

    SynchronizedArrayListTest(int nReaders, int nWriters) {
        super("Synched ArrayList", nReaders, nWriters);
    }
}

class CopyOnWriteArrayListTest extends ListTest {
    @Override
    List<Integer> containerInitializer() {
        return new CopyOnWriteArrayList<>(
                new CountingIntegerList(containerSize));
    }

    CopyOnWriteArrayListTest(int nReaders, int nWriters) {
        super("CopyOnWriteArrayList", nReaders, nWriters);
    }
}

public class ListComparisons {
    public static void main(String[] args) {
        Tester.initMain(args);
        new SynchronizedArrayListTest(10, 0);
        new SynchronizedArrayListTest(9, 1);
        new SynchronizedArrayListTest(5, 5);
        new CopyOnWriteArrayListTest(10, 0);
        new CopyOnWriteArrayListTest(9, 1);
        new CopyOnWriteArrayListTest(5, 5);
        Tester.exec.shutdown();
    }
}
/* Output: (Sample)
Type                             Read time     Write time
Synched ArrayList 10r 0w           2379208              0
Synched ArrayList 9r 1w            2839290          38541
readTime + writeTime =             2877831
Synched ArrayList 5r 5w            1545666        1294792
readTime + writeTime =             2840458
CopyOnWriteArrayList 10r 0w         561376              0
CopyOnWriteArrayList 9r 1w          768876         142792
readTime + writeTime =              911668
CopyOnWriteArrayList 5r 5w           94041         728249
readTime + writeTime =              822290
 */