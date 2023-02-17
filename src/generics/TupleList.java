package generics;

import net.mindview.util.FourTuple;

import java.util.ArrayList;

/**
 * Combining generic types to make complex generic types.
 */
public class TupleList<A, B, C, D> extends ArrayList<FourTuple<A, B, C, D>> {
    public static void main(String[] args) {
        TupleList<Vehicle, Amphibian, String, Integer> tl = new TupleList<>();
        tl.add(TupleTest.h());
        tl.add(TupleTest.h());
        for (FourTuple<Vehicle, Amphibian, String, Integer> i : tl)
            System.out.println(i);
    }
}
/* Output:
(generics.Vehicle@6bf256fa, generics.Amphibian@6cd8737, hi, 47)
(generics.Vehicle@22f71333, generics.Amphibian@13969fbe, hi, 47)
 */