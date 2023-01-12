package exercises.chapter10;

/**
 * Exercise 22
 * Implement reverseSelector() in Sequence.java
 */
interface Selector3 {
    boolean end();
    Object current();
    void next();
}

class Sequence3 {
    private Object[] items;
    private int next = 0;

    public Sequence3(int size) {
        items = new Object[size];
    }

    public void add(Object x) {
        if (next < items.length)
            items[next++] = x;
    }

    private class SequenceSelector implements Selector3 {
        private int i = 0;

        @Override
        public boolean end() {
            return i != items.length;
        }

        @Override
        public Object current() {
            return items[i];
        }

        @Override
        public void next() {
            if (i < items.length)
                i++;
        }
    }

    private class ReverseSelector implements Selector3 {
        private int i = items.length - 1;

        @Override
        public boolean end() {
            return i >= 0;
        }

        @Override
        public Object current() {
            return items[i];
        }

        @Override
        public void next() {
            if (i >= 0)
                i--;
        }
    }

    public Selector3 selector() {
        return new SequenceSelector();
    }

    public Selector3 reverseSelector() {
        return new ReverseSelector();
    }
}
/* Output:
0 1 2 3 4 5 6 7 8 9
 */
public class E22_SequenceReverseSelector {
    public static void main(String[] args) {
        Sequence3 sequence = new Sequence3(10);
        for (int i = 0; i < 10; i++)
            sequence.add(Integer.toString(i));
        Selector3 selector = sequence.selector();
        System.out.println("SequenceSelector:");
        while (selector.end()) {
            System.out.print(selector.current() + " ");
            selector.next();
        }
        selector = sequence.reverseSelector();
        System.out.println("\nReverseSelector:");
        while (selector.end()) {
            System.out.print(selector.current() + " ");
            selector.next();
        }
    }
}
/* Output:
SequenceSelector:
0 1 2 3 4 5 6 7 8 9
ReverseSelector:
9 8 7 6 5 4 3 2 1 0
 */