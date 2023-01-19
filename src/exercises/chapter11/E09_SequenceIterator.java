package exercises.chapter11;

import java.util.Iterator;

/**
 * Exercise 9
 * Modify innerclasses/Sequence.java so that
 * Sequence works with an Iterator instead of a Selector.
 */
class Sequence {
    private Object[] items;
    private int next = 0;

    public Sequence(int size) {
        items = new Object[size];
    }

    public void add(Object x) {
        if (next < items.length)
            items[next++] = x;
    }

    private class SequenceIterator implements Iterator<Object> {
        private int i = 0;

        @Override
        public boolean hasNext() {
            return i < items.length;
        }

        @Override
        public Object next() {
            return items[i++];
        }
    }

    public Iterator<Object> iterator() {
        return new SequenceIterator();
    }
}

public class E09_SequenceIterator {
    public static void main(String[] args) {
        Sequence sequence = new Sequence(10);
        for (int i = 0; i < 10; i++)
            sequence.add(Integer.toString(i));
        Iterator<Object> it = sequence.iterator();
        while (it.hasNext()) {
            System.out.print(it.next() + " ");
        }
    }
}
/* Output:
0 1 2 3 4 5 6 7 8 9
 */