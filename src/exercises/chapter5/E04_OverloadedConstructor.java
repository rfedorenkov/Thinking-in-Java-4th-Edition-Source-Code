package exercises.chapter5;

/**
 * Exercise 4
 * Add an overloaded constructor to Exercise 3 that takes a String argument and prints it along with your message.
 */
class Horse2 {
    Horse2() {
        System.out.println("Default constructor");
    }

    Horse2(String s) {
        System.out.println(s);
    }
}

public class E04_OverloadedConstructor {
    public static void main(String[] args) {
        Horse2 horse1 = new Horse2();
        Horse2 horse2 = new Horse2("Overloaded constructor");
    }
}
/* Output:
Default constructor
Overloaded constructor
 */