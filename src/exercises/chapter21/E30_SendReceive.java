package exercises.chapter21;

import java.util.Random;
import java.util.concurrent.*;

import static net.mindview.util.Print.print;
import static net.mindview.util.Print.printnb;

/**
 * Exercise 30
 * Modify PipedIO.java to use a BlockingQueue instead of a pipe.
 */
class CharQueue extends LinkedBlockingQueue<Character> {
}

class Sender implements Runnable {
    private Random rand = new Random(47);
    private CharQueue out = new CharQueue();

    public CharQueue getOut() {
        return out;
    }

    @Override
    public void run() {
        try {
            while (true) {
                for (char c = 'A'; c <= 'z'; c++) {
                    out.put(c);
                    TimeUnit.MILLISECONDS.sleep(rand.nextInt(500));
                }
            }
        } catch (InterruptedException e) {
            print(e + " Sender sleep interrupted");
        }
    }
}

class Receiver implements Runnable {
    private BlockingQueue<Character> in;

    public Receiver(Sender sender) {
        in = sender.getOut();
    }

    @Override
    public void run() {
        try {
            while (true) {
                // Blocks until characters are there:
                printnb("Read: " + (char) in.take() + ", ");
            }
        } catch (InterruptedException e) {
            print(e + " Receiver sleep interrupted");
        }
    }
}

public class E30_SendReceive {
    public static void main(String[] args) throws Exception {
        Sender sender = new Sender();
        Receiver receiver = new Receiver(sender);
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(sender);
        exec.execute(receiver);
        TimeUnit.SECONDS.sleep(4);
        exec.shutdownNow();
    }
}
/* Output: (Sample)
Read: A, Read: B, Read: C, Read: D, Read: E, Read: F, Read: G, Read: H, Read: I, Read: J, Read: K, Read: L, Read: M, Read: N, Read: O, Read: P, Read: Q, java.lang.InterruptedException Receiver sleep interrupted
java.lang.InterruptedException: sleep interrupted Sender sleep interrupted
 */