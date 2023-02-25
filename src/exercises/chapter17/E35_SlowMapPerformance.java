package exercises.chapter17;

import containers.Test;
import containers.TestParam;
import containers.Tester;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Exercise 35
 * Modify MapPerformance.java to include tests to SlowMap.
 */
public class E35_SlowMapPerformance {
    static List<Test<Map<Integer, Integer>>> tests = new ArrayList<>();

    static {
        tests.add(new Test<Map<Integer, Integer>>("put") {
            @Override
            public int test(Map<Integer, Integer> map, TestParam tp) {
                int loops = tp.loops;
                int size = tp.size;
                for (int i = 0; i < loops; i++) {
                    map.clear();
                    for (int j = 0; j < size; j++) {
                        map.put(i, j);
                    }
                }
                return loops * size;
            }
        });

        tests.add(new Test<Map<Integer, Integer>>("get") {
            @Override
            public int test(Map<Integer, Integer> map, TestParam tp) {
                int loops = tp.loops;
                int span = tp.size * 2;
                for (int i = 0; i < loops; i++)
                    for (int j = 0; j < span; j++)
                        map.get(j);
                return loops * span;
            }
        });

        tests.add(new Test<Map<Integer, Integer>>("iterate") {
            @Override
            public int test(Map<Integer, Integer> map, TestParam tp) {
                int loops = tp.loops * 10;
                for (int i = 0; i < loops; i++) {
                    Iterator<Map.Entry<Integer, Integer>> it = map.entrySet().iterator();
                    while (it.hasNext())
                        it.next();
                }
                return loops * tp.size;
            }
        });
    }

    public static void main(String[] args) {
        Tester.run(new SlowMap2<>(), tests);
        Tester.run(new SlowMap3<>(), tests);
    }
}
/* Output:
---------- SlowMap2 ----------
 size     put     get iterate
   10     101      33       9
  100      17       6       0
 1000      20       4       0
10000      27       3       0
---------- SlowMap3 ----------
 size     put     get iterate
   10      71      34      11
  100      31       3       0
 1000      11       3       0
10000      29       3       0
 */