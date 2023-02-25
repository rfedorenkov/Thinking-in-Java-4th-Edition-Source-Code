package containers;

import java.util.*;

/**
 * Demonstrates performance differences in Sets.
 * {Args: 100 5000} Small to keep build testing short
 */
public class SetPerformance {
    static List<Test<Set<Integer>>> tests = new ArrayList<>();

    static {
        tests.add(new Test<Set<Integer>>("add") {
            @Override
            public int test(Set<Integer> set, TestParam tp) {
                int loops = tp.loops;
                int size = tp.size;
                for (int i = 0; i < loops; i++) {
                    set.clear();
                    for (int j = 0; j < size; j++)
                        set.add(j);
                }
                return loops * size;
            }
        });

        tests.add(new Test<Set<Integer>>("contains") {
            @Override
            public int test(Set<Integer> set, TestParam tp) {
                int loops = tp.loops;
                int span = tp.size * 2;
                for (int i = 0; i < loops; i++)
                    for (int j = 0; j < span; j++)
                        set.contains(j);
                return loops * span;
            }
        });

        tests.add(new Test<Set<Integer>>("iterate") {
            @Override
            public int test(Set<Integer> set, TestParam tp) {
                int loops = tp.loops * 10;
                for (int i = 0; i < loops; i++) {
                    Iterator<Integer> it = set.iterator();
                    while (it.hasNext())
                        it.next();
                }
                return loops * set.size();
            }
        });
    }

    public static void main(String[] args) {
        if (args.length > 0)
            Tester.defaultParams = TestParam.array(args);
        Tester.fieldWidth = 10;
        Tester.run(new TreeSet<>(), tests);
        Tester.run(new HashSet<>(), tests);
        Tester.run(new LinkedHashSet<>(), tests);
    }
}
/* Output:
------------- TreeSet -------------
 size       add  contains   iterate
   10       119        52        16
  100        30        14         5
 1000        41        29         3
10000        55        41         4
------------- HashSet -------------
 size       add  contains   iterate
   10        51        43        15
  100         8         2         5
 1000        10         3         5
10000         8         3         4
---------- LinkedHashSet ----------
 size       add  contains   iterate
   10        94        34        11
  100        10         5         3
 1000         9         5         3
10000         9         5         4
 */