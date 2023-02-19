package arrays;

import net.mindview.util.Generated;

import java.util.Arrays;
import java.util.Collections;

import static net.mindview.util.Print.print;

/**
 * Creating multidimensional arrays.
 */
public class MultidimensionalPrimitiveArray {
    public static void main(String[] args) {
        int[][] a = {
                { 1, 2, 3 },
                { 4, 5, 6 }
        };
        System.out.println(Arrays.deepToString(a));
    }

    /**
     * The Collections.reverseOrder() Comparator.
     */
    public static class Reverse {
        public static void main(String[] args) {
            TestGenerated.CompType[] a = Generated.array(new TestGenerated.CompType[12], TestGenerated.CompType.generator());
            print("before sorting:");
            print(Arrays.toString(a));
            Arrays.sort(a, Collections.reverseOrder());
            print("after sorting:");
            print(Arrays.toString(a));
        }
    }
}
/* Output:
[[1, 2, 3], [4, 5, 6]]
 */