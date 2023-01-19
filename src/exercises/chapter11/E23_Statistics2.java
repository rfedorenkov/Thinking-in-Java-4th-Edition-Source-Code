package exercises.chapter11;

import java.util.*;

/**
 * Exercise 23
 * Starting with Statistics.java, create a program
 * that runs the test repeatedly and look to see
 * if any one number tends to appear more than
 * the others in the results.
 */
class StatisticsCounter implements Comparable<StatisticsCounter> {
    private int frequency;

    private final int value;

    public StatisticsCounter(int value) {
        this.value = value;
        this.frequency = 1;
    }

    public void increase() {
        frequency++;
    }

    @Override
    public String toString() {
        return "Value = " + value + ": duplication = " + frequency;
    }

    @Override
    public int compareTo(StatisticsCounter o) {
        return Integer.compare(o.frequency, frequency);
    }
}
public class E23_Statistics2 {
    private static final Random rand = new Random(47);
    private static final Map<Integer, StatisticsCounter> map = new HashMap<>();
    public static void test() {
        for (int i = 0; i < 100_000; i++) {
            // Produce a number between 0 and 100;
            int r = rand.nextInt(20);
            if (map.containsKey(r)) {
                map.get(r).increase();
            } else {
                map.put(r, new StatisticsCounter(r));
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++)
            test();
        List<StatisticsCounter> list = new ArrayList<>(map.values());
        Collections.sort(list);
        for (StatisticsCounter counter : list)
            System.out.println(counter);
    }
}
/* Output:
Value = 1: duplication = 501134
Value = 14: duplication = 501101
Value = 4: duplication = 500693
Value = 10: duplication = 500585
Value = 12: duplication = 500492
Value = 15: duplication = 500174
Value = 7: duplication = 500168
Value = 9: duplication = 500141
Value = 13: duplication = 500121
Value = 3: duplication = 500007
Value = 2: duplication = 499998
Value = 18: duplication = 499986
Value = 17: duplication = 499974
Value = 8: duplication = 499938
Value = 11: duplication = 499852
Value = 19: duplication = 499793
Value = 0: duplication = 499578
Value = 16: duplication = 499295
Value = 5: duplication = 498698
Value = 6: duplication = 498272
 */