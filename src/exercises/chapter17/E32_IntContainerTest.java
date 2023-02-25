package exercises.chapter17;

import containers.Test;
import containers.TestParam;
import containers.Tester;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Exercise 32
 * Repeat the previous exercise for a container of int,
 * and compare the performance to an ArrayList<Integer>.
 * In your performance comparison, include the process
 * of incrementing each object in the container.
 */
class IntContainer {
    int[] array = new int[16];
    int size = 0;

    public void add(int i) {
        if (size == array.length) {
            int[] arr = new int[array.length * 2 + 1];
            System.arraycopy(array, 0, arr, 0, array.length);
            array = arr;
        }
        array[size++] = i;
    }

    public int get(int i) {
        if (i < 0 || i > size)
            throw new IllegalArgumentException();
        return array[i];
    }

    public void set(int index, int value) {
        if (index < 0 || index > size)
            throw new IllegalArgumentException();
        array[index] = value;
    }

    public int size() {
        return size;
    }

    public void clear() {
        array = new int[array.length];
        size = 0;
    }

    @Override
    public String toString() {
        return Arrays.toString(array);
    }
}

public class E32_IntContainerTest {
    static Random rand = new Random();
    static int reps = 1000;
    static List<Test<List<Integer>>> tests = new ArrayList<>();
    static List<Test<IntContainer>> sctests = new ArrayList<>();

    static {
        tests.add(new Test<List<Integer>>("add") {
            @Override
            public int test(List<Integer> list, TestParam tp) {
                int loops = tp.loops;
                int listSize = tp.size;
                for (int i = 0; i < loops; i++) {
                    list.clear();
                    for (int j = 0; j < listSize; j++)
                        list.add(j);
                }
                return loops * listSize;
            }
        });

        tests.add(new Test<List<Integer>>("get") {
            @Override
            public int test(List<Integer> list, TestParam tp) {
                int loops = tp.loops * reps;
                int listSize = tp.size;
                for (int i = 0; i < loops; i++) {
                    list.get(rand.nextInt(listSize));
                }
                return loops;
            }
        });

        tests.add(new Test<List<Integer>>("incr") {
            @Override
            public int test(List<Integer> list, TestParam tp) {
                int loops = tp.loops * reps;
                int listSize = tp.size;
                for (int i = 0; i < loops; i++) {
                    int index = rand.nextInt(listSize);
                    list.set(index, list.get(index) + 1);
                }
                return loops;
            }
        });

        sctests.add(new Test<IntContainer>("add") {
            @Override
            public int test(IntContainer c, TestParam tp) {
                int loops = tp.loops;
                int listSize = tp.size;
                for (int i = 0; i < loops; i++) {
                    c.clear();
                    for (int j = 0; j < listSize; j++)
                        c.add(j);
                }
                return loops * listSize;
            }
        });

        sctests.add(new Test<IntContainer>("get") {
            @Override
            public int test(IntContainer c, TestParam tp) {
                int loops = tp.loops * reps;
                int listSize = tp.size;
                for (int i = 0; i < loops; i++) {
                    c.get(rand.nextInt(listSize));
                }
                return loops;
            }
        });

        sctests.add(new Test<IntContainer>("incr") {
            @Override
            public int test(IntContainer c, TestParam tp) {
                int loops = tp.loops * reps;
                int listSize = tp.size;
                for (int i = 0; i < loops; i++) {
                    int index = rand.nextInt(listSize);
                    c.set(index, c.get(index) + 1);
                }
                return loops;
            }
        });
    }

    public static void main(String[] args) {
        Tester.run(new ArrayList<Integer>(), tests);
        Tester.run(new IntContainer(), sctests);
    }
}
/* Output:
--------- ArrayList ---------
 size     add     get    incr
   10      42       8      12
  100      11       8      10
 1000       5       8      11
10000       4      10      12
-------- IntContainer --------
 size     add     get    incr
   10      21       8       8
  100       6       7       8
 1000       0       7       7
10000       0       7       7
 */