package exercises.chapter12;

/**
 * Exercise 5
 * Create your own resumption-like behavior using
 * a while loop that repeats until an exception
 * is no longer thrown.
 */
class Resumption {
    public static void f(int count) throws IllegalArgumentException {
        if (count % 3 != 0) {
            throw new IllegalArgumentException(String.valueOf(count));
        } else {
            System.out.println("Argument legal, count = " + count);
        }
    }
}

public class E05_ResumptionLikeBehavior {
    public static void main(String[] args) {
        int count = 1;
        System.out.println("Starting");
        while (true) {
            try {
                System.out.println("Try with count " + count);
                Resumption.f(count);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Caught IllegalArgumentException, e.getMessage = " + e.getMessage());
                count++;
            }
        }
        System.out.println("Program completed");
    }
}
/* Output:
Starting
Try with count 1
Caught IllegalArgumentException, e.getMessage = 1
Try with count 2
Caught IllegalArgumentException, e.getMessage = 2
Try with count 3
Argument legal, count = 3
Program completed
 */