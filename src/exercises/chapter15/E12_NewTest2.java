package exercises.chapter15;

import net.mindview.util.New;

import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * Exercise 12
 * Repeat the previous exercise using explicit type specification.
 */
public class E12_NewTest2 {
    public static void main(String[] args) {
        Map<Integer, A> map = New.<Integer, A>map();
        map.put(1, new A());
        map.put(2, new B());
        map.put(3, new C());
        System.out.println(map);
        List<A> list = New.<A>list();
        list.add(new A());
        list.add(new B());
        list.add(new C());
        System.out.println(list);
        list = New.lList();
        list.add(new A());
        list.add(new B());
        list.add(new C());
        System.out.println(list);
        Set<A> set = New.<A>set();
        set.add(new A());
        set.add(new B());
        set.add(new C());
        System.out.println(set);
        Queue<A> queue = New.<A>queue();
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