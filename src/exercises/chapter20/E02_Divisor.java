package exercises.chapter20;

import annotations.ExtractInterface;

/**
 * Exercise 2
 * Add support for division to the interface extractor.
 */
@ExtractInterface("IDivisor")
public class E02_Divisor {
    public int divide(int x, int y) {
        if (y == 0) throw new ArithmeticException("Divide by zero");
        int total = 0;
        while (x >= y) {
            x = sub(x, y);
            total++;
        }
        return total;
    }

    private int sub(int x, int y) {
        return x - y;
    }

    public static void main(String[] args) {
        E02_Divisor divisor = new E02_Divisor();
        System.out.println("1568 / 16 = " + divisor.divide(1568, 16));
    }
}
/* Output:
1568 / 16 = 98
 */