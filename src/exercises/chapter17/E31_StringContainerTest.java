package exercises.chapter17;

import containers.Test;
import containers.TestParam;
import containers.Tester;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Exercise 31
 * Create a container that encapsulates an array of String,
 * and that only allows adding Strings and getting Strings,
 * so that there are no casting issues during use. If the
 * internal array isn't big enough for the next add, your
 * container should automatically resize it. In main(),
 * compare the performance of your container with an ArrayList<String>.
 */
class StringContainer {
    String[] array = new String[16];
    int size = 0;

    public void add(String s) {
        if (size == array.length) {
            String[] arr = new String[array.length * 2 + 1];
            System.arraycopy(array, 0, arr, 0, array.length);
            array = arr;
        }
        array[size++] = s;
    }

    public String get(int i) {
        if (i < 0 || i > size)
            throw new IllegalArgumentException();
        return array[i];
    }

    public int size() {
        return size;
    }

    public void clear() {
        array = new String[size];
        size = 0;
    }

    @Override
    public String toString() {
        return Arrays.toString(array);
    }
}

public class E31_StringContainerTest {
    static Random rand = new Random();
    static int reps = 1000;
    static List<Test<List<String>>> tests = new ArrayList<>();
    static List<Test<StringContainer>> sctests = new ArrayList<>();

    static {
        tests.add(new Test<List<String>>("add") {
            @Override
            public int test(List<String> list, TestParam tp) {
                int loops = tp.loops;
                int listSize = tp.size;
                for (int i = 0; i < loops; i++) {
                    list.clear();
                    for (int j = 0; j < listSize; j++)
                        list.add(Integer.toString(j));
                }
                return loops * listSize;
            }
        });

        tests.add(new Test<List<String>>("get") {
            @Override
            public int test(List<String> list, TestParam tp) {
                int loops = tp.loops * reps;
                int listSize = tp.size;
                for (int i = 0; i < loops; i++) {
                    list.get(rand.nextInt(listSize));
                }
                return loops;
            }
        });

        sctests.add(new Test<StringContainer>("add") {
            @Override
            public int test(StringContainer c, TestParam tp) {
                int loops = tp.loops;
                int listSize = tp.size;
                for (int i = 0; i < loops; i++) {
                    c.clear();
                    for (int j = 0; j < listSize; j++)
                        c.add(Integer.toString(j));
                }
                return loops * listSize;
            }
        });

        sctests.add(new Test<StringContainer>("get") {
            @Override
            public int test(StringContainer c, TestParam tp) {
                int loops = tp.loops * reps;
                int listSize = tp.size;
                for (int i = 0; i < loops; i++) {
                    c.get(rand.nextInt(listSize));
                }
                return loops;
            }
        });
    }

    public static void main(String[] args) {
        Tester.run(new ArrayList<String>(), tests);
        Tester.run(new StringContainer(), sctests);
    }
}
/* Output:
----- ArrayList -----
 size     add     get
   10      55       8
  100      24       8
 1000      11       8
10000      10       8
-- StringContainer --
 size     add     get
   10      37       8
  100      17       8
 1000       8       8
10000       7       8
 */