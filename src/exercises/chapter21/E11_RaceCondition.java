package exercises.chapter21;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Exercise 11
 * Create a class containing two data fields, and a method
 * that manipulates those fields in a multistep process so
 * that, during the execution of that method, those fields
 * are in an "improper state" (according to some definition
 * that you establish). Add methods to read the fields, and
 * create multiple threads to call the various methods and
 * show that the data is visible in its "improper state".
 * Fix the problem using the synchronized keyword.
 */
class Bathroom {
    private boolean lock = false;
    private int numberOfPeople;

    public int getValue() {
        return numberOfPeople;
    }

    public boolean isLock() {
        return lock;
    }

    public void enter() {
        numberOfPeople = 1;
        Thread.yield();
        lock = true;
    }

    public void leave() {
        numberOfPeople = 0;
        Thread.yield();
        lock = false;
    }

    public void isOk() {
        if ((lock && numberOfPeople == 0) ||
                (!lock && numberOfPeople == 1)) {
            throw new IllegalStateException("[" + lock + " : " + numberOfPeople + "]");
        }
    }
}

class SafeBathroom extends Bathroom {
    @Override
    public synchronized void enter() {
        super.enter();
    }

    @Override
    public synchronized void leave() {
        super.leave();
    }

    @Override
    public synchronized void isOk() {
        super.isOk();
    }
}

class Wash implements Runnable {
    private Bathroom room;

    public Wash(Bathroom room) {
        this.room = room;
    }

    @Override
    public void run() {
        while (true) {
            if (room.isLock())
                room.leave();
            else
                room.enter();
            room.isOk();
        }
    }
}

public class E11_RaceCondition {
    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        // Without synchronization
        // Bathroom bathRoom = new Bathroom(); // IllegalStateException
        // With synchronization
         Bathroom bathRoom = new SafeBathroom(); // Ok
        for (int i = 0; i < 10; i++) {
            exec.execute(new Wash(bathRoom));
        }
        exec.shutdown();
    }
}