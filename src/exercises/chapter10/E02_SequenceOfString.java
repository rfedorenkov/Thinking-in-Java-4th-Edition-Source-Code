package exercises.chapter10;

/**
 * Exercise 2
 * Create a class that holds a String, with a toString()
 * method that displays this String. Add several instances
 * of your new class to a Sequence object, then display them.
 */
class Holder {
    private String s;

    public Holder(String s) {
        this.s = s;
    }

    @Override
    public String toString() {
        return s;
    }
}

interface Selector {
    boolean end();
    Object current();
    void next();
}

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

    private class SequenceSelector implements Selector {
        private int i = 0;

        @Override
        public boolean end() {
            return i == items.length;
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

    public Selector selector() {
        return new SequenceSelector();
    }
}

public class E02_SequenceOfString {
    public static void main(String[] args) {
        Sequence sequence = new Sequence(10);
        for (int i = 0; i < 10; i++)
            sequence.add(new Holder(String.valueOf(i)));
        Selector selector = sequence.selector();
        while (!selector.end()) {
            System.out.print(selector.current() + " ");
            selector.next();
        }
    }
}
/* Output:
0 1 2 3 4 5 6 7 8 9
 */