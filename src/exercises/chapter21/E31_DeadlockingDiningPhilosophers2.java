package exercises.chapter21;

import java.util.Random;
import java.util.concurrent.*;

import static net.mindview.util.Print.print;

/**
 * Exercise 31
 * Change DeadlockingDiningPhilosophers.java so that when a philosopher is done
 * with their chopsticks, they drop them into a bin. When a philosopher wants
 * to eat, they take the next two available chopsticks from the bin. Does this
 * eliminate the possibility of deadlock? Can you reintroduce deadlock by simply
 * reducing the number of available chopsticks?
 * {Args: 0 5 timeout}
 */
class Chopstick {
    private boolean taken = false;

    public synchronized void take() throws InterruptedException {
        while (taken)
            wait();
        taken = true;
    }

    public synchronized void drop() {
        taken = false;
        notifyAll();
    }
}

class ChopstickQueue extends LinkedBlockingQueue<Chopstick> {
}

class ChopstickBin {
    private ChopstickQueue bin = new ChopstickQueue();

    public void put(Chopstick c) throws InterruptedException {
        bin.put(c);
    }

    public Chopstick get() throws InterruptedException {
        return bin.take();
    }
}

class Philosopher implements Runnable {
    private ChopstickBin bin;
    private Chopstick right;
    private Chopstick left;
    private final int id;
    private final int ponderFactor;
    private Random rand = new Random(47);

    private void pause() throws InterruptedException {
        if (ponderFactor == 0) return;
        TimeUnit.MILLISECONDS.sleep(rand.nextInt(ponderFactor * 250));
    }

    public Philosopher(ChopstickBin bin, int ident, int ponder) {
        this.bin = bin;
        id = ident;
        ponderFactor = ponder;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                print(this + " thinking");
                pause();
                // Philosopher becomes hungry
                print(this + " grabbing right");
                right = bin.get();
                print(this + " grabbing left");
                left = bin.get();
                print(this + " eating");
                pause();
                bin.put(right);
                bin.put(left);
            }
        } catch (InterruptedException e) {
            print(this + " exiting via interrupt");
        }
    }

    @Override
    public String toString() {
        return "Philosopher " + id;
    }
}

public class E31_DeadlockingDiningPhilosophers2 {
    public static void main(String[] args) throws Exception {
        ChopstickBin bin = new ChopstickBin();
        int ponder = 5;
        if (args.length > 0)
            ponder = Integer.parseInt(args[0]);
        int size = 5;
        if (args.length > 1)
            size = Integer.parseInt(args[1]);
        ExecutorService exec = Executors.newCachedThreadPool();
        for (int i = 0; i < size; i++)
            bin.put(new Chopstick());
        // One extra chopstick ensures that at least one
        // philosopher can eat without being blocked.
        bin.put(new Chopstick()); // Comment this if you want to see deadlock
        for (int i = 0; i < size; i++)
            exec.execute(new Philosopher(bin, i, ponder));
        if (args.length == 3 && args[2].equals("timeout"))
            TimeUnit.SECONDS.sleep(5);
        else {
            System.out.println("Press 'Enter' to quit");
            System.in.read();
        }
        exec.shutdownNow();
    }
}
/* (Execute to see output) */