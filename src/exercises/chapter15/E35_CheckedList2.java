package exercises.chapter15;

import generics.coffee.Cappuccino;
import generics.coffee.Coffee;
import generics.coffee.Latte;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Exercise 35
 * Modify CheckedList.java so that it uses the Coffee
 * classes defined in this chapter.
 */
public class E35_CheckedList2 {
    @SuppressWarnings("unchecked")
    static void oldStyleMethod(List probablyLatte) {
        probablyLatte.add(new Cappuccino());
    }

    public static void main(String[] args) {
        List<Latte> lattes = new ArrayList<>();
        oldStyleMethod(lattes); // Quietly accepts a Cappuccino
        List<Latte> lattes2 = Collections.checkedList(new ArrayList<>(), Latte.class);
        try {
            oldStyleMethod(lattes2); // Throws an exception
        } catch (Exception e) {
            System.out.println(e);
        }
        // Derived types work fine:
        List<Coffee> coffees = Collections.checkedList(new ArrayList<>(), Coffee.class);
        coffees.add(new Latte());
        coffees.add(new Cappuccino());
    }
}
/* Output:
java.lang.ClassCastException: Attempt to insert class generics.coffee.Cappuccino element into collection with element type class generics.coffee.Latte
 */