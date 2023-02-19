package arrays;

import net.mindview.util.CountingGenerator;
import net.mindview.util.Generated;
import net.mindview.util.Generator;

import java.util.Arrays;
import java.util.Random;

import static net.mindview.util.Print.print;

public class TestGenerated {
    public static void main(String[] args) {
        Integer[] a = { 9, 8, 7, 6 };
        System.out.println(Arrays.toString(a));
        a = Generated.array(a, new CountingGenerator.Integer());
        System.out.println(Arrays.toString(a));
        Integer[] b = Generated.array(Integer.class, new CountingGenerator.Integer(), 15);
        System.out.println(Arrays.toString(b));
    }

    /**
     * Implementing Comparable in a class.
     */
    public static class CompType implements Comparable<CompType> {
        int i;
        int j;
        private static int count = 1;

        public CompType(int n1, int n2) {
            i = n1;
            j = n2;
        }

        @Override
        public String toString() {
            String result = "[i = " + i + ", j = " + j + "]";
            if (count++ % 3 == 0)
                result += "\n";
            return result;
        }

        @Override
        public int compareTo(CompType rv) {
            return (i < rv.i ? -1 : (i == rv.i ? 0 : 1));
        }

        private static Random r = new Random(47);

        public static Generator<CompType> generator() {
            return new Generator<CompType>() {
                @Override
                public CompType next() {
                    return new CompType(r.nextInt(100), r.nextInt(100));
                }
            };
        }

        public static void main(String[] args) {
            CompType[] a = Generated.array(new CompType[12], generator());
            print("before sorting:");
            print(Arrays.toString(a));
            Arrays.sort(a);
            print("after sorting");
            print(Arrays.toString(a));
        }
    }
}
/* Output:
[9, 8, 7, 6]
[0, 1, 2, 3]
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14]
 */