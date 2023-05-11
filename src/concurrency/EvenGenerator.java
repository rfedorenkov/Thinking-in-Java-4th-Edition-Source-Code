package concurrency;

/**
 * When threads collide.
 */
public class EvenGenerator extends IntGenerator {
    private int currentEvenValue = 0;

    @Override
    public int next() {
        ++currentEvenValue; // Danger point here!
        ++currentEvenValue;
        return currentEvenValue;
    }

    public static void main(String[] args) {
        EvenChecker.test(new EvenGenerator());
    }
}
/* Output: (Sample)
Press Control-C to exit
1335 not even!
1333 not even!
1325 not even!
1341 not even!
1339 not even!
1327 not even!
1323 not even!
1337 not even!
1329 not even!
1331 not even!
 */