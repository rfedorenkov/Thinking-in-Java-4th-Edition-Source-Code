package exercises.chapter10;

/**
 * Exercise 13
 * Repeat Exercise 9 using an anonymous inner class.
 */
public class E13_AnonymousInnerClassWithinMethod {
    public static Flyable getFlyable() {
        return new Flyable() {
            @Override
            public void fly() {
                System.out.println("I'm flying!");
            }
        };
    }

    public static void main(String[] args) {
        Flyable flyable = getFlyable();
        flyable.fly();
    }
}
/* Output:
I'm flying!
 */