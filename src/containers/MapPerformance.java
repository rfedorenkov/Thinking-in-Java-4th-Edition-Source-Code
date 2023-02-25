package containers;

import java.util.*;

/**
 * Demonstrates performance differences in Maps.
 * {Args: 100 5000} Small to keep build testing short
 */
public class MapPerformance {
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

        tests.add(new Test<Map<Integer, Integer>>("get") {
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
        if (args.length > 0)
            Tester.defaultParams = TestParam.array(args);
        Tester.run(new TreeMap<>(), tests);
        Tester.run(new HashMap<>(), tests);
        Tester.run(new LinkedHashMap<>(), tests);
        Tester.run(new IdentityHashMap<>(), tests);
        Tester.run(new WeakHashMap<>(), tests);
        Tester.run(new Hashtable<>(), tests);
    }
}
/* Output:
---------- TreeMap ----------
 size     put     get     get
   10      66      29       8
  100      13       2       0
 1000       6       3       0
10000       4       2       0
---------- HashMap ----------
 size     put     get     get
   10      69      39      12
  100      23       2       0
 1000       8       2       0
10000       7       2       0
------- LinkedHashMap -------
 size     put     get     get
   10     131      24       4
  100      13       7       0
 1000       9       6       0
10000       7       8       0
------ IdentityHashMap ------
 size     put     get     get
   10     112      16      12
  100      82      34       6
 1000      83      84       6
10000      66      88      10
-------- WeakHashMap --------
 size     put     get     get
   10      55      15       7
  100       7       6       0
 1000       8       5       0
10000       6       5       0
--------- Hashtable ---------
 size     put     get     get
   10      49      10       6
  100       6       4       0
 1000       6       4       0
10000       6       8       0
 */