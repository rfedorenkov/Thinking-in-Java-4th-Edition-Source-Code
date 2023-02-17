package exercises.chapter15;

import net.mindview.util.FiveTuple;
import typeinfo.pets.Cat;
import typeinfo.pets.Dog;
import typeinfo.pets.Mouse;

/**
 * Exercise 3
 * Create and test a SixTuple generic.
 */
class SixTuple<A, B, C, D, E, F> extends FiveTuple<A, B, C, D, E> {
    public F sixth;

    public SixTuple(A a, B b, C c, D d, E e, F f) {
        super(a, b, c, d, e);
        sixth = f;
    }

    @Override
    public String toString() {
        return "(" + first + ", " + second + ", " +
                third + ", " + fourth + ", " + fifth + ", " + sixth + ")";
    }
}

public class E03_SixTupleTest {
    static SixTuple<Mouse, Dog, Cat, String, Integer, Double> a() {
        return new SixTuple<>(new Mouse(), new Dog(), new Cat(), "hi", 47, 11.1);
    }
    public static void main(String[] args) {
        System.out.println(a());
    }
}
/* Output:
(Mouse, Dog, Cat, hi, 47, 11.1)
 */