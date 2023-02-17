package exercises.chapter15;

import net.mindview.util.*;

import static net.mindview.util.Tuple.tuple;

/**
 * Exercise 16
 * Add a SixTuple to Tuple.java, and test it in TupleTest2.java
 */
class Amphibian {
    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}

class Vehicle {
    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}

class Tuple2 extends Tuple {
    public static <A, B, C, D, E, F> SixTuple<A, B, C, D, E, F> tuple(A a, B b, C c, D d, E e, F f) {
        return new SixTuple<>(a, b, c, d, e, f);
    }
}

public class E16_TupleTest3 {
    static TwoTuple<String, Integer> f() {
        return tuple("hi", 47);
    }

    static TwoTuple f2() {
        return tuple("hi", 47);
    }

    static ThreeTuple<Amphibian, String, Integer> g() {
        return tuple(new Amphibian(), "hi", 47);
    }

    static FourTuple<Vehicle, Amphibian, String, Integer> h() {
        return tuple(new Vehicle(), new Amphibian(), "hi", 47);
    }

    static FiveTuple<Vehicle, Amphibian, String, Integer, Double> k() {
        return tuple(new Vehicle(), new Amphibian(), "hi", 47, 11.1);
    }

    static SixTuple<Vehicle, Amphibian, String, Integer, Double, Character> m() {
        return Tuple2.tuple(new Vehicle(), new Amphibian(), "hi", 47, 11.1, 'x');
    }

    public static void main(String[] args) {
        TwoTuple<String, Integer> ttsi = f();
        System.out.println(ttsi);
        System.out.println(f2());
        System.out.println(g());
        System.out.println(h());
        System.out.println(k());
        System.out.println(m());
    }
}
/* Output:
(hi, 47)
(hi, 47)
(Amphibian, hi, 47)
(Vehicle, Amphibian, hi, 47)
(Vehicle, Amphibian, hi, 47, 11.1)
(Vehicle, Amphibian, hi, 47, 11.1, x)
 */