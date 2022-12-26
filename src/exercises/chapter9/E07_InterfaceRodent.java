package exercises.chapter9;

/**
 * Exercise 7
 * Change Rodent to an interface in Exercise 9
 * of the Polymorphism chapter.
 */
interface Rodent2 {
    void eat();

    void sleep();
}

class Mouse2 implements Rodent2 {
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

class Gerbil2 implements Rodent2 {
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

class Hamster2 implements Rodent2 {
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

public class E07_InterfaceRodent {
    public static void main(String[] args) {
        Rodent2[] rodents = {
                new Mouse2(),
                new Gerbil2(),
                new Hamster2()
        };

        for (Rodent2 rodent : rodents) {
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