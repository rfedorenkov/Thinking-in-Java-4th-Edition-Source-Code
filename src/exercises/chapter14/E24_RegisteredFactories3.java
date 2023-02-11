package exercises.chapter14;

import typeinfo.pets.factory.Factory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Exercise 24
 * Add Null Objects to RegisteredFactories.java.
 */
class NullPartProxyHandler implements InvocationHandler {
    private String nullName;
    private IPart proxied = new NullPart();

    NullPartProxyHandler(Class<? extends IPart> type) {
        nullName = type.getSimpleName() + " NullPart";
    }

    public class NullPart extends Part3 {
        @Override
        public String toString() {
            return nullName;
        }
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return method.invoke(proxied, args);
    }
}

interface IPart {
}
class Part3 implements IPart {
    public static IPart newNullPart(Class<? extends IPart> type) {
        return (IPart) Proxy.newProxyInstance(
                NullPartProxyHandler.NullPart.class.getClassLoader(),
                new Class[] { IPart.class },
                new NullPartProxyHandler(type));
    }
    @Override
    public String toString() {
        return getClass().getSimpleName();
    }

    static List<Factory<? extends Part3>> partFactories = new ArrayList<>();

    static {
        // Collections.addAll() gives an "unchecked generic
        // array creation ... for varargs parameter" warning.
        partFactories.add(new FuelFilter3.Factory());
        partFactories.add(new AirFilter3.Factory());
        partFactories.add(new CabinAirFilter3.Factory());
        partFactories.add(new OilFilter3.Factory());
        partFactories.add(new FanBelt3.Factory());
        partFactories.add(new PowerSteeringBelt3.Factory());
        partFactories.add(new GeneratorBelt3.Factory());
    }

    private static Random rand = new Random(47);

    public static Part3 createRandom() {
        int n = rand.nextInt(partFactories.size());
        return partFactories.get(n).create();
    }
}

class Filter3 extends Part3 {
}

class FuelFilter3 extends Filter3 {
    // Create a Class Factory for each specific type:
    public static class Factory implements typeinfo.pets.factory.Factory<FuelFilter3> {
        @Override
        public FuelFilter3 create() {
            return new FuelFilter3();
        }
    }
}

class AirFilter3 extends Filter3 {
    public static class Factory implements typeinfo.pets.factory.Factory<AirFilter3> {
        @Override
        public AirFilter3 create() {
            return new AirFilter3();
        }
    }
}

class CabinAirFilter3 extends Filter3 {
    public static class Factory implements typeinfo.pets.factory.Factory<CabinAirFilter3> {
        @Override
        public CabinAirFilter3 create() {
            return new CabinAirFilter3();
        }
    }
}

class OilFilter3 extends Filter3 {
    public static class Factory implements typeinfo.pets.factory.Factory<OilFilter3> {
        @Override
        public OilFilter3 create() {
            return new OilFilter3();
        }
    }
}

class Belt3 extends Part3 {
}

class FanBelt3 extends Belt3 {
    public static class Factory implements typeinfo.pets.factory.Factory<FanBelt3> {
        @Override
        public FanBelt3 create() {
            return new FanBelt3();
        }
    }
}

class GeneratorBelt3 extends Belt3 {
    public static class Factory implements typeinfo.pets.factory.Factory<GeneratorBelt3> {
        @Override
        public GeneratorBelt3 create() {
            return new GeneratorBelt3();
        }
    }
}

class PowerSteeringBelt3 extends Belt3 {
    public static class Factory implements typeinfo.pets.factory.Factory<PowerSteeringBelt3> {
        @Override
        public PowerSteeringBelt3 create() {
            return new PowerSteeringBelt3();
        }
    }
}

public class E24_RegisteredFactories3 {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            IPart part = Part3.createRandom();
            // Real part
            System.out.println(part);
            IPart nullPart = Part3.newNullPart(part.getClass());
            // Null Part
            System.out.println(nullPart);
        }
    }
}
/* Output:
GeneratorBelt3
GeneratorBelt3 NullPart
CabinAirFilter3
CabinAirFilter3 NullPart
GeneratorBelt3
GeneratorBelt3 NullPart
AirFilter3
AirFilter3 NullPart
PowerSteeringBelt3
PowerSteeringBelt3 NullPart
CabinAirFilter3
CabinAirFilter3 NullPart
FuelFilter3
FuelFilter3 NullPart
PowerSteeringBelt3
PowerSteeringBelt3 NullPart
PowerSteeringBelt3
PowerSteeringBelt3 NullPart
FuelFilter3
FuelFilter3 NullPart
 */