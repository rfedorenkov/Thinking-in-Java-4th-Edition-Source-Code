package exercises.chapter15;

import generics.Fibonacci;
import generics.IterableFibonacci;

import java.util.Iterator;

/**
 * Exercise 7
 * Use composition instead of inheritance to adapt
 * Fibonacci to make it Iterable.
 */
public class E07_IterableFibonacci2 implements Iterable<Integer> {
    private Fibonacci fib = new Fibonacci();
    private int n;

    public E07_IterableFibonacci2(int count) {
        n = count;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<>() {
            @Override
            public boolean hasNext() {
                return n > 0;
            }

            @Override
            public Integer next() {
                n--;
                return fib.next();
            }

            @Override
            public void remove() { // Not implemented
                throw new UnsupportedOperationException();
            }
        };
    }

    public static void main(String[] args) {
        for (int i : new IterableFibonacci(18))
            System.out.print(i + " ");
    }
}
/* Output:
1 1 2 3 5 8 13 21 34 55 89 144 233 377 610 987 1597 2584
 */