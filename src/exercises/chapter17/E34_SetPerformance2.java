package exercises.chapter17;

import containers.Test;
import containers.TestParam;
import containers.Tester;
import net.mindview.util.CountingGenerator;
import net.mindview.util.Generator;

import java.util.*;

/**
 * Exercise 34
 * Modify SetPerformance.java so that the Sets hold String objects
 * instead of Integers. Use a Generator from the Arrays chapter
 * to create test values.
 * {Args: 100 5000} Small to keep build testing short
 */
public class E34_SetPerformance2 {
    static List<Test<Set<String>>> tests = new ArrayList<>();
    private static Generator<String> gen;

    static {
        tests.add(new Test<Set<String>>("add") {
            @Override
            public int test(Set<String> set, TestParam tp) {
                int loops = tp.loops;
                int size = tp.size;
                for (int i = 0; i < loops; i++) {
                    set.clear();
                    gen = new CountingGenerator.String();
                    for (int j = 0; j < size; j++) {
                        set.add(gen.next());
                    }
                }
                return loops * size;
            }
        });

        tests.add(new Test<Set<String>>("contains") {
            @Override
            public int test(Set<String> set, TestParam tp) {
                gen = new CountingGenerator.String();
                int loops = tp.loops;
                int span = tp.size * 2;
                for (int i = 0; i < loops; i++) {
                    for (int j = 0; j < span; j++) {
                        set.contains(gen.next());
                    }
                }
                return loops * span;
            }
        });

        tests.add(new Test<Set<String>>("iterate") {
            @Override
            public int test(Set<String> set, TestParam tp) {
                int loops = tp.loops * 10;
                for (int i = 0; i < loops; i++) {
                    Iterator<String> it = set.iterator();
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
   10       222        70        17
  100        76        36         5
 1000        47        39         2
10000        47        39         2
------------- HashSet -------------
 size       add  contains   iterate
   10        73        79        20
  100        52        36         6
 1000        42        36         5
10000        43        36         6
---------- LinkedHashSet ----------
 size       add  contains   iterate
   10       101        64        10
  100        59        37         4
 1000        44        37         4
10000        43        37         3
 */