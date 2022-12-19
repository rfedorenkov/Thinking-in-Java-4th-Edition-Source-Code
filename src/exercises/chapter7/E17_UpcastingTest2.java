package exercises.chapter7;

/**
 * Exercise 17
 * Modify Exercise 16 so Frog overrides the
 * method definitions from the base class
 * (provides new definitions using the same
 * methods signatures). Note what happens in main().
 */
class Amphibian2 {
    public void swim() {
        System.out.println("Amphibian swim()");
    }

    public void speak() {
        System.out.println("Amphibian speak()");
    }

    public void eat() {
        System.out.println("Amphibian eat()");
    }
}

class Frog2 extends Amphibian2 {
    @Override
    public void swim() {
        System.out.println("Frog swim()");
    }

    @Override
    public void speak() {
        System.out.println("Frog speak()");
    }

    @Override
    public void eat() {
        System.out.println("Frog eat()");
    }
}

public class E17_UpcastingTest2 {
    public static void main(String[] args) {
        Amphibian2 frog = new Frog2();
        frog.swim();
        frog.speak();
        frog.eat();
    }
}
/* Output:
Frog swim()
Frog speak()
Frog eat()
 */