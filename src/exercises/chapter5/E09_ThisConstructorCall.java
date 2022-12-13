package exercises.chapter5;

/**
 * Exercise 9
 * Create a class with two (overloaded) constructors.
 * Using this, call the second constructor inside the first one.
 */
class Car {
    int speed;

    Car() {
        this(60);
        System.out.println("Default constructor");
    }

    Car(int speed) {
        this.speed = speed;
        System.out.println("Overloaded constructor");
    }
}

public class E09_ThisConstructorCall {
    public static void main(String[] args) {
        Car car = new Car();
    }
}
/* Output:
Overloaded constructor
Default constructor
 */