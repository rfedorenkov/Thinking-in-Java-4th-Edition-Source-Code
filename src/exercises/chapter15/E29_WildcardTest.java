package exercises.chapter15;

import generics.Holder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Exercise 29
 * Create a generic method that takes as an argument a Holder<List<?>>.
 * Determine what methods you can and can't call for the Holder and
 * for the List. Repeat for an argument of List<Holder<?>>
 */
public class E29_WildcardTest {
    static void f1(Holder<List<?>> holder) {
        System.out.println("Inside f1()");
        List<?> list = holder.get();
        // list.add(new Object()); // Error
        // list.add(2); // Error
        Object obj = list.get(0);
        System.out.println("list.get(0).getClass.getSimpleName() = " + obj.getClass().getSimpleName());
        holder.set(new ArrayList<Number>());
    }

    static void f2(List<Holder<?>> list) {
        System.out.println("Inside f2()");
        Holder<?> holder = list.get(0);
        list.add(new Holder<Integer>());
        list.add(new Holder<Double>());
        list.add(holder);
        // list.add(new Object()); // Error
        Object o = holder.get();
        // holder.set(o); // Error
        // holder.set(new Object()); // Error
        // holder.set(2); // Error
        System.out.println("holder.get().getClass.getSimpleName() = " + o.getClass().getSimpleName());
    }

    public static void main(String[] args) {
        List<?> list1 = new ArrayList<Integer>(Arrays.asList(2));
        Holder<List<?>> holder1 = new Holder<>(list1);
        f1(holder1);

        List<Holder<?>> list2 = new ArrayList<>();
        list2.add(new Holder<>(2));
        f2(list2);
    }
}
/* Output:
Inside f1()
list.get(0).getClass.getSimpleName() = Integer
Inside f2()
holder.get().getClass.getSimpleName() = Integer
 */