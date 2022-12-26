package exercises.chapter9;

import static net.mindview.util.Print.print;

/**
 * Exercise 18
 * Create a Cycle interface, with implementations
 * Unicycle, Bicycle, and Tricycle. Create factories
 * for each type of Cycle, and code that uses these factories.
 */
interface Cycle {
    void ride();
}

class Unicycle implements Cycle {
    Unicycle() {} // Package access

    @Override
    public void ride() {
        print("Ride a unicycle");
    }
}

class Bicycle implements Cycle {
    Bicycle() {} // Package access

    @Override
    public void ride() {
        print("Ride a bicycle");
    }
}

class Tricycle implements Cycle {
    Tricycle() {} // Package access

    @Override
    public void ride() {
        print("Ride a tricycle");
    }
}

interface CycleFactory {
    Cycle getCycle();
}

class UnicycleFactory implements CycleFactory {
    @Override
    public Cycle getCycle() {
        return new Tricycle();
    }
}

class BicycleFactory implements CycleFactory {

    @Override
    public Cycle getCycle() {
        return new Bicycle();
    }
}

class TricycleFactory implements CycleFactory {

    @Override
    public Cycle getCycle() {
        return new Tricycle();
    }
}

public class E18_CycleFactories {
    public static void rideCycle(CycleFactory factory) {
        factory.getCycle().ride();
    }

    public static void main(String[] args) {
        rideCycle(new UnicycleFactory());
        rideCycle(new BicycleFactory());
        rideCycle(new TricycleFactory());
    }
}
/* Output:
Ride a tricycle
Ride a bicycle
Ride a tricycle
 */