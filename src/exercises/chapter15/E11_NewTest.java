package exercises.chapter15;

import net.mindview.util.New;

import java.util.*;

/**
 * Exercise 11
 * Test New.java by creating your own classes and
 * ensuring that New will work properly with them.
 */
class A {
    @Override
    public String toString() {
        return "A";
    }
}

class B extends A {
    @Override
    public String toString() {
        return "B";
    }
}

class C extends B {
    @Override
    public String toString() {
        return "C";
    }
}
public class E11_NewTest {
    public static void main(String[] args) {
        Map<Integer, A> map = New.map();
        map.put(1, new A());
        map.put(2, new B());
        map.put(3, new C());
        System.out.println(map);
        List<A> list = New.list();
        list.add(new A());
        list.add(new B());
        list.add(new C());
        System.out.println(list);
        list = New.lList();
        list.add(new A());
        list.add(new B());
        list.add(new C());
        System.out.println(list);
        Set<A> set = New.set();
        set.add(new A());
        set.add(new B());
        set.add(new C());
        System.out.println(set);
        Queue<A> queue = New.queue();
        queue.add(new A());
        queue.add(new B());
        queue.add(new C());
        System.out.println(queue);
    }
}
/* Output:
{1=A, 2=B, 3=C}
[A, B, C]
[A, B, C]
[C, B, A]
[A, B, C]
 */