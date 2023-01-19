package exercises.chapter11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Exercise 10
 * Change Exercise 9 in the Polymorphism chapter to use
 * an ArrayList to hold the Rodents and an Iterator
 * to move through their sequence.
 */
class Rodent {
    public void eat() {
        System.out.println("Rodent.eat()");
    }

    public void sleep() {
        System.out.println("Rodent.sleep()");
    }

    @Override
    public String toString() {
        return "Rodent";
    }
}

class Mouse extends Rodent {
    @Override
    public void eat() {
        System.out.println("Mouse.eat()");
    }

    @Override
    public void sleep() {
        System.out.println("Mouse.sleep()");
    }

    @Override
    public String toString() {
        return "Mouse";
    }
}

class Hamster extends Rodent {
    @Override
    public void eat() {
        System.out.println("Hamster.eat()");
    }

    @Override
    public void sleep() {
        System.out.println("Hamster.sleep()");
    }

    @Override
    public String toString() {
        return "Hamster";
    }
}

public class E10_RodentIterator {
    public static void main(String[] args) {
        List<Rodent> rodents = new ArrayList<>(Arrays.asList(new Rodent(), new Mouse(), new Hamster()));
        Iterator<Rodent> iterator = rodents.iterator();
        while (iterator.hasNext()) {
            Rodent rodent = iterator.next();
            System.out.println(rodent);
            rodent.eat();
            rodent.sleep();
        }
    }
}
/* Output:
Rodent
Rodent.eat()
Rodent.sleep()
Mouse
Mouse.eat()
Mouse.sleep()
Hamster
Hamster.eat()
Hamster.sleep()
 */