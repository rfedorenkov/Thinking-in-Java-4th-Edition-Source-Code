package exercises.chapter5;

/**
 * Exercise 3
 * Create a class with a default constructor (one that takes no arguments)
 * that prints a message. Create an object of this class.
 */
class Horse {
    Horse() {
        System.out.println("Default constructor");
    }
}

public class E03_DefaultConstructor {
    public static void main(String[] args) {
        Horse horse = new Horse();
    }
}
/* Output:
Default constructor
 */