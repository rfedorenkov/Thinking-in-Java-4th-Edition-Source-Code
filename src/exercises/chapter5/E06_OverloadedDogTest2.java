package exercises.chapter5;

/**
 * Exercise 6
 * Modify Exercise 5 so two of the overloaded methods
 * have two arguments of two different types,
 * but in reversed order relative to each other.
 * Verify that this works.
 */
class Dog2 {
    void bark() {
        System.out.println("Default bark(): Arf!");
    }

    void bark(char x) {
        System.out.println("bark(char): Yamph!");
    }

    void bark(byte x) {
        System.out.println("bark(byte): Woof!");
    }

    void bark(short x) {
        System.out.println("bark(short): Yaffle!");
    }

    void bark(int x) {
        System.out.println("bark(int): Yawp!");
    }

    void bark(long x) {
        System.out.println("bark(long): Guf! Guf!");
    }

    void bark(float y, double x) {
        System.out.println("bark(float): Bow! Wow!");
    }

    void bark(double x, float y) {
        System.out.println("bark(double): Ruff!");
    }
}

public class E06_OverloadedDogTest2 {
    public static void main(String[] args) {
        Dog2 dog = new Dog2();
        char c = 'c';
        dog.bark();
        dog.bark(c);
        dog.bark((byte) c);
        dog.bark((short) c);
        dog.bark((int) c);
        dog.bark((long) c);
        dog.bark((float) c, (double) c);
        dog.bark((double) c, (float) c);
    }
}
/* Output:
Default bark(): Arf!
bark(char): Yamph!
bark(byte): Woof!
bark(short): Yaffle!
bark(int): Yawp!
bark(long): Guf! Guf!
bark(float): Bow! Wow!
bark(double): Ruff!
 */