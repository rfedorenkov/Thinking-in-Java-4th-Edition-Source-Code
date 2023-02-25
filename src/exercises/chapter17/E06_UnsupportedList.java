package exercises.chapter17;

import java.util.*;

/**
 * Exercise 6
 * Note that List has additional "optional" operations that
 * are not included in Collection. Write a version of
 * Unsupported.java that test these additional optional operations.
 */
class Unsupported {
    static void test(String msg, List<String> list) {
        System.out.println("--- " + msg + " ---");
        Collection<String> c = list;
        List<String> subList = list.subList(1, 8);
        // Copy of the sublist:
        Collection<String> c2 = new ArrayList<>(subList);
        try {
            c.retainAll(c2);
        } catch (Exception e) {
            System.out.println("retainAll(): " + e);
        }
        try {
            c.removeAll(c2);
        } catch (Exception e) {
            System.out.println("removeAll(): " + e);
        }
        try {
            c.clear();
        } catch (Exception e) {
            System.out.println("clear(): " + e);
        }
        try {
            c.add("X");
        } catch (Exception e) {
            System.out.println("add(): " + e);
        }
        try {
            c.addAll(c2);
        } catch (Exception e) {
            System.out.println("addAll(): " + e);
        }
        try {
            c.remove("C");
        } catch (Exception e) {
            System.out.println("remove(): " + e);
        }
        // The List.set() method modifies the value but
        // doesn't change the size of the data structure:
        try {
            list.set(0, "X");
        } catch (Exception e) {
            System.out.println("List.set(): " + e);
        }
        // Additional optional operations
        try {
            list.add(0, "X");
        } catch (Exception e) {
            System.out.println("List.add(index, element): " + e);
        }
        try {
            list.remove(0);
        } catch (Exception e) {
            System.out.println("List.remove(index): " + e);
        }
        try {
            list.addAll(0, c);
        } catch (Exception e) {
            System.out.println("List.addAll(index, collection): " + e);
        }
    }
}

public class E06_UnsupportedList {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("A B C D E F G H I J K L".split(" "));
        Unsupported.test("Modifiable Copy", new ArrayList<>(list));
        Unsupported.test("Arrays.asList()", list);
        Unsupported.test("unmodifiableList()", Collections.unmodifiableList(new ArrayList<>(list)));
    }
}
/* Output:
--- Modifiable Copy ---
--- Arrays.asList() ---
retainAll(): java.lang.UnsupportedOperationException: remove
removeAll(): java.lang.UnsupportedOperationException: remove
clear(): java.lang.UnsupportedOperationException
add(): java.lang.UnsupportedOperationException
addAll(): java.lang.UnsupportedOperationException
remove(): java.lang.UnsupportedOperationException: remove
List.add(index, element): java.lang.UnsupportedOperationException
List.remove(index): java.lang.UnsupportedOperationException
List.addAll(index, collection): java.lang.UnsupportedOperationException
--- unmodifiableList() ---
retainAll(): java.lang.UnsupportedOperationException
removeAll(): java.lang.UnsupportedOperationException
clear(): java.lang.UnsupportedOperationException
add(): java.lang.UnsupportedOperationException
addAll(): java.lang.UnsupportedOperationException
remove(): java.lang.UnsupportedOperationException
List.set(): java.lang.UnsupportedOperationException
List.add(index, element): java.lang.UnsupportedOperationException
List.remove(index): java.lang.UnsupportedOperationException
List.addAll(index, collection): java.lang.UnsupportedOperationException
 */