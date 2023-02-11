package exercises.chapter14;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Exercise 14
 * A constructor is a kind of factory method.
 * Modify RegisteredFactories.java so that instead of using
 * an explicit factory, the class object is stored in the List,
 * and newInstance() is used to create each object.
 */
class Part2 {
    @Override
    public String toString() {
        return getClass().getSimpleName();
    }

    static List<Class<? extends Part2>> partFactories = new ArrayList<>();

    static {
        // Collections.addAll() gives an "unchecked generic
        // array creation ... for varargs parameter" warning.
        partFactories.add(FuelFilter2.class);
        partFactories.add(AirFilter2.class);
        partFactories.add(CabinAirFilter2.class);
        partFactories.add(OilFilter2.class);
        partFactories.add(FanBelt2.class);
        partFactories.add(PowerSteeringBelt2.class);
        partFactories.add(GeneratorBelt2.class);
    }

    private static Random rand = new Random(47);

    public static Part2 createRandom() {
        int n = rand.nextInt(partFactories.size());
        try {
            return partFactories.get(n).newInstance();
        } catch (IllegalAccessException | InstantiationException e) {
            throw new RuntimeException(e);
        }
    }
}

class Filter2 extends Part2 {
}

class FuelFilter2 extends Filter2 {
}

class AirFilter2 extends Filter2 {
}

class CabinAirFilter2 extends Filter2 {
}

class OilFilter2 extends Filter2 {
}

class Belt2 extends Part2 {
}

class FanBelt2 extends Belt2 {
}

class GeneratorBelt2 extends Belt2 {
}

class PowerSteeringBelt2 extends Belt2 {
}

public class E14_RegisteredFactories2 {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++)
            System.out.println(Part2.createRandom());
    }
}
/* Output:
GeneratorBelt2
CabinAirFilter2
GeneratorBelt2
AirFilter2
PowerSteeringBelt2
CabinAirFilter2
FuelFilter2
PowerSteeringBelt2
PowerSteeringBelt2
FuelFilter2
 */