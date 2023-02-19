package exercises.chapter16;

import java.util.Arrays;

/**
 * Exercise 5
 * Demonstrate that multidimensional arrays of non-primitive
 * types are automatically initialized to null.
 */
public class E05_NonPrimitiveMultidimensionalArray {
    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(new Integer[2][3][2]));
    }
}
/* Output:
[[[null, null], [null, null], [null, null]], [[null, null], [null, null], [null, null]]]
 */