package exercises.chapter21;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static net.mindview.util.Print.print;
import static net.mindview.util.Print.printnb;

/**
 * Exercise 26
 * Add a BusBoy class to Restaurant.java. After the meal is delivered,
 * the WaitPerson should notify the BufBoy to clean up.
 */
class WaitPerson2 implements Runnable {
    private Restaurant2 restaurant;
    volatile boolean notified = false;

    public WaitPerson2(Restaurant2 r) {
        restaurant = r;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                synchronized (this) {
                    while (restaurant.meal == null)
                        wait(); // ... for the chef to produce a meal
                }
                print("Waitperson got " + restaurant.meal);
                synchronized (restaurant.busBoy) {
                    restaurant.busBoy.meal = restaurant.meal;
                    restaurant.busBoy.cleaned = false;
                    restaurant.busBoy.notifyAll();
                }
                synchronized (restaurant.chef) {
                    restaurant.meal = null;
                    restaurant.chef.notifyAll(); // Ready for another

                }
                synchronized (this) {
                    if (!notified)
                        wait();
                    notified = false;
                }
            }
        } catch (InterruptedException e) {
            print("WaitPerson interrupted");
        }
    }
}

class Chef2 implements Runnable {
    private Restaurant2 restaurant;
    private int count = 0;

    public Chef2(Restaurant2 r) {
        restaurant = r;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                synchronized (this) {
                    while (restaurant.meal != null)
                        wait(); // ... for the meal to be taken
                }
                if (++count == 10) {
                    print("Out of food, closing");
                    restaurant.exec.shutdownNow();
                }
                printnb("Order up! ");
                synchronized (restaurant.waitPerson) {
                    restaurant.meal = new Meal(count);
                    restaurant.waitPerson.notifyAll();
                }
                TimeUnit.MILLISECONDS.sleep(100);
            }
        } catch (InterruptedException e) {
            print("Chef interrupted");
        }
    }
}

class BusBoy implements Runnable {
    Restaurant2 restaurant;
    boolean cleaned = true;
    volatile Meal meal;

    public BusBoy(Restaurant2 r) {
        restaurant = r;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                synchronized (this) {
                    while (cleaned)
                        wait();
                    cleaned = true;
                }
                print("BusBoy clean " + meal);
                synchronized (restaurant.waitPerson) {
                    restaurant.waitPerson.notified = true;
                    restaurant.waitPerson.notifyAll();
                }
            }
        } catch (InterruptedException e) {
            print("BusBoy interrupted");
        }
    }
}

class Restaurant2 {
    Meal meal;
    ExecutorService exec = Executors.newCachedThreadPool();
    WaitPerson2 waitPerson = new WaitPerson2(this);
    Chef2 chef = new Chef2(this);
    BusBoy busBoy = new BusBoy(this);

    public Restaurant2() {
        exec.execute(chef);
        exec.execute(waitPerson);
        exec.execute(busBoy);
    }
}

public class E26_Restaurant2 {
    public static void main(String[] args) {
        new Restaurant2();
    }
}
/* Output:
Order up! Waitperson got Meal 1
BusBoy clean Meal 1
Order up! Waitperson got Meal 2
BusBoy clean Meal 2
Order up! Waitperson got Meal 3
BusBoy clean Meal 3
Order up! Waitperson got Meal 4
BusBoy clean Meal 4
Order up! Waitperson got Meal 5
BusBoy clean Meal 5
Order up! Waitperson got Meal 6
BusBoy clean Meal 6
Order up! Waitperson got Meal 7
BusBoy clean Meal 7
Order up! Waitperson got Meal 8
BusBoy clean Meal 8
Order up! Waitperson got Meal 9
BusBoy clean Meal 9
Out of food, closing
Order up! WaitPerson interrupted
Chef interrupted
BusBoy interrupted
 */