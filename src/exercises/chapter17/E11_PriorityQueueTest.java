package exercises.chapter17;

import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;

import static net.mindview.util.Print.print;

/**
 * Exercise 11
 * Create a class that contains an Integer that is initialized
 * to a value between 0 and 100 using java.util.Random. Implement
 * Comparable using this Integer field. Fill a PriorityQueue
 * with objects of your class, and extract the values using
 * poll() to show that it produces the expected order.
 */
class Data implements Comparable<Data> {
    private static Random rand = new Random(47);
    private Integer i;

    public Data() {
        i = rand.nextInt(100);
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " #" + i;
    }

    @Override
    public int compareTo(Data o) {
        return Integer.compare(i, o.i);
    }
}

public class E11_PriorityQueueTest {
    public static void main(String[] args) {
        Queue<Data> pq = new PriorityQueue<>();
        for (int i = 0; i < 10; i++)
            pq.add(new Data());
        while (!pq.isEmpty())
            print(pq.poll());
    }
}
/* Output:
Data #0
Data #7
Data #22
Data #29
Data #55
Data #58
Data #61
Data #61
Data #68
Data #93
 */