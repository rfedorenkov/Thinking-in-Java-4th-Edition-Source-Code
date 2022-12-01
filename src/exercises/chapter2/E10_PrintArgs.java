package exercises.chapter2;

/**
 * Exercise 10
 * Write a program that prints three arguments taken from
 * the command line. You'll need to index into the command-line
 * array of Strings.
 */
public class E10_PrintArgs {
    public static void main(String[] args) {
        if (args.length != 3) {
            System.err.println("You need to enter three Args: A B C");
            System.exit(1);
        }
        System.out.println(args[0]);
        System.out.println(args[1]);
        System.out.println(args[2]);
    }
}
/* Output:
A
B
C
 */