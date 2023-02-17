package exercises.chapter15;

/**
 * Exercise 23
 * Modify FactoryConstraint.java so that create() takes an argument.
 */
interface FactoryI<T> {
    T create(int arg);
}

class Foo2<T> {
    private T x;

    public <F extends FactoryI<T>> Foo2(F factory, int arg) {
        x = factory.create(arg);
    }
    // ...
}

class IntegerFactory implements FactoryI<Integer> {
    @Override
    public Integer create(int arg) {
        return new Integer(arg);
    }
}

class Widget {
    private final int i;

    public Widget(int i) {
        this.i = i;
    }

    public static class Factory implements FactoryI<Widget> {
        @Override
        public Widget create(int arg) {
            return new Widget(arg);
        }
    }
}

public class E23_FactoryConstraint2 {
    public static void main(String[] args) {
        new Foo2<Integer>(new IntegerFactory(), 10);
        new Foo2<Widget>(new Widget.Factory(), 10);
    }
}