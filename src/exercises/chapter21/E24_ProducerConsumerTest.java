package exercises.chapter21;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static net.mindview.util.Print.print;

/**
 * Exercise 24
 * Solve a single-producer, single-consumer problem using wait()
 * and notifyAll(). The producer must not overflow the receiver's
 * buffer, which can happen if the producer is faster than the consumer.
 * If the consumer is faster than the producer, then it must not read
 * the same data more than once. Do not assume anything about the relative
 * speeds of the producer of consumer.
 */
class Item {
    private static int count = 0;
    private final int id = ++count;

    @Override
    public String toString() {
        return "Item #" + id;
    }
}

class Market {
    private Queue<Item> queue = new LinkedList<>();

    public synchronized void add(Item item) throws InterruptedException {
        while (queue.size() >= 10)
            wait();
        queue.add(item);
        notifyAll();
    }

    public synchronized Item get() throws InterruptedException {
        while (queue.isEmpty())
            wait();
        Item item = queue.poll();
        notifyAll();
        return item;
    }
}

class Producer implements Runnable {
    private final Market market;
    private final int sleepTimeout;

    public Producer(Market market, int sleepTimeout) {
        this.market = market;
        this.sleepTimeout = sleepTimeout;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                TimeUnit.MILLISECONDS.sleep(sleepTimeout);
                Item item = new Item();
                market.add(item);
                print("Produced " + item);
            }
        } catch (InterruptedException e) {
            print("Producer interrupted");
        }
    }
}

class Consumer implements Runnable {
    private final Market market;
    private final int sleepTimeout;

    public Consumer(Market market, int sleepTimeout) {
        this.market = market;
        this.sleepTimeout = sleepTimeout;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                Item item = market.get();
                print("Consumed " + item);
                TimeUnit.MILLISECONDS.sleep(sleepTimeout);
            }
        } catch (InterruptedException e) {
            print("Consumer interrupted");
        }
    }
}

public class E24_ProducerConsumerTest {
    public static void test(int timeProducer, int timeConsumer) throws InterruptedException {
        print("TEST [Producer vs Consumer " + timeProducer + ":" + timeConsumer + "]");
        ExecutorService exec = Executors.newCachedThreadPool();
        Market market = new Market();
        exec.execute(new Producer(market, timeProducer));
        exec.execute(new Consumer(market, timeConsumer));
        TimeUnit.SECONDS.sleep(1);
        exec.shutdownNow();
    }

    public static void main(String[] args) throws Exception {
        test(1, 200);
        TimeUnit.SECONDS.sleep(1);
        test(200, 1);
    }
}
/* Output:
TEST [Producer vs Consumer 1:200]
Produced Item #1
Consumed Item #1
Produced Item #2
Produced Item #3
Produced Item #4
Produced Item #5
Produced Item #6
Produced Item #7
Produced Item #8
Produced Item #9
Produced Item #10
Produced Item #11
Consumed Item #2
Produced Item #12
Consumed Item #3
Produced Item #13
Produced Item #14
Consumed Item #4
Consumed Item #5
Produced Item #15
Producer interrupted
Consumer interrupted
TEST [Producer vs Consumer 200:1]
Produced Item #17
Consumed Item #17
Consumed Item #18
Produced Item #18
Consumed Item #19
Produced Item #19
Produced Item #20
Consumed Item #20
Consumer interrupted
Producer interrupted
 */