package exercises.chapter16;

import net.mindview.util.Generated;
import net.mindview.util.RandomGenerator;

import java.util.Arrays;

/**
 * Exercise 22
 * Show that the results of performing a binarySearch()
 * on an unsorted array are unpredictable.
 */
public class E22_ArrayBinarySearchTest {
    public static void main(String[] args) {
        String[] sa = Generated.array(new String[10], new RandomGenerator.String(5));
        System.out.println("before sort: " + Arrays.toString(sa));
        int index = Arrays.binarySearch(sa, sa[3]);
        System.out.println("Index: " + index);
        try {
            System.out.println(sa[index]); // throw ArrayIndexOutOfBoundsException
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e);
        }
        Arrays.sort(sa);
        System.out.println("after sort: " + Arrays.toString(sa));
        index = Arrays.binarySearch(sa, sa[3]);
        System.out.println("Index: " + index + " = " + sa[index]);
    }
}
/* Output:
before sort: [YNzbr, nyGcF, OWZnT, cQrGs, eGZMm, JMRoE, suEcU, OneOE, dLsmw, HLGEa]
Index: -2
java.lang.ArrayIndexOutOfBoundsException: Index -2 out of bounds for length 10
after sort: [HLGEa, JMRoE, OWZnT, OneOE, YNzbr, cQrGs, dLsmw, eGZMm, nyGcF, suEcU]
Index: 3 = OneOE
 */