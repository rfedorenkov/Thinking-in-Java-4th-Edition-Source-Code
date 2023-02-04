package exercises.chapter13;

import java.util.ArrayList;
import java.util.List;

/**
 * Exercise 2
 * Repair InfiniteRecursion.java.
 */
class InfiniteRecursion {
    @Override
    public String toString() {
        return " InfiniteRecursion address: " + super.toString() + "\n";
    }
}

public class E02_RepairInfiniteRecursion {
    public static void main(String[] args) {
        List<InfiniteRecursion> v = new ArrayList<>();
        for (int i = 0; i < 10; i++)
            v.add(new InfiniteRecursion());
        System.out.println(v);
    }
}
/* Output:
[ InfiniteRecursion address: exercises.chapter13.InfiniteRecursion@3f0ee7cb
,  InfiniteRecursion address: exercises.chapter13.InfiniteRecursion@59a6e353
,  InfiniteRecursion address: exercises.chapter13.InfiniteRecursion@7a0ac6e3
,  InfiniteRecursion address: exercises.chapter13.InfiniteRecursion@71be98f5
,  InfiniteRecursion address: exercises.chapter13.InfiniteRecursion@6fadae5d
,  InfiniteRecursion address: exercises.chapter13.InfiniteRecursion@17f6480
,  InfiniteRecursion address: exercises.chapter13.InfiniteRecursion@2d6e8792
,  InfiniteRecursion address: exercises.chapter13.InfiniteRecursion@2812cbfa
,  InfiniteRecursion address: exercises.chapter13.InfiniteRecursion@2acf57e3
,  InfiniteRecursion address: exercises.chapter13.InfiniteRecursion@506e6d5e
]
 */