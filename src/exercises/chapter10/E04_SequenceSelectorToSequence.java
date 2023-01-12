package exercises.chapter10;

/**
 * Exercise 4
 * Add a method to the class Sequence.SequenceSelector
 * that produces the reference to the outer class Sequence.
 */
interface Selector2 {
    boolean end();
    Object current();
    void next();
}

class Sequence2 {
    private Object[] items;
    private int next = 0;

    public Sequence2(int size) {
        items = new Object[size];
    }

    public void add(Object x) {
        if (next < items.length)
            items[next++] = x;
    }

    private class SequenceSelector2 implements Selector2 {
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

        public Sequence2 getSequence() {
            return Sequence2.this;
        }
    }

    public Selector2 selector() {
        return new SequenceSelector2();
    }

    public void test() {
        SequenceSelector2 selector = (SequenceSelector2) selector();
        System.out.println("(Sequence2.this == this) = " + (selector.getSequence() == this));
    }
}

public class E04_SequenceSelectorToSequence {
    public static void main(String[] args) {
        Sequence2 sequence = new Sequence2(10);
        //! SequenceSelector2 selector = sequence.selector(); // SequenceSelector is private class
        sequence.test();
    }
}
/* Output:
Sequence2.this
 */