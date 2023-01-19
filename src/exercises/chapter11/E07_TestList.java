package exercises.chapter11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static net.mindview.util.Print.print;

/**
 * Exercise 7
 * Create a class and make an initialized array
 * of your class objects. Fill a List from your
 * array. Create a subset of your list using subList(),
 * then remove this subset from your List.
 */
class Counter {
    private static int current = 0;
    private int id = current++;

    @Override
    public String toString() {
        return "User id " + id;
    }
}

public class E07_TestList {
    public static void main(String[] args) {
        Counter[] array = new Counter[10];
        for (int i = 0; i < array.length; i++)
            array[i] = new Counter();
        List<Counter> list = new ArrayList<>(Arrays.asList(array));
        print("list: " + list);
        List<Counter> subList = list.subList(0, list.size() / 2);
        print("subList: " + subList);
        // ConcurrentModificationException
        //! list.removeAll(subList);
        //! print("subList: " + subList);
        List<Counter> copy = new ArrayList<>(list);
        copy.removeAll(subList);
        list = copy;
        print("list: " + list);
    }
}
/* Output:
list: [User id 0, User id 1, User id 2, User id 3, User id 4, User id 5, User id 6, User id 7, User id 8, User id 9]
subList: [User id 2, User id 3, User id 4, User id 5]
list: [User id 0, User id 1, User id 6, User id 7, User id 8, User id 9]
 */