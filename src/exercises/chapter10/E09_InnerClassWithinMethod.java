package exercises.chapter10;

/**
 * Exercise 9
 * Create an interface with at least one method, and implement
 * it by defining an inner class within a method that returns
 * a reference to your interface.
 */
interface Flyable {
    void fly();
}
public class E09_InnerClassWithinMethod {
    public static Flyable getFlyable() {
        class BatterFly implements Flyable {
            @Override
            public void fly() {
                System.out.println("I'm flying!");
            }
        }
        return new BatterFly();
    }

    public static void main(String[] args) {
        Flyable flyable = getFlyable();
        flyable.fly();
    }
}
/* Output:
I'm flying!
 */