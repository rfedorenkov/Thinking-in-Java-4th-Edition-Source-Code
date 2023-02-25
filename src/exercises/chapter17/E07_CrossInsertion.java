package exercises.chapter17;

import net.mindview.util.Countries;

import java.util.*;

import static net.mindview.util.Print.print;

/**
 * Exercise 7
 * Create both an ArrayList and a LinkedList, and fill each
 * using the Countries.names() generator. Print each list
 * using an ordinary Iterator, then insert one list into
 * the other by using a ListIterator, inserting at every
 * other location. Now perform the insertion starting at
 * the end of the first list and moving backward.
 */
public class E07_CrossInsertion {
    public static void printList(List<String> list) {
        print("Iteration " + list.getClass().getSimpleName());
        Iterator<String> it = list.iterator();
        while (it.hasNext())
            print(it.next());
    }

    public static void main(String[] args) {
        List<String> list1 = new ArrayList<>(Countries.names(6));
        List<String> list2 = new LinkedList<>(Countries.names(6));
        printList(list1);
        print("*************************");
        printList(list2);

        print("*************************");
        ListIterator<String> it = list1.listIterator();
        int index = 0;
        while (it.hasNext()) {
            list2.add(index, it.next());
            index += 2;
        }
        print(list2);
        it = list1.listIterator(list1.size());
        index = 0;
        while (it.hasPrevious()) {
            list2.add(index, it.previous());
            index += 2;
        }
        print(list2);
    }
}
/* Output:
Iteration ArrayList
ALGERIA
ANGOLA
BENIN
BOTSWANA
BURKINA FASO
BURUNDI
*************************
Iteration LinkedList
ALGERIA
ANGOLA
BENIN
BOTSWANA
BURKINA FASO
BURUNDI
*************************
[ALGERIA, ALGERIA, ANGOLA, ANGOLA, BENIN, BENIN, BOTSWANA, BOTSWANA, BURKINA FASO, BURKINA FASO, BURUNDI, BURUNDI]
[BURUNDI, ALGERIA, BURKINA FASO, ALGERIA, BOTSWANA, ANGOLA, BENIN, ANGOLA, ANGOLA, BENIN, ALGERIA, BENIN, BOTSWANA, BOTSWANA, BURKINA FASO, BURKINA FASO, BURUNDI, BURUNDI]
 */