package exercises.chapter3;

/**
 * Exercise 10
 * Write a program with two constant values, one with alternating binary ones and
 * zeros, with a zero in the least-significant digit (hint: It's easiest to
 * use hexadecimal constants for this). Take these two values and combine them in
 * all possible ways using the bitwise operators, and display the results using
 * Integer.toBinaryString()
 */
public class E10_BitwiseOperators {
    public static void main(String[] args) {
        int i = 0xAAAA;
        int j = 0x5555;
        System.out.println("i = " + Integer.toBinaryString(i));
        System.out.println("j = " + Integer.toBinaryString(j));
        System.out.println("~i = " + Integer.toBinaryString(~i));
        System.out.println("~j = " + Integer.toBinaryString(~j));
        System.out.println("i & j = " + Integer.toBinaryString(i & j));
        System.out.println("i | j = " + Integer.toBinaryString(i | j));
        System.out.println("i ^ j = " + Integer.toBinaryString(i ^ j));
    }
}
/* Output:
i = 1010101010101010
j = 101010101010101
~i = 11111111111111110101010101010101
~j = 11111111111111111010101010101010
i & j = 0
i | j = 1111111111111111
i ^ j = 1111111111111111
 */