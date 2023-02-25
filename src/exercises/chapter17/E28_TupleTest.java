package exercises.chapter17;

import static net.mindview.util.Print.print;

/**
 * Exercise 28
 * Modify net/mindview/util/Tuple.java to make it
 * a general-purpose class by adding hashCode(),
 * equals(), and implementing Comparable for each
 * type of Tuple.
 */
class TwoTuple<A, B> implements Comparable<TwoTuple<A, B>> {
    private final A first;
    private final B second;

    public TwoTuple(A a, B b) {
        first = a;
        second = b;
    }

    public A getFirst() {
        return first;
    }

    public B getSecond() {
        return second;
    }

    @Override
    public int hashCode() {
        int result = 17;
        if (first != null)
            result = 37 * result + first.hashCode();
        if (second != null)
            result = 37 * result + second.hashCode();
        return result;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean equals(Object o) {
        if (o instanceof TwoTuple) {
            TwoTuple<A, B> other = (TwoTuple<A, B>) o;
            return (first == null ? other.first == null : first.equals(other.first)) &&
                    (second == null ? other.second == null : second.equals(other.second));
        }
        return false;
    }

    @Override
    @SuppressWarnings("unchecked")
    public int compareTo(TwoTuple<A, B> o) {
        int i = ((Comparable<A>) first).compareTo(o.first);
        if (i != 0)
            return i;
        return ((Comparable<B>) second).compareTo(o.second);
    }

    @Override
    public String toString() {
        return "(" + first + ", " + second + ")";
    }
}

class ThreeTuple<A, B, C> implements Comparable<ThreeTuple<A, B, C>> {
    private final C third;
    private final TwoTuple<A, B> tuple;

    public ThreeTuple(A a, B b, C c) {
        tuple = new TwoTuple<>(a, b);
        third = c;
    }

    public A getFirst() {
        return tuple.getFirst();
    }

    public B getSecond() {
        return tuple.getSecond();
    }

    public C getThird() {
        return third;
    }

    @Override
    public int hashCode() {
        int result = 17;
        if (third != null)
            result = 37 * result + third.hashCode();
        result = 37 * result + tuple.hashCode();
        return result;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean equals(Object o) {
        if (o instanceof ThreeTuple) {
            ThreeTuple<A, B, C> other = (ThreeTuple<A, B, C>) o;
            return (third == null ? other.third == null : third.equals(other.third)) &&
                    tuple.equals(other.tuple);
        }
        return false;
    }

    @Override
    @SuppressWarnings("unchecked")
    public int compareTo(ThreeTuple<A, B, C> o) {
        int i = tuple.compareTo(o.tuple);
        if (i != 0)
            return i;
        return ((Comparable<C>) third).compareTo(o.third);
    }

    @Override
    public String toString() {
        return "(" + tuple.getFirst() + ", " + tuple.getSecond() + ", " + third + ")";
    }
}

class FourTuple<A, B, C, D> implements Comparable<FourTuple<A, B, C, D>> {
    private final D fourth;
    private final ThreeTuple<A, B, C> tuple;

    public FourTuple(A a, B b, C c, D d) {
        tuple = new ThreeTuple<>(a, b, c);
        fourth = d;
    }

    public A getFirst() {
        return tuple.getFirst();
    }

    public B getSecond() {
        return tuple.getSecond();
    }

    public C getThird() {
        return tuple.getThird();
    }

    public D getFourth() {
        return fourth;
    }

    @Override
    public int hashCode() {
        int result = 17;
        if (fourth != null)
            result = 37 * result + fourth.hashCode();
        result = 37 * result + tuple.hashCode();
        return result;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean equals(Object o) {
        if (o instanceof FourTuple) {
            FourTuple<A, B, C, D> other = (FourTuple<A, B, C, D>) o;
            return (fourth == null ? other.fourth == null : fourth.equals(other.fourth)) &&
                    tuple.equals(other.tuple);
        }
        return false;
    }

    @Override
    @SuppressWarnings("unchecked")
    public int compareTo(FourTuple<A, B, C, D> o) {
        int i = tuple.compareTo(o.tuple);
        if (i != 0)
            return i;
        return ((Comparable<D>) fourth).compareTo(o.fourth);
    }

    @Override
    public String toString() {
        return "(" + tuple.getFirst() + ", " + tuple.getSecond() + ", " +
                tuple.getThird() + ", " + fourth + ")";
    }
}

class FiveTuple<A, B, C, D, E> implements Comparable<FiveTuple<A, B, C, D, E>> {
    public E fifth;
    private FourTuple<A, B, C, D> tuple;

    public FiveTuple(A a, B b, C c, D d, E e) {
        tuple = new FourTuple<>(a, b, c, d);
        fifth = e;
    }

    public A getFirst() {
        return tuple.getFirst();
    }

    public B getSecond() {
        return tuple.getSecond();
    }

    public C getThird() {
        return tuple.getThird();
    }

    public D getFourth() {
        return tuple.getFourth();
    }

    public E getFifth() {
        return fifth;
    }

    @Override
    public int hashCode() {
        int result = 17;
        if (fifth != null)
            result = 37 * result + fifth.hashCode();
        result = 37 * result + tuple.hashCode();
        return result;
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean equals(Object o) {
        if (o instanceof FiveTuple) {
            FiveTuple<A, B, C, D, E> other = (FiveTuple<A, B, C, D, E>) o;
            return (fifth == null ? other.fifth == null : fifth.equals(other.fifth)) &&
                    tuple.equals(other.tuple);
        }
        return false;
    }

    @Override
    @SuppressWarnings("unchecked")
    public int compareTo(FiveTuple<A, B, C, D, E> o) {
        int i = tuple.compareTo(o.tuple);
        if (i != 0)
            return i;
        return ((Comparable<E>) fifth).compareTo(o.fifth);
    }

    @Override
    public String toString() {
        return "(" + tuple.getFirst() + ", " + tuple.getSecond() + ", " +
                tuple.getThird() + ", " + tuple.getFourth() + ", " + fifth + ")";
    }
}

class Tuple {
    public static <A, B> TwoTuple<A, B> tuple(A a, B b) {
        return new TwoTuple<>(a, b);
    }

    public static <A, B, C> ThreeTuple<A, B, C> tuple(A a, B b, C c) {
        return new ThreeTuple<>(a, b, c);
    }

    public static <A, B, C, D> FourTuple<A, B, C, D> tuple(A a, B b, C c, D d) {
        return new FourTuple<>(a, b, c, d);
    }

    public static <A, B, C, D, E> FiveTuple<A, B, C, D, E> tuple(A a, B b, C c, D d, E e) {
        return new FiveTuple<>(a, b, c, d, e);
    }
}

public class E28_TupleTest {
    public static void main(String[] args) {
        TwoTuple<String, String> twoTuple = Tuple.tuple("One", "Two");
        ThreeTuple<String, String, String> threeTuple = Tuple.tuple("One", "Two", "Three");
        FourTuple<String, String, String, String> fourTuple = Tuple.tuple("One", "Two", "Three", "Four");
        FiveTuple<String, String, String, String, String> fiveTuple = Tuple.tuple("One", "Two", "Three", "Four", "Five");
        print(twoTuple + ".hashCode(): " + twoTuple.hashCode());
        print(threeTuple + ".hashCode(): " + threeTuple.hashCode());
        print(fourTuple + ".hashCode(): " + fourTuple.hashCode());
        print(fiveTuple + ".hashCode(): " + fiveTuple.hashCode());

        FiveTuple<String, String, String, String, String> a = Tuple.tuple("One", "Two", "Three", "Four", "Five");
        FiveTuple<String, String, String, String, String> b = Tuple.tuple("One", "Two", "Three", "Four", "Five");
        FiveTuple<String, String, String, String, String> c = Tuple.tuple("Five", "Four", "Three", "Two", "One");
        print("a: " + a);
        print("b: " + b);
        print("c: " + c);
        print("a.equals(b): " + a.equals(b));
        print("b.equals(c): " + b.equals(c));
        print("a.compareTo(b): " + a.compareTo(b));
        print("b.compareTo(c): " + b.compareTo(c));
    }
}
/* Output:
(One, Two).hashCode(): 3046707
(One, Two, Three).hashCode(): -1302785198
(One, Two, Three, Four).hashCode(): -1221517991
(One, Two, Three, Four, Five).hashCode(): -1140463460
a: (One, Two, Three, Four, Five)
b: (One, Two, Three, Four, Five)
c: (Five, Four, Three, Two, One)
a.equals(b): true
b.equals(c): false
a.compareTo(b): 0
b.compareTo(c): 9
 */