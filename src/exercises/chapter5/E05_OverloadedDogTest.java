package exercises.chapter5;

/**
 * Exercise 5
 * Create a class called Dog with an overloaded bark() method.
 * Your method should be overloaded based on various primitive data types,
 * and should print different types of barking, howling, etc.,
 * depending on which overloaded version is called.
 * Write a main() that calls all the different versions.
 */
class Dog {
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

    void bark(float x) {
        System.out.println("bark(float): Bow! Wow!");
    }

    void bark(double x) {
        System.out.println("bark(double): Ruff!");
    }
}

public class E05_OverloadedDogTest {
    public static void main(String[] args) {
        Dog dog = new Dog();
        char c = 'c';
        dog.bark();
        dog.bark(c);
        dog.bark((byte) c);
        dog.bark((short) c);
        dog.bark((int) c);
        dog.bark((long) c);
        dog.bark((float) c);
        dog.bark((double) c);
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