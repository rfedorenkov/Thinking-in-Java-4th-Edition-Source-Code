package exercises.chapter4;

import java.util.Random;

/**
 * Exercise 2
 * Write a program that generates 25 random int values. For each value, use an
 * if-else statement to classify it as greater than, less than or equals to a
 * second randomly generated value.
 */
public class E02_CompareInts {
    static void compare(int x, int y) {
        if (x < y)
            System.out.println(x + " < " + y);
        else if (x > y)
            System.out.println(x + " > " + y);
        else
            System.out.println(x + " = " + y);
    }

    public static void main(String[] args) {
        Random rand = new Random(47);
        for (int i = 0; i < 25; i++) {
            int x = rand.nextInt(20);
            int y = rand.nextInt(20);
            compare(x, y);
        }
    }
}
/* Output:
18 > 15
13 > 1
1 < 9
8 > 0
2 < 7
8 = 8
11 > 9
9 < 18
18 > 1
0 < 18
16 > 0
11 > 2
4 > 3
6 < 15
10 > 2
14 > 4
10 > 6
2 < 16
13 > 4
4 < 10
3 > 1
18 > 12
6 < 9
5 < 12
6 < 16
 */