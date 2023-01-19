package exercises.chapter11;

import java.util.ArrayList;
import java.util.List;

/**
 * Exercise 3
 * Modify innerclasses/Sequence.java so you can
 * add any number of elements to it.
 */
interface Selector2 {
    boolean end();
    Object current();
    void next();
}

class UnlimitedSequence {
    private List<Object> items = new ArrayList<>();

    public void add(Object x) {
        items.add(x);
    }

    private class SequenceSelector implements Selector2 {
        private int i = 0;

        @Override
        public boolean end() {
            return i == items.size();
        }

        @Override
        public Object current() {
            return items.get(i);
        }

        @Override
        public void next() {
            if (i < items.size())
                i++;
        }
    }

    public Selector2 selector() {
        return new SequenceSelector();
    }
}

public class E03_UnlimitedSequenceTest {
    public static void main(String[] args) {
        UnlimitedSequence sequence = new UnlimitedSequence();
        for (int i = 0; i < 10; i++)
            sequence.add(Integer.toString(i));
        Selector2 selector = sequence.selector();
        while (!selector.end()) {
            System.out.print(selector.current() + " ");
            selector.next();
        }
    }
}
/* Output:
0 1 2 3 4 5 6 7 8 9
 */