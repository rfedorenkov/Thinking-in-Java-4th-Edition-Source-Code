package exercises.chapter17;

import containers.Test;
import containers.TestParam;
import containers.Tester;
import net.mindview.util.*;

import java.util.*;

/**
 * Exercise 29
 * Modify ListPerformance.java so that the Lists hold
 * String objects instead of Integers. Use a Generator
 * from the Arrays chapter to create test values.
 * {Args: 100 500} Small to keep build testing short
 */
public class E29_ListPerformance2 {
    static Random rand = new Random();
    static Generator<String> gen = new CountingGenerator.String();
    static int reps = 1000;
    static List<Test<List<String>>> tests = new ArrayList<>();
    static List<Test<LinkedList<String>>> qTests = new ArrayList<>();

    static {
        tests.add(new Test<List<String>>("add") {
            @Override
            public int test(List<String> list, TestParam tp) {
                int loops = tp.loops;
                int listSize = tp.size;
                for (int i = 0; i < loops; i++) {
                    list.clear();
                    for (int j = 0; j < listSize; j++)
                        list.add(gen.next());
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

        tests.add(new Test<List<String>>("set") {
            @Override
            public int test(List<String> list, TestParam tp) {
                int loops = tp.loops * reps;
                int listSize = list.size();
                String next = gen.next();
                for (int i = 0; i < loops; i++)
                    list.set(rand.nextInt(listSize), next);
                return loops;
            }
        });

        tests.add(new Test<List<String>>("iteradd") {
            @Override
            public int test(List<String> list, TestParam tp) {
                final int LOOPS = 1000000;
                int half = list.size() / 2;
                ListIterator<String> it = list.listIterator(half);
                for (int i = 0; i < LOOPS; i++)
                    it.add(gen.next());
                return LOOPS;
            }
        });

        tests.add(new Test<List<String>>("insert") {
            @Override
            public int test(List<String> list, TestParam tp) {
                int loops = tp.loops;
                String next = gen.next();
                for (int i = 0; i < loops; i++)
                    list.add(5, next); // Minimize random-access cost
                return loops;
            }
        });

        tests.add(new Test<List<String>>("remove") {
            @Override
            public int test(List<String> list, TestParam tp) {
                int loops = tp.loops;
                int size = tp.size;
                for (int i = 0; i < loops; i++) {
                    list.clear();
                    list.addAll(CollectionData.list(gen, size));
                    while (list.size() > 5)
                        list.remove(5); // Minimize random-access cost
                }
                return loops * size;
            }
        });

        // Test for queue behavior:
        qTests.add(new Test<LinkedList<String>>("addFirst") {
            @Override
            public int test(LinkedList<String> list, TestParam tp) {
                int loops = tp.loops;
                int size = tp.size;
                for (int i = 0; i < loops; i++) {
                    list.clear();
                    for (int j = 0; j < size; j++)
                        list.addFirst(gen.next());
                }
                return loops * size;
            }
        });

        qTests.add(new Test<LinkedList<String>>("addLast") {
            @Override
            public int test(LinkedList<String> list, TestParam tp) {
                int loops = tp.loops;
                int size = tp.size;
                for (int i = 0; i < loops; i++) {
                    list.clear();
                    for (int j = 0; j < size; j++)
                        list.addLast(gen.next());
                }
                return loops * size;
            }
        });

        qTests.add(new Test<LinkedList<String>>("rmFirst") {
            @Override
            public int test(LinkedList<String> list, TestParam tp) {
                int loops = tp.loops;
                int size = tp.size;
                for (int i = 0; i < loops; i++) {
                    list.clear();
                    list.addAll(CollectionData.list(gen, size));
                    while (list.size() > 0)
                        list.removeFirst();
                }
                return loops * size;
            }
        });

        qTests.add(new Test<LinkedList<String>>("rmLast") {
            @Override
            public int test(LinkedList<String> list, TestParam tp) {
                int loops = tp.loops;
                int size = tp.size;
                for (int i = 0; i < loops; i++) {
                    list.clear();
                    list.addAll(CollectionData.list(gen, size));
                    while (list.size() > 0)
                        list.removeLast();
                }
                return loops * size;
            }
        });
    }

    static class ListTester extends Tester<List<String>> {
        public ListTester(List<String> container, List<Test<List<String>>> tests) {
            super(container, tests);
        }

        // Fill to the appropriate size before each test:
        @Override
        protected List<String> initialize(int size) {
            container.clear();
            container.addAll(CollectionData.list(gen, size));
            return container;
        }

        // Convenience method:
        public static void run(List<String> list, List<Test<List<String>>> tests) {
            new ListTester(list, tests).timedTest();
        }
    }

    public static void main(String[] args) {
        if (args.length > 0)
            Tester.defaultParams = TestParam.array(args);
        // Can only do these two tests on an array:
        Tester<List<String>> arrayTest = new Tester<>(null, tests.subList(1, 3)) {
            // This will be called before each test. It
            // produces a non-resizeable array-backed list:
            @Override
            protected List<String> initialize(int size) {
                String[] ia = Generated.array(String.class, new CountingGenerator.String(), size);
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
        Tester<LinkedList<String>> qTest = new Tester<>(new LinkedList<>(), qTests);
        qTest.setHeadline("Queue tests");
        qTest.timedTest();
    }
}
/* Output:
--- Array as List ---
 size     get     set
   10       8       9
  100       8       8
 1000       8       8
10000       8      11
--------------------- ArrayList ---------------------
 size     add     get     set iteradd  insert  remove
   10      34       8       8      49     294      80
  100      10       7       8      48     277      48
 1000       7       7       8      77     214      90
10000       9       8       8     448     917     447
--------------------- LinkedList ---------------------
 size     add     get     set iteradd  insert  remove
   10      52      19      19      35      79      48
  100       6      32      33       3      44      11
 1000      19     348     350       6      43      13
10000       8    3991    3984       3      40      13
----------------------- Vector -----------------------
 size     add     get     set iteradd  insert  remove
   10      32       8       9      50     288      53
  100       5       8       9      51     288      48
 1000       6       8       9      81     192      82
10000       6       8       8     458     912     455
-------------------- Queue tests --------------------
 size    addFirst     addLast     rmFirst      rmLast
   10          42          34          47          44
  100          10          10          11          11
 1000           6           7           9           7
10000           4           4           5           6
 */