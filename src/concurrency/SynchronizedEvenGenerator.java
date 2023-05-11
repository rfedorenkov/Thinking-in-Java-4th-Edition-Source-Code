package concurrency;

/**
 * Simplifying mutexes with the synchronized keyword.
 * {RunByHand}
 */
public class SynchronizedEvenGenerator extends IntGenerator {
    private int currentEventValue = 0;

    @Override
    public synchronized int next() {
        ++currentEventValue;
        Thread.yield(); // Cause failure faster
        ++currentEventValue;
        return currentEventValue;
    }

    public static void main(String[] args) {
        EvenChecker.test(new SynchronizedEvenGenerator());
    }
}