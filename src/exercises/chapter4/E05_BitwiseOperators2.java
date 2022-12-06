package exercises.chapter4;

/**
 * Exercise 5
 * Repeat exercise 10 from the previous chapter,
 * but use the ternary operator and a bitwise test
 * instead of Integer.toBinaryString() to display
 * the ones and zeroes.
 */
public class E05_BitwiseOperators2 {
    static void binaryTest(int i) {
        char[] buffer = new char[32];
        int bufferPosition = 32;
        do {
            buffer[--bufferPosition] = ((i & 0x01) != 0) ? '1' : '0';
            i >>>= 1;
        } while (i != 0);
        for (int j = bufferPosition; j < 32; j++) {
            System.out.print(buffer[j]);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int i = 0xAAAA;
        int j = 0x5555;
        System.out.print("i = ");
        binaryTest(i);
        System.out.print("j = ");
        binaryTest(j);
        System.out.print("~i = ");
        binaryTest(~i);
        System.out.print("~j = ");
        binaryTest(~j);
        System.out.print("i & j = ");
        binaryTest(i & j);
        System.out.print("i | j = ");
        binaryTest(i | j);
        System.out.print("i ^ j = ");
        binaryTest(i ^ j);
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