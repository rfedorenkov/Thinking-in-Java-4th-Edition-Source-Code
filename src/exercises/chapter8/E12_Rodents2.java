package exercises.chapter8;

/**
 * Exercise 12
 * Modify Exercise 9 so it demonstrates the order
 * of initialization of the base classes and derived
 * classes. Now add member objects to both the base
 * and derived classes, and show the order in which
 * their initialization occurs during construction.
 */
class Description {
    Description(String s) {
        System.out.println("Description() " + s);
    }
}

class Rodent2 {
    private Description d = new Description("Simple rodent");

    Rodent2() {
        System.out.println("Rodent constructor");
    }

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

class Mouse2 extends Rodent2 {
    private Description d = new Description("Gray mouse");

    Mouse2() {
        System.out.println("Mouse constructor");
    }

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

class Gerbil2 extends Rodent2 {
    private Description d = new Description("White gerbil");

    Gerbil2() {
        System.out.println("Gerbil constructor");
    }

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

class Hamster2 extends Rodent2 {
    private Description d = new Description("Domestic hamster");

    Hamster2() {
        System.out.println("Hamster constructor");
    }

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

public class E12_Rodents2 {
    public static void main(String[] args) {
        new Hamster2();
    }
}
/* Output:
Description() Simple rodent
Rodent constructor
Description() Domestic hamster
Hamster constructor
 */