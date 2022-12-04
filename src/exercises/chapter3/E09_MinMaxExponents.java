package exercises.chapter3;

/**
 * Exercise 9
 * Display the largest and smallest numbers for
 * both float and double exponential notation.
 */
public class E09_MinMaxExponents {
    public static void main(String[] args) {
        double maxDouble = Double.MAX_VALUE;
        System.out.println("Max double = " + maxDouble);
        double minDouble = Double.MIN_VALUE;
        System.out.println("Min double = " + minDouble);
        float maxFloat = Float.MAX_VALUE;
        System.out.println("Max float = " + maxFloat);
        float minFloat = Float.MIN_VALUE;
        System.out.println("Min float = " + minFloat);
    }
}
/* Output:
Max double = 1.7976931348623157E308
Min double = 4.9E-324
Max float = 3.4028235E38
Min float = 1.4E-45
 */