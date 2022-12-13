package exercises.chapter5;

/**
 * Exercise 12
 * Create a class called Tank that can be filled and emptied,
 * with a termination condition that it must be empty when
 * the object is cleaned up. Write a finalize() that verifies
 * this termination condition. In main(), test the possible
 * scenarios that can occur when you use Tank.
 */
class Tank {

    boolean full = false;

    void fill() {
        System.out.println("Tank fill()");
        full = true;
    }

    void empty() {
        System.out.println("Tank empty()");
        full = false;
    }

    @Override
    protected void finalize() {
        System.out.println("finalize()");
        if (full) {
            System.out.println("Error: tank full");
        } else {
            System.out.println("Ok! Tank empty");
        }
    }
}
public class E12_TankTest {
    public static void main(String[] args) {
        new Tank().fill();
        new Tank();
        System.gc();
    }
}
/* Output:
Tank fill()
finalize()
Error: tank full
finalize()
Ok! Tank empty
 */