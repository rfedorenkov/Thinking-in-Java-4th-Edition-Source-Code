package exercises.chapter14;

import net.mindview.util.TypeCounter;
import typeinfo.pets.factory.Factory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static net.mindview.util.Print.print;
import static net.mindview.util.Print.printnb;

/**
 * Exercise 13
 * Use TypeCounter with the RegisteredFactories.java
 * example in this chapter.
 */
class Part {
    @Override
    public String toString() {
        return getClass().getSimpleName();
    }

    static List<Factory<? extends Part2>> partFactories = new ArrayList<>();

    static {
        // Collections.addAll() gives an "unchecked generic
        // array creation ... for varargs parameter" warning.
        partFactories.add(new FuelFilter.Factory());
        partFactories.add(new AirFilter.Factory());
        partFactories.add(new CabinAirFilter.Factory());
        partFactories.add(new OilFilter.Factory());
        partFactories.add(new FanBelt.Factory());
        partFactories.add(new PowerSteeringBelt.Factory());
        partFactories.add(new GeneratorBelt.Factory());
    }

    private static Random rand = new Random(47);

    public static Part2 createRandom() {
        int n = rand.nextInt(partFactories.size());
        return partFactories.get(n).create();
    }
}

class Filter extends Part2 {
}

class FuelFilter extends Filter2 {
    // Create a Class Factory for each specific type:
    public static class Factory implements typeinfo.pets.factory.Factory<FuelFilter2> {
        @Override
        public FuelFilter2 create() {
            return new FuelFilter2();
        }
    }
}

class AirFilter extends Filter2 {
    public static class Factory implements typeinfo.pets.factory.Factory<AirFilter2> {
        @Override
        public AirFilter2 create() {
            return new AirFilter2();
        }
    }
}

class CabinAirFilter extends Filter2 {
    public static class Factory implements typeinfo.pets.factory.Factory<CabinAirFilter2> {
        @Override
        public CabinAirFilter2 create() {
            return new CabinAirFilter2();
        }
    }
}

class OilFilter extends Filter2 {
    public static class Factory implements typeinfo.pets.factory.Factory<OilFilter2> {
        @Override
        public OilFilter2 create() {
            return new OilFilter2();
        }
    }
}

class Belt extends Part2 {
}

class FanBelt extends Belt2 {
    public static class Factory implements typeinfo.pets.factory.Factory<FanBelt2> {
        @Override
        public FanBelt2 create() {
            return new FanBelt2();
        }
    }
}

class GeneratorBelt extends Belt2 {
    public static class Factory implements typeinfo.pets.factory.Factory<GeneratorBelt2> {
        @Override
        public GeneratorBelt2 create() {
            return new GeneratorBelt2();
        }
    }
}

class PowerSteeringBelt extends Belt2 {
    public static class Factory implements typeinfo.pets.factory.Factory<PowerSteeringBelt2> {
        @Override
        public PowerSteeringBelt2 create() {
            return new PowerSteeringBelt2();
        }
    }
}

public class E13_PartCount {
    public static void main(String[] args) {
        TypeCounter counter = new TypeCounter(Part2.class);
        for (int i = 0; i < 20; i++) {
            Part2 part = Part2.createRandom();
            printnb(part + " ");
            counter.count(part);
        }
        print();
        print(counter);
    }
}
/* Output:
GeneratorBelt CabinAirFilter GeneratorBelt AirFilter PowerSteeringBelt CabinAirFilter FuelFilter PowerSteeringBelt PowerSteeringBelt FuelFilter CabinAirFilter PowerSteeringBelt FanBelt AirFilter OilFilter OilFilter AirFilter PowerSteeringBelt FuelFilter CabinAirFilter
{GeneratorBelt=2, OilFilter=2, Filter=12, AirFilter=3, Part=20, Belt=8, FuelFilter=3, CabinAirFilter=4, FanBelt=1, PowerSteeringBelt=5}
 */