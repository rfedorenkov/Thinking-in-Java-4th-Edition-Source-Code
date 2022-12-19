package exercises.chapter7;

/**
 * Exercise 16
 * Create a class called Amphibian. From it, inherit a class
 * from it called Frog. Put appropriate methods in the base
 * class. In main(), create a Frog, upcast it to Amphibian,
 * and demonstrate that all the methods still work.
 */
class Amphibian {
    public void swim() {
        System.out.println("swim()");
    }

    public void speak() {
        System.out.println("speak()");
    }

    public void eat() {
        System.out.println("eat()");
    }
}

class Frog extends Amphibian {

}

public class E16_UpcastingTest {
    public static void main(String[] args) {
        Amphibian frog = new Frog();
        frog.swim();
        frog.speak();
        frog.eat();
    }
}
/* Output:
swim()
speak()
eat()
 */