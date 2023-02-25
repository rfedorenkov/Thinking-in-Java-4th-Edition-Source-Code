package containers;

import net.mindview.util.CountingGenerator;
import net.mindview.util.CountingIntegerList;
import net.mindview.util.Generated;

import java.util.*;

/**
 * Demonstrates performance differences in Lists.
 * {Args: 100 500} Small to keep build testing short
 */
public class ListPerformance {
    static Random rand = new Random();
    static int reps = 1000;
    static List<Test<List<Integer>>> tests = new ArrayList<>();
    static List<Test<LinkedList<Integer>>> qTests = new ArrayList<>();

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

        tests.add(new Test<List<Integer>>("set") {
            @Override
            public int test(List<Integer> list, TestParam tp) {
                int loops = tp.loops * reps;
                int listSize = list.size();
                for (int i = 0; i < loops; i++)
                    list.set(rand.nextInt(listSize), 47);
                return loops;
            }
        });

        tests.add(new Test<List<Integer>>("iteradd") {
            @Override
            public int test(List<Integer> list, TestParam tp) {
                final int LOOPS = 1000000;
                int half = list.size() / 2;
                ListIterator<Integer> it = list.listIterator(half);
                for (int i = 0; i < LOOPS; i++)
                    it.add(47);
                return LOOPS;
            }
        });

        tests.add(new Test<List<Integer>>("insert") {
            @Override
            public int test(List<Integer> list, TestParam tp) {
                int loops = tp.loops;
                for (int i = 0; i < loops; i++)
                    list.add(5, 47); // Minimize random-access cost
                return loops;
            }
        });

        tests.add(new Test<List<Integer>>("remove") {
            @Override
            public int test(List<Integer> list, TestParam tp) {
                int loops = tp.loops;
                int size = tp.size;
                for (int i = 0; i < loops; i++) {
                    list.clear();
                    list.addAll(new CountingIntegerList(size));
                    while (list.size() > 5)
                        list.remove(5); // Minimize random-access cost
                }
                return loops * size;
            }
        });

        // Test for queue behavior:
        qTests.add(new Test<LinkedList<Integer>>("addFirst") {
            @Override
            public int test(LinkedList<Integer> list, TestParam tp) {
                int loops = tp.loops;
                int size = tp.size;
                for (int i = 0; i < loops; i++) {
                    list.clear();
                    for (int j = 0; j < size; j++)
                        list.addFirst(47);
                }
                return loops * size;
            }
        });

        qTests.add(new Test<LinkedList<Integer>>("addLast") {
            @Override
            public int test(LinkedList<Integer> list, TestParam tp) {
                int loops = tp.loops;
                int size = tp.size;
                for (int i = 0; i < loops; i++) {
                    list.clear();
                    for (int j = 0; j < size; j++)
                        list.addLast(47);
                }
                return loops * size;
            }
        });

        qTests.add(new Test<LinkedList<Integer>>("rmFirst") {
            @Override
            public int test(LinkedList<Integer> list, TestParam tp) {
                int loops = tp.loops;
                int size = tp.size;
                for (int i = 0; i < loops; i++) {
                    list.clear();
                    list.addAll(new CountingIntegerList(size));
                    while (list.size() > 0)
                        list.removeFirst();
                }
                return loops * size;
            }
        });

        qTests.add(new Test<LinkedList<Integer>>("rmLast") {
            @Override
            public int test(LinkedList<Integer> list, TestParam tp) {
                int loops = tp.loops;
                int size = tp.size;
                for (int i = 0; i < loops; i++) {
                    list.clear();
                    list.addAll(new CountingIntegerList(size));
                    while (list.size() > 0)
                        list.removeLast();
                }
                return loops * size;
            }
        });
    }

    static class ListTester extends Tester<List<Integer>> {
        public ListTester(List<Integer> container, List<Test<List<Integer>>> tests) {
            super(container, tests);
        }

        // Fill to the appropriate size before each test:
        @Override
        protected List<Integer> initialize(int size) {
            container.clear();
            container.addAll(new CountingIntegerList(size));
            return container;
        }

        // Convenience method:
        public static void run(List<Integer> list, List<Test<List<Integer>>> tests) {
            new ListTester(list, tests).timedTest();
        }
    }

    public static void main(String[] args) {
        if (args.length > 0)
            Tester.defaultParams = TestParam.array(args);
        // Can only do these two tests on an array:
        Tester<List<Integer>> arrayTest = new Tester<>(null, tests.subList(1, 3)) {
            // This will be called before each test. It
            // produces a non-resizeable array-backed list:
            @Override
            protected List<Integer> initialize(int size) {
                Integer[] ia = Generated.array(Integer.class, new CountingGenerator.Integer(), size);
                return Arrays.asList(ia);
            }
        };
        arrayTest.setHeadline("Array as List");
        arrayTest.timedTest();
        Tester.defaultParams = TestParam.array(10, 5000, 100, 5000, 1000, 1000, 10000, 200);
        if (args.length > 0)
            Tester.defaultParams = TestParam.array(args);
        ListTester.run(new ArrayList<>(), tests);
        ListTester.run(new LinkedList<>(), tests);
        ListTester.run(new Vector<>(), tests);
        Tester.fieldWidth = 12;
        Tester<LinkedList<Integer>> qTest = new Tester<>(new LinkedList<>(), qTests);
        qTest.setHeadline("Queue tests");
        qTest.timedTest();
    }
}
/* Output:
--- Array as List ---
 size     get     set
   10       8       9
  100       7       8
 1000       8       8
10000       8       8
--------------------- ArrayList ---------------------
 size     add     get     set iteradd  insert  remove
   10      34       8       8      47     282      76
  100      11       7       8      50     278      48
 1000       4       7       8      79     194      90
10000      10       8       7     447     917     449
--------------------- LinkedList ---------------------
 size     add     get     set iteradd  insert  remove
   10      52      19      19      40     104      48
  100       6      32      33       3      42      10
 1000      20     349     352       6      48      14
10000       8    3984    3987       3      41      12
----------------------- Vector -----------------------
 size     add     get     set iteradd  insert  remove
   10      33       8       8      48     285      54
  100       8       8       8      49     284      46
 1000       6       8       8      81     191      82
10000       6       8       8     454    1035     456
-------------------- Queue tests --------------------
 size    addFirst     addLast     rmFirst      rmLast
   10          42          36          51          46
  100          11          10          11          10
 1000           6           7           8           7
10000           4           4           5           6
 */