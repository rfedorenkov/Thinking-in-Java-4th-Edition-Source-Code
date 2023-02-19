package exercises.chapter16;

import net.mindview.util.ConvertTo;
import net.mindview.util.CountingGenerator;
import net.mindview.util.Generated;

import java.util.Arrays;

import static net.mindview.util.Print.print;

/**
 * Exercise 14
 * Create an array of each primitive type, then fill
 * each array by using CountingGenerator.
 * Print each array.
 */
public class E14_AllPrimitiveArraysFill {
    public static void main(String[] args) {
        boolean[] a1 = ConvertTo.primitive(
                Generated.array(Boolean.class, new CountingGenerator.Boolean(), 10));
        char[] a2 = ConvertTo.primitive(
                Generated.array(Character.class, new CountingGenerator.Character(), 10));
        byte[] a3 = ConvertTo.primitive(
                Generated.array(Byte.class, new CountingGenerator.Byte(), 10));
        short[] a4 = ConvertTo.primitive(
                Generated.array(Short.class, new CountingGenerator.Short(), 10));
        int[] a5 = ConvertTo.primitive(
                Generated.array(Integer.class, new CountingGenerator.Integer(), 10));
        long[] a6 = ConvertTo.primitive(
                Generated.array(Long.class, new CountingGenerator.Long(), 10));
        float[] a7 = ConvertTo.primitive(
                Generated.array(Float.class, new CountingGenerator.Float(), 10));
        double[] a8 = ConvertTo.primitive(
                Generated.array(Double.class, new CountingGenerator.Double(), 10));
        print("a1 = " + Arrays.toString(a1));
        print("a2 = " + Arrays.toString(a2));
        print("a3 = " + Arrays.toString(a3));
        print("a4 = " + Arrays.toString(a4));
        print("a5 = " + Arrays.toString(a5));
        print("a6 = " + Arrays.toString(a6));
        print("a7 = " + Arrays.toString(a7));
        print("a8 = " + Arrays.toString(a8));
    }
}
/* Output:
a1 = [true, false, true, false, true, false, true, false, true, false]
a2 = [a, b, c, d, e, f, g, h, i, j]
a3 = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
a4 = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
a5 = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
a6 = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
a7 = [0.0, 1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0]
a8 = [0.0, 1.0, 2.0, 3.0, 4.0, 5.0, 6.0, 7.0, 8.0, 9.0]
 */