package exercises.chapter4;

/**
 * Exercise 10
 * A vampire number has an even number of digits and is formed by multiplying
 * a pair of numbers containing half the number of digits of the result.
 * The digits are taken from the original number in any order.
 * Pairs of trailing zeroes are not allowed. Examples include:
 * 1260 = 21 * 60
 * 1827 = 21 * 87
 * 2187 = 27 * 81
 * Write a program that finds all the 4-digit vampire numbers.
 * (Suggested by Dan Forhan.)
 */
public class E10_VampireNumbers {
    public static void main(String[] args) {
        int[] start = new int[4];
        int[] product = new int[4];
        for (int num1 = 10; num1 <= 99; num1++) {
            for (int num2 = num1; num2 <= 99; num2++) {
                if ((num1 * num2) % 9 != (num1 + num2) % 9)
                    continue;
                int result = num1 * num2;
                start[0] = num1 / 10;
                start[1] = num1 % 10;
                start[2] = num2 / 10;
                start[3] = num2 % 10;
                product[0] = result / 1000;
                product[1] = result % 1000 / 100;
                product[2] = result % 100 / 10;
                product[3] = result % 10;
                int count = 0;
                for (int x = 0; x < 4; x++) {
                    for (int y = 0; y < 4; y++) {
                        if (product[x] == start[y]) {
                            count++;
                            product[x] = -1;
                            start[y] = -2;
                            if (count == 4)
                                System.out.println(num1 + " * " + num2 + " : " + result);
                        }
                    }
                }
            }
        }
    }
}
/* Output:
15 * 93 : 1395
21 * 60 : 1260
21 * 87 : 1827
27 * 81 : 2187
30 * 51 : 1530
35 * 41 : 1435
80 * 86 : 6880
 */