package exercises.chapter5;

/**
 * Exercise 7
 * Create a class without a constructor, then create an object
 * of that class in main() to verify that the default constructor
 * is automatically synthesized.
 */
class Phone {
    void call() {
        System.out.println("Calling...");
    }
}

public class E07_AutomaticDefaultConstructor {
    public static void main(String[] args) {
        Phone phone = new Phone();
        phone.call();
    }
}
/* Output:
Calling...
 */