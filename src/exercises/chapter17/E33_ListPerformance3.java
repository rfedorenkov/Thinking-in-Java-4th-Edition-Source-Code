package exercises.chapter17;

import containers.Test;
import containers.TestParam;
import containers.Tester;
import net.mindview.util.CountingIntegerList;

import java.util.*;

/**
 * Exercise 33
 * Create a FastTraversalLinkedList that internally uses
 * a LinkedList for rapid insertions and removals, and
 * an ArrayList for rapid traversals and get() operations.
 * Test it by modifying ListPerformance.java
 */
class FastTraversalLinkedList<T> extends AbstractList<T> {
    public LinkedList<T> linkedList = new LinkedList<>();
    public ArrayList<T> arrayList = new ArrayList<>();
    private boolean changeAL = false;
    private boolean changeLL = false;

    @Override
    public boolean add(T t) {
        boolean result = linkedList.add(t);
        changeLL = true;
        return result;
    }

    @Override
    public void add(int index, T element) {
        linkedList.add(index, element);
        changeLL = true;
    }

    @Override
    public boolean addAll(int index, Collection<? extends T> c) {
        boolean change = false;
        for (T t : c) {
            change |= add(t);
        }
        return change;
    }

    @Override
    public T set(int index, T element) {
        if (changeLL)
            synchronize();
        changeAL = true;
        return arrayList.set(index, element);
    }

    @Override
    public int size() {
        if (changeLL)
            synchronize();
        return arrayList.size();
    }

    @Override
    public T get(int index) {
        if (changeLL)
            synchronize();
        return arrayList.get(index);
    }

    @Override
    public T remove(int index) {
        if (changeLL)
            synchronize();
        changeAL = true;
        return arrayList.remove(index);
    }

    @Override
    public void clear() {
        linkedList = new LinkedList<>();
        arrayList = new ArrayList<>();
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return linkedList.listIterator(index);
    }

    private void synchronize() {
        if (changeLL) {
            arrayList = new ArrayList<>(linkedList);
            changeLL = false;
        } else if (changeAL) {
            linkedList = new LinkedList<>(arrayList);
            changeAL = false;
        }
    }
}

public class E33_ListPerformance3 {
    static Random rand = new Random();
    static int reps = 1000;
    static List<Test<List<Integer>>> tests = new ArrayList<>();

    static {
        tests.add(new Test<List<Integer>>("add") {
            @Override
            public int test(List<Integer> list, TestParam tp) {
                int loops = tp.loops;
                int listSize = tp.size;
                for (int i = 0; i < tp.loops; i++) {
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
                int count = 0;
                for (int i = list.size() / 2; i < list.size(); i++) {
                    ++count;
                    list.remove(i);
                }
                return count;
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
        ListTester.run(new ArrayList<Integer>(), tests);
        ListTester.run(new LinkedList<Integer>(), tests);
        ListTester.run(new FastTraversalLinkedList<Integer>(), tests);

    }
}
/*
--------------------- ArrayList ---------------------
 size     add     get     set iteradd  insert  remove
   10      40       8       8      48     289    2833
  100      10       7       8      48     285     295
 1000       8       8       8      77     359     317
10000       5       8       8     471     951     283
--------------------- LinkedList ---------------------
 size     add     get     set iteradd  insert  remove
   10      52      18      19       8      96    1944
  100       7      31      32      23      47     230
 1000      10     344     347       6      36     532
10000       8    3943    3949       6      39    4295
-------------- FastTraversalLinkedList --------------
 size     add     get     set iteradd  insert  remove
   10      26       8       8       6      73    2722
  100       6       8       8       6      49     460
 1000       7       7       8       4      38     249
10000       5       8       8       4      39     343
 */