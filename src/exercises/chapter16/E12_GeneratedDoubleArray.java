package exercises.chapter16;

import net.mindview.util.ConvertTo;
import net.mindview.util.CountingGenerator;
import net.mindview.util.Generated;

import java.util.Arrays;

/**
 * Exercise 12
 * Create an initialized array of double using
 * CountingGenerator. Print the result.
 */
public class E12_GeneratedDoubleArray {
    public static void main(String[] args) {
        double[] arr = ConvertTo.primitive(
                Generated.array(Double.class, new CountingGenerator.Double(), 10));
        System.out.println(Arrays.toString(arr));
    }
}
/* Output:
[0.0, 1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0]
 */