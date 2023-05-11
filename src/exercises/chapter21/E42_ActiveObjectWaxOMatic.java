package exercises.chapter21;

import java.util.concurrent.*;

import static net.mindview.util.Print.print;
import static net.mindview.util.Print.printnb;

/**
 * Exercise 42
 * Modify WaxOMatic.java so that it implements active objects.
 */
class ActiveCar {
    private ExecutorService ex = Executors.newSingleThreadExecutor();
    private enum Action { WAX, BUFF }
    private Action lastAction = Action.BUFF;
    private boolean waxOn = false;

    public void wax() {
        try {
            ex.execute(waxingTask);
        } catch (RejectedExecutionException ignored) {
        }
    }

    public void buff() {
        try {
            ex.execute(buffingTask);
        } catch (RejectedExecutionException ignored) {
        }
    }

    private class WaxingTask implements Runnable {
        @Override
        public void run() {
            if (lastAction != Action.WAX) {
                printnb("Wax On! ");
                pause(200);
                waxOn = true; // Ready to buff
                lastAction = Action.WAX;
            }
        }
    }

    private final WaxingTask waxingTask = new WaxingTask();

    private class BuffingTask implements Runnable {
        @Override
        public void run() {
            if (lastAction != Action.BUFF) {
                printnb("Wax Off! ");
                pause(200);
                waxOn = false; // Ready for another coat of wax
                lastAction = Action.BUFF;
            }
        }
    }

    private final BuffingTask buffingTask = new BuffingTask();

    private void pause(int factor) {
        try {
            TimeUnit.MILLISECONDS.sleep(factor);
        } catch (InterruptedException e) {
            print("sleep() interrupted");
        }
    }

    public void shutdown() {
        ex.shutdown();
    }
}

class WaxCar implements Runnable {
    private ActiveCar car;

    public WaxCar(ActiveCar c) {
        car = c;
    }

    @Override
    public void run() {
        car.wax();
    }
}

class BuffCar implements Runnable {
    private ActiveCar car;

    public BuffCar(ActiveCar c) {
        car = c;
    }

    @Override
    public void run() {
        car.buff();
    }
}

public class E42_ActiveObjectWaxOMatic {
    public static void main(String[] args) throws Exception {
        ActiveCar car = new ActiveCar();
        ScheduledExecutorService ex = Executors.newScheduledThreadPool(2);
        ex.scheduleAtFixedRate(new BuffCar(car), 0, 200, TimeUnit.MILLISECONDS);
        ex.scheduleAtFixedRate(new WaxCar(car), 0, 200, TimeUnit.MILLISECONDS);
        TimeUnit.SECONDS.sleep(5); // Run for a while...
        ex.shutdownNow(); // Interrupt all tasks
        car.shutdown();
    }
}
/* Output: (Sample)
Wax On! Wax Off! Wax On! Wax Off! Wax On! Wax Off! Wax On! Wax Off! Wax On! Wax Off! Wax On! Wax Off! Wax On! Wax Off! Wax On! Wax Off! Wax On! Wax Off! Wax On! Wax Off! Wax On! Wax Off! Wax On! Wax Off! Wax On! Wax Off! Wax On! Wax Off! Wax On! Wax Off! Wax On! Wax Off! Wax On! Wax Off! Wax On! Wax Off! Wax On! Wax Off! Wax On! Wax Off! Wax On! Wax Off! Wax On! Wax Off! Wax On! Wax Off! Wax On!
 */