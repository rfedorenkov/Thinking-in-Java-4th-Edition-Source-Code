package exercises.chapter8;

/**
 * Exercise 9
 * Create an inheritance hierarchy of Rodent:
 * Mouse, Gerbil, Hamster, ect. In the base class,
 * provide methods that are common to all Rodents,
 * and override these in the derived classes
 * to perform different behaviors depending
 * on the specific type of Rodent.
 * Create an array of Rodent, fill it with
 * different specific types of Rodents, and call
 * your base-class methods to see what happens.
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

class Gerbil extends Rodent {
    @Override
    public void eat() {
        System.out.println("Gerbil.eat()");
    }

    @Override
    public void sleep() {
        System.out.println("Gerbil.sleep()");
    }

    @Override
    public String toString() {
        return "Gerbil";
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

public class E09_Rodents {
    public static void main(String[] args) {
        Rodent[] rodents = {
                new Rodent(),
                new Mouse(),
                new Gerbil(),
                new Hamster()
        };

        for (Rodent rodent : rodents) {
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
Gerbil
Gerbil.eat()
Gerbil.sleep()
Hamster
Hamster.eat()
Hamster.sleep()
 */