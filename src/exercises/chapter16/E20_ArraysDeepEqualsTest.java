package exercises.chapter16;

import java.util.Arrays;

/**
 * Exercise 20
 * Demonstrate deepEquals() for multidimensional arrays.
 */
public class E20_ArraysDeepEqualsTest {
    public static void main(String[] args) {
        int[][] a1 = {
                { 1, 2, 3 },
                { 4, 5, 6 },
                { 7, 8, 9 },
        };
        int[][] a2 = {
                { 1, 2, 3 },
                { 4, 5, 6 },
                { 7, 8, 9 },
        };
        System.out.println("Arrays.equals(a1, a2) = " + Arrays.equals(a1, a2));
        System.out.println("Arrays.deepEquals(a1, a2) = " + Arrays.deepEquals(a1, a2));
    }
}
/* Output:
Arrays.equals(a1, a2) = false
Arrays.deepEquals(a1, a2) = true
 */