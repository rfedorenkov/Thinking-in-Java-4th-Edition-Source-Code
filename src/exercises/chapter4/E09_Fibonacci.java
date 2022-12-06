package exercises.chapter4;

/**
 * Exercise 9
 * A Fibonacci sequence is the sequence of number 1, 1, 2, 3, 5, 8, 13, 21, 34, etc.,
 * where each number (from the third on) is the sum of the previous two.
 * Create a method that takes an integer as an argument and displays that many
 * Fibonacci numbers starting from the beginning. If, e.g., you run java Fibonacci 5
 * (where Fibonacci is the name of the class) the output will be: 1, 1, 2, 3, 5.
 */
public class E09_Fibonacci {
    public static final int DEFAULT_VALUE = 15;

    public static int fibonacci(int n) {
        if (n == 0)
            return 0;
        if (n == 1)
            return 1;
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    public static void main(String[] args) {
        int n = DEFAULT_VALUE;
        if (args.length == 0) {
            System.out.println("You can run the program with a numeric argument like this: java fibonacci 5");
            System.out.println("This program is started with the default parameter - 15.");
        } else {
            try {
                n = Integer.parseInt(args[0]);
                if (n < 0) {
                    System.out.println("Can't use negative numbers.");
                    System.exit(1);
                }
                System.out.printf("This program is started with parameter - %d.%n", n);
            } catch (NumberFormatException e) {
                System.out.println("Invalid numeric attribute. Try java fibonacci 5");
                System.exit(1);
            }
        }
        for (int i = 1; i <= n; i++) {
            String format = String.format("%d%s", fibonacci(i), i != n ? ", " : ".");
            System.out.print(format);
        }
    }
}
/* Output:
You can run the program with a numeric argument like this: java fibonacci 5
This program is launched with the default parameter - 15.
1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610.

OR

This program is launched with the parameter - 7.
1, 1, 2, 3, 5, 8, 13.
 */