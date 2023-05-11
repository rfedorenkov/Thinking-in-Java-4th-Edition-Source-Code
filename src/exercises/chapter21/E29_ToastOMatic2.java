package exercises.chapter21;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import static net.mindview.util.Print.print;

/**
 * Exercise 29
 * Modify ToastOMatic.java to create peanut butter and jelly on toast
 * sandwiches using two separate assembly lines (one for peanut butter,
 * the second for jelly, then merging the two lines).
 */
class Toast {
    public enum Status {
        DRY,
        BUTTERED,
        JAMMED,
        READY {
            @Override
            public String toString() {
                return BUTTERED + " & " + JAMMED;
            }
        }
    }

    private Status status = Status.DRY;
    private final int id;

    public Toast(int idn) {
        id = idn;
    }

    public void butter() {
        status = (status == Status.DRY) ? Status.BUTTERED : Status.READY;
    }

    public void jam() {
        status = (status == Status.DRY) ? Status.JAMMED : Status.READY;
    }

    public Status getStatus() {
        return status;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Toast " + id + ": " + status;
    }
}

class ToastQueue extends LinkedBlockingQueue<Toast> {
}

class Toaster implements Runnable {
    private ToastQueue toastQueue;
    private int count = 0;
    private Random rand = new Random(47);

    public Toaster(ToastQueue tq) {
        toastQueue = tq;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                TimeUnit.MILLISECONDS.sleep(100 + rand.nextInt(500));
                // Make toast
                Toast t = new Toast(count++);
                print(t);
                // Insert into queue
                toastQueue.put(t);
            }
        } catch (InterruptedException e) {
            print("Toaster interrupted");
        }
        print("Toaster off");
    }
}

// Apply butter to toast:
class Butterer implements Runnable {
    private ToastQueue inQueue, butteredQueue;

    public Butterer(ToastQueue in, ToastQueue buttered) {
        inQueue = in;
        butteredQueue = buttered;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                // Block until next piece of toast is available:
                Toast t = inQueue.take();
                t.butter();
                print(t);
                butteredQueue.put(t);
            }
        } catch (InterruptedException e) {
            print("Butterer interrupted");
        }
        print("Butterer off");
    }
}

// Apply jam to buttered toast:
class Jammer implements Runnable {
    private ToastQueue inQueue, finishedQueue;

    public Jammer(ToastQueue in, ToastQueue finished) {
        inQueue = in;
        finishedQueue = finished;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                // Blocks until next piece of toast is available:
                Toast t = inQueue.take();
                t.jam();
                print(t);
                finishedQueue.put(t);
            }
        } catch (InterruptedException e) {
            print("Jammer interruped");
        }
        print("Jammer off");
    }
}

// Consume the toast:
class Eater implements Runnable {
    private ToastQueue finishedQueue;
    private int counter = 0;

    public Eater(ToastQueue finished) {
        finishedQueue = finished;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                // Blocks until next piece of toast is available:
                Toast t = finishedQueue.take();
                // Verify that the toast is coming in order,
                // and that all pieces are getting jammed:
                if (t.getStatus() != Toast.Status.READY) {
                    print(">>>> Error: " + t);
                    System.exit(1);
                } else
                    print("Chomp! " + t);
            }
        } catch (InterruptedException e) {
            print("Eater interrupted");
        }
        print("Eater off");
    }
}

class Alternator implements Runnable {
    private ToastQueue inQueue, outQueue1, outQueue2;
    private boolean outTo2;

    public Alternator(ToastQueue in, ToastQueue out1, ToastQueue out2) {
        inQueue = in;
        outQueue1 = out1;
        outQueue2 = out2;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                Toast t = inQueue.take();
                if (!outTo2)
                    outQueue1.put(t);
                else
                    outQueue2.put(t);
                outTo2 = !outTo2;
            }
        } catch (InterruptedException e) {
            print("Alternator interrupted");
        }
        print("Alternator off");
    }
}

class Merger implements Runnable {
    private ToastQueue inQueue1, inQueue2, toBeButteredQueue, toBeJammedQueue, finishedQueue;

    public Merger(ToastQueue in1, ToastQueue in2,
                  ToastQueue toBeButtered, ToastQueue toBeJammed,
                  ToastQueue finished) {
        inQueue1 = in1;
        inQueue2 = in2;
        toBeButteredQueue = toBeButtered;
        toBeJammedQueue = toBeJammed;
        finishedQueue = finished;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                Toast t = null;
                while (t == null) {
                    t = inQueue1.poll(50, TimeUnit.MILLISECONDS);
                    if (t != null)
                        break;
                    t = inQueue2.poll(50, TimeUnit.MILLISECONDS);
                }
                switch (t.getStatus()) {
                    case BUTTERED:
                        toBeJammedQueue.put(t);
                        break;
                    case JAMMED:
                        toBeButteredQueue.put(t);
                        break;
                    default:
                        finishedQueue.put(t);
                }
            }
        } catch (InterruptedException e) {
            print("Merger interrupted");
        }
        print("Merger off");
    }
}

public class E29_ToastOMatic2 {
    public static void main(String[] args) throws Exception {
        ToastQueue dryQueue = new ToastQueue(),
                butteredQueue = new ToastQueue(),
                toBeButteredQueue = new ToastQueue(),
                jammedQueue = new ToastQueue(),
                toBeJammedQueue = new ToastQueue(),
                finishedQueue = new ToastQueue();
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new Toaster(dryQueue));
        exec.execute(new Alternator(dryQueue, toBeButteredQueue, toBeJammedQueue));
        exec.execute(new Butterer(toBeButteredQueue, butteredQueue));
        exec.execute(new Jammer(toBeJammedQueue, jammedQueue));
        exec.execute(new Merger(butteredQueue, jammedQueue, toBeButteredQueue, toBeJammedQueue, finishedQueue));
        exec.execute(new Eater(finishedQueue));
        TimeUnit.SECONDS.sleep(5);
        exec.shutdownNow();
    }
}
/* Output:
Toast 0: DRY
Toast 0: BUTTERED
Toast 0: BUTTERED & JAMMED
Chomp! Toast 0: BUTTERED & JAMMED
Toast 1: DRY
Toast 1: JAMMED
Toast 1: BUTTERED & JAMMED
Chomp! Toast 1: BUTTERED & JAMMED
Toast 2: DRY
Toast 2: BUTTERED
Toast 2: BUTTERED & JAMMED
Chomp! Toast 2: BUTTERED & JAMMED
Toast 3: DRY
Toast 3: JAMMED
Toast 3: BUTTERED & JAMMED
Chomp! Toast 3: BUTTERED & JAMMED
Toast 4: DRY
Toast 4: BUTTERED
Toast 4: BUTTERED & JAMMED
Chomp! Toast 4: BUTTERED & JAMMED
Toast 5: DRY
Toast 5: JAMMED
Toast 5: BUTTERED & JAMMED
Chomp! Toast 5: BUTTERED & JAMMED
Toast 6: DRY
Toast 6: BUTTERED
Toast 6: BUTTERED & JAMMED
Chomp! Toast 6: BUTTERED & JAMMED
Toast 7: DRY
Toast 7: JAMMED
Toast 7: BUTTERED & JAMMED
Chomp! Toast 7: BUTTERED & JAMMED
Toast 8: DRY
Toast 8: BUTTERED
Toast 8: BUTTERED & JAMMED
Chomp! Toast 8: BUTTERED & JAMMED
Toast 9: DRY
Toast 9: JAMMED
Toast 9: BUTTERED & JAMMED
Chomp! Toast 9: BUTTERED & JAMMED
Toast 10: DRY
Toast 10: BUTTERED
Toast 10: BUTTERED & JAMMED
Chomp! Toast 10: BUTTERED & JAMMED
Toast 11: DRY
Toast 11: JAMMED
Toast 11: BUTTERED & JAMMED
Chomp! Toast 11: BUTTERED & JAMMED
Toast 12: DRY
Toast 12: BUTTERED
Toast 12: BUTTERED & JAMMED
Chomp! Toast 12: BUTTERED & JAMMED
Toast 13: DRY
Toast 13: JAMMED
Toast 13: BUTTERED & JAMMED
Chomp! Toast 13: BUTTERED & JAMMED
Toast 14: DRY
Toast 14: BUTTERED
Alternator interrupted
Alternator off
Toaster interrupted
Toaster off
Eater interrupted
Eater off
Butterer interrupted
Butterer off
Jammer interruped
Jammer off
Merger interrupted
Merger off
 */