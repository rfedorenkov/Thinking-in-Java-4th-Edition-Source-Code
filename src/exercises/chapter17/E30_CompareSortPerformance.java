package exercises.chapter17;

import containers.Test;
import containers.TestParam;
import containers.Tester;
import net.mindview.util.CollectionData;
import net.mindview.util.RandomGenerator;

import java.util.*;

/**
 * Exercise 30
 * Compare the performance of Collections.sort()
 * between an ArrayList and LinkedList.
 */
public class E30_CompareSortPerformance {
    static List<Test<List<Integer>>> tests = new ArrayList<>();

    static {
        tests.add(new Test<>("sort") {
            @Override
            public int test(List<Integer> list, TestParam tp) {
                int loops = tp.loops;
                for (int i = 0; i < loops; i++) {
                    list.clear();
                    list.addAll(CollectionData.list(new RandomGenerator.Integer(), tp.size));
                }
                return loops;
            }
        });
    }

    static class ListTester extends Tester<List<Integer>> {
        public ListTester(List<Integer> container, List<Test<List<Integer>>> tests) {
            super(container, tests);
        }

        @Override
        protected List<Integer> initialize(int size) {
            container.clear();
            container.addAll(CollectionData.list(new RandomGenerator.Integer(), size));
            return container;
        }

        // Convenience method:
        public static void run(List<Integer> list, List<Test<List<Integer>>> tests) {
            new ListTester(list, tests).timedTest();
        }
    }

    public static void main(String[] args) {
        ListTester.run(new ArrayList<>(), tests);
        ListTester.run(new LinkedList<>(), tests);
    }
}
/* Output:
- ArrayList -
 size    sort
   10     710
  100    2707
 1000   12149
10000  120429
- LinkedList -
 size    sort
   10     327
  100    1462
 1000   12989
10000  131515
 */