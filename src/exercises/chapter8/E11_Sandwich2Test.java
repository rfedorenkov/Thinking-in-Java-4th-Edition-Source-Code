package exercises.chapter8;

import static net.mindview.util.Print.print;

/**
 * Exercise 11
 * Add class Pickle to Sandwich.java.
 */
class Meal {
    Meal() {
        print("Meal()");
    }
}

class Bread {
    Bread() {
        print("Bread()");
    }
}

class Cheese {
    Cheese() {
        print("Cheese()");
    }
}

class Lettuce {
    Lettuce() {
        print("Lettuce()");
    }
}

class Pickle {
    Pickle() {
        print("Pickle()");
    }
}

class Lunch extends Meal {
    Lunch() {
        print("Lunch()");
    }
}

class PortableLunch extends Lunch {
    PortableLunch() {
        print("PortableLunch");
    }
}

class Sandwich2 extends PortableLunch {
    private Bread b = new Bread();
    private Cheese c = new Cheese();
    private Lettuce l = new Lettuce();
    private Pickle p = new Pickle();

    public Sandwich2() {
        print("Sandwich()");
    }
}
public class E11_Sandwich2Test {
    public static void main(String[] args) {
        new Sandwich2();
    }
}
/* Output:
Meal()
Lunch()
PortableLunch
Bread()
Cheese()
Lettuce()
Pickle()
Sandwich()
 */