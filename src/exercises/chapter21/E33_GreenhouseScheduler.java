package exercises.chapter21;

import java.util.concurrent.*;

import static java.util.concurrent.TimeUnit.NANOSECONDS;
import static net.mindview.util.Print.print;

/**
 * Exercise 33
 * Modify GreenhouseScheduler.java to use a DelayQueue
 * instead of a ScheduledExecutor.
 * {Args: 5000}
 */
abstract class Event implements Runnable, Delayed {
    protected long delayTime;
    private long trigger;

    public Event(long delayTime) {
        this.delayTime = delayTime;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(trigger - System.nanoTime(), NANOSECONDS);
    }

    @Override
    public int compareTo(Delayed arg) {
        Event that = (Event) arg;
        if (trigger < that.trigger) return -1;
        if (trigger > that.trigger) return 1;
        return 0;
    }

    public void start() {
        trigger = System.nanoTime() + NANOSECONDS.convert(delayTime, NANOSECONDS);
    }

    @Override
    abstract public void run();
}

class Controller implements Runnable {
    private DelayQueue<Event> q;

    public Controller(DelayQueue<Event> q) {
        this.q = q;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                Event task = q.take();
                print(task);
                task.run();
            }
        } catch (InterruptedException e) {
            // Acceptable way to exit
        }
        print("Finished Controller");
    }

    public void addEvent(Event e) {
        e.start();
        q.put(e);
    }
}

class GreenhouseControls extends Controller {
    public GreenhouseControls(DelayQueue<Event> q) {
        super(q);
    }

    private boolean light = false;

    class LightOn extends Event {
        public LightOn(long delayTime) {
            super(delayTime);
        }

        @Override
        public void run() {
            // Put hardware control code here to
            // physically turn on the light.
            light = true;
        }

        @Override
        public String toString() {
            return "Light is on";
        }
    }

    class LightOff extends Event {
        public LightOff(long delayTime) {
            super(delayTime);
        }

        @Override
        public void run() {
            // Put hardware control code here to
            // physically turn off the light.
            light = false;
        }

        @Override
        public String toString() {
            return "Light is off";
        }
    }

    private boolean water = false;

    class WaterOn extends Event {
        public WaterOn(long delayTime) {
            super(delayTime);
        }

        @Override
        public void run() {
            // Put hardware control code here.
            water = true;
        }

        @Override
        public String toString() {
            return "Greenhouse water is on";
        }
    }

    class WaterOff extends Event {
        public WaterOff(long delayTime) {
            super(delayTime);
        }

        @Override
        public void run() {
            // Put hardware control code here.
            water = false;
        }

        @Override
        public String toString() {
            return "Greenhouse water is off";
        }
    }

    private String thermostat = "Day";

    class ThermostatNight extends Event {
        public ThermostatNight(long delayTime) {
            super(delayTime);
        }

        @Override
        public void run() {
            // Put hardware control code here.
            thermostat = "Night";
        }

        @Override
        public String toString() {
            return "Thermostat on night setting";
        }
    }

    class ThermostatDay extends Event {
        public ThermostatDay(long delayTime) {
            super(delayTime);
        }

        @Override
        public void run() {
            // Put hardware control code here.
            thermostat = "Day";
        }

        @Override
        public String toString() {
            return "Thermostat on day setting";
        }
    }

    class Bell extends Event {
        public Bell(long delayTime) {
            super(delayTime);
        }

        @Override
        public void run() {
            addEvent(new Bell(delayTime));
        }

        @Override
        public String toString() {
            return "Bing!";
        }
    }

    class Terminate extends Event {
        private ExecutorService exec;
        public Terminate(long delayTime, ExecutorService exec) {
            super(delayTime);
            this.exec = exec;
        }

        @Override
        public void run() {
            exec.shutdownNow();
        }

        @Override
        public String toString() {
            return "Terminating";
        }
    }

    class Restart extends Event {
        private Event[] tasks;

        public Restart(long delayTime, Event[] tasks) {
            super(delayTime);
            this.tasks = tasks;
            for (Event task : tasks)
                addEvent(task);
        }

        @Override
        public void run() {
            for (Event task : tasks)
                addEvent(task);
            addEvent(this);
        }

        @Override
        public String toString() {
            return "Restarting system";
        }
    }
}

public class E33_GreenhouseScheduler {
    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool();
        DelayQueue<Event> queue = new DelayQueue<>();
        GreenhouseControls gc = new GreenhouseControls(queue);
        gc.addEvent(gc.new Bell(900));
        Event[] tasks = {
                gc.new ThermostatNight(0),
                gc.new LightOn(200),
                gc.new LightOff(400),
                gc.new WaterOn(600),
                gc.new WaterOff(800),
                gc.new ThermostatDay(1400),
        };
        gc.addEvent(gc.new Restart(2000, tasks));
        if (args.length == 1)
            gc.addEvent(gc.new Terminate(new Integer(args[0]), exec));
        exec.execute(gc);
    }
}
/* Output:
Bing!
Thermostat on night setting
Light is on
Light is off
Greenhouse water is on
Greenhouse water is off
Thermostat on day setting
Restarting system
Terminating
Finished Controller
 */