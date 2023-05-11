package exercises.chapter21;

import net.mindview.util.RandomGenerator;

import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

import static net.mindview.util.Print.print;

/**
 * Exercise 41
 * Add a message handler to ActiveObjectDemo.java that
 * has no return value, and call this within main().
 */
public class E41_ActiveObjectDemo2 {
    private ExecutorService ex = Executors.newSingleThreadExecutor();
    private Random rand = new Random(47);

    // Insert a random delay to produce the effect
    // of a calculation time:
    private void pause(int factor) {
        try {
            TimeUnit.MILLISECONDS.sleep(100 + rand.nextInt(factor));
        } catch (InterruptedException e) {
            print("sleep() interrupted");
        }
    }

    public Future<Integer> calculateInt(final int x, final int y) {
        return ex.submit(new Callable<Integer>() {
            @Override
            public Integer call() {
                print("starting " + x + " + " + y);
                pause(500);
                return x + y;
            }
        });
    }

    public Future<Float> calculateFloat(final float x, final float y) {
        return ex.submit(new Callable<Float>() {
            @Override
            public Float call() {
                print("starting " + x + " + " + y);
                pause(2000);
                return x + y;
            }
        });
    }

    public void send(final String file) {
        ex.submit(new Runnable() {
            @Override
            public void run() {
                print("Sending a file: " + file);
                pause(500);
                print("File " + file + " sent");
            }
        });
    }

    public void shutdown() {
        ex.shutdown();
    }

    public static void main(String[] args) {
        E41_ActiveObjectDemo2 d1 = new E41_ActiveObjectDemo2();
        // Prevents ConcurrentModificationException:
        List<Future<?>> results = new CopyOnWriteArrayList<>();
        for (float f = 0.0f; f < 1.0f; f += 0.2f)
            results.add(d1.calculateFloat(f, f));
        for (int i = 0; i < 5; i++)
            results.add(d1.calculateInt(i, i));
        for (int i = 0; i < 5; i++) {
            d1.send(new RandomGenerator.String().next() + ".txt");
        }
        print("All asynch calls made");
        while (results.size() > 0) {
            for (Future<?> f : results)
                if (f.isDone()) {
                    try {
                        print(f.get());
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    results.remove(f);
                }
        }
        d1.shutdown();
    }
}
/* Output: (Sample)
All asynch calls made
starting 0.0 + 0.0
0.0
starting 0.2 + 0.2
0.4
starting 0.4 + 0.4
0.8
starting 0.6 + 0.6
1.2
starting 0.8 + 0.8
1.6
starting 0 + 0
starting 1 + 1
0
2
starting 2 + 2
4
starting 3 + 3
6
starting 4 + 4
8
Sending a file: YNzbrny.txt
File YNzbrny.txt sent
Sending a file: GcFOWZn.txt
File GcFOWZn.txt sent
Sending a file: TcQrGse.txt
File TcQrGse.txt sent
Sending a file: GZMmJMR.txt
File GZMmJMR.txt sent
Sending a file: oEsuEcU.txt
File oEsuEcU.txt sent
 */