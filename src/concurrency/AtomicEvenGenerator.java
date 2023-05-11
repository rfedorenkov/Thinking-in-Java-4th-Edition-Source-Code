package concurrency;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Atomic classes are occasionally useful in regular code.
 * {RunByHand}
 */
public class AtomicEvenGenerator extends IntGenerator {
    private AtomicInteger currentEventValue = new AtomicInteger(0);

    @Override
    public int next() {
        return currentEventValue.addAndGet(2);
    }

    public static void main(String[] args) {
        EvenChecker.test(new AtomicEvenGenerator());
    }
}