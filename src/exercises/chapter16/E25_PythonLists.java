package exercises.chapter16;

import java.util.*;

import static net.mindview.util.Print.print;

/**
 * Exercise 25
 * Rewrite PythonLists.py in Java.
 */
class MyList<T> extends ArrayList<T> {
    List<? extends T> list;

    MyList(List<? extends T> list) {
        this.list = list;
    }

    List<T> getReversed() {
        List<T> result = new ArrayList<>();
        for (int i = list.size() - 1; i >= 0; i--)
            result.add(list.get(i));
        return result;
    }
}

public class E25_PythonLists {
    public static void main(String[] args) {
        List<Integer> aList = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        print(aList.getClass().getSimpleName());
        print(aList);
        print(aList.get(4));
        aList.add(6);
        aList.addAll(Arrays.asList(7, 8));
        print(aList);
        List<Integer> aSlice = aList.subList(2, 4);
        print(aSlice);

        MyList<Integer> list2 = new MyList<>(aList);
        print(list2.getClass().getSimpleName());
        print(list2.getReversed());
    }
}
/* Output:
ArrayList
[1, 2, 3, 4, 5]
5
[1, 2, 3, 4, 5, 6, 7, 8]
[3, 4]
MyList
[8, 7, 6, 5, 4, 3, 2, 1]
 */