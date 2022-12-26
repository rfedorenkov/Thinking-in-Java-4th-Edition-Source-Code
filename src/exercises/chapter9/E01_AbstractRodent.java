package exercises.chapter9;

/**
 * Exercise 1
 * Modify Exercise 9 in the previous chapter so Rodent
 * is an abstract class. Make the methods of Rodent abstract
 * whenever possible.
 */
abstract class Rodent {
    abstract public void eat();

    abstract public void sleep();
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

public class E01_AbstractRodent {
    public static void main(String[] args) {
        Rodent[] rodents = {
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