package exercises.chapter15;

import generics.Fibonacci;
import generics.coffee.Coffee;
import generics.coffee.CoffeeGenerator;
import net.mindview.util.Generator;

import java.util.*;

/**
 * Exercise 13
 * Overload the fill() method so the arguments and return types
 * are the specific subtypes of Collection: List, Queue and Set.
 * This way, you don't lose the type of container. Can you
 * overload to distinguish between List and LinkedList?
 */
public class E13_OverloadedFill {
    public static <T> Collection<T> fill(Collection<T> coll, Generator<T> gen, int n) {
        for (int i = 0; i < n; i++)
            coll.add(gen.next());
        return coll;
    }

    public static <T> List<T> fill(List<T> coll, Generator<T> gen, int n) {
        for (int i = 0; i < n; i++)
            coll.add(gen.next());
        return coll;
    }

    public static <T> Queue<T> fill(Queue<T> coll, Generator<T> gen, int n) {
        for (int i = 0; i < n; i++)
            coll.add(gen.next());
        return coll;
    }

    public static <T> Set<T> fill(Set<T> coll, Generator<T> gen, int n) {
        for (int i = 0; i < n; i++)
            coll.add(gen.next());
        return coll;
    }

    public static void main(String[] args) {
        List<Coffee> coffees = fill(new ArrayList<>(), new CoffeeGenerator(), 4);
        for (Coffee c : coffees)
            System.out.println(c);
        Queue<Integer> fnumbers = fill((Queue<Integer>) new LinkedList<Integer>(), new Fibonacci(), 12);
        for (int i : fnumbers)
            System.out.print(i + ", ");
        System.out.println();
        Set<Coffee> setCoffees = fill(new HashSet<>(), new CoffeeGenerator(), 4);
        for (Coffee c : setCoffees)
            System.out.println(c);
    }
}
/* Output:
Americano 0
Latte 1
Americano 2
Mocha 3
1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144,
Mocha 4
Latte 7
Breve 5
Americano 6
 */