package exercises.chapter4;

/**
 * Exercise 4
 * Write a program to detect and print prime numbers
 * (integers evenly divisible only by themselves and 1),
 * using two nested for loops and the modulus operator (%).
 */
public class E04_FindPrimes {
    public static void main(String[] args) {
        for (int i = 1; i < 100; i++) {
            boolean prime = true;
            for (int j = 2; j < i; j++) {
                if (i % j == 0) {
                    prime = false;
                    break;
                }
            }
            if (prime)
                System.out.println(i);
        }
    }
}
/* Output:
1
2
3
5
7
11
13
17
19
23
29
31
37
41
43
47
53
59
61
67
71
73
79
83
89
97
 */