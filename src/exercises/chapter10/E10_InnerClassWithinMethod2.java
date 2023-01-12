package exercises.chapter10;

/**
 * Exercise 10
 * Repeat Exercise 9 but define the inner class
 * withing a scope within a method.
 */
public class E10_InnerClassWithinMethod2 {
    public static Flyable getFlyable(boolean b) {
        if (b) {
            class BatterFly implements Flyable {
                @Override
                public void fly() {
                    System.out.println("I'm flying!");
                }
            }
            return new BatterFly();
        }
        return null;
    }

    public static void main(String[] args) {
        Flyable flyable = getFlyable(true);
        flyable.fly();
    }
}
/* Output:
I'm flying!
 */