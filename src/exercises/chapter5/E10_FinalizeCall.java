package exercises.chapter5;

/**
 * Exercise 10
 * Create a class with a finalize() method that prints a message.
 * In main(), create an object of your class.
 * Explain the behavior of your program.
 */
class Book {
    @Override
    protected void finalize() {
        System.out.println("finalize()");
    }
}
public class E10_FinalizeCall {
    public static void main(String[] args) {
        new Book();
    }
}