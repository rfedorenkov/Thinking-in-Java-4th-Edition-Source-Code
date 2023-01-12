package exercises.chapter10;

import static net.mindview.util.Print.print;

/**
 * Exercise 16
 * Use anonymous inner classes to modify the solution
 * to Exercise 18 from the Interfaces chapter.
 */
interface Cycle {
    void ride();
}

interface CycleFactory {
    Cycle getCycle();
}

class Unicycle implements Cycle {
    Unicycle() {} // Package access

    @Override
    public void ride() {
        print("Ride a unicycle");
    }

    public static CycleFactory factory = new CycleFactory() {
        @Override
        public Cycle getCycle() {
            return new Unicycle();
        }
    };
}

class Bicycle implements Cycle {
    Bicycle() {} // Package access

    @Override
    public void ride() {
        print("Ride a bicycle");
    }

    public static CycleFactory factory = new CycleFactory() {
        @Override
        public Cycle getCycle() {
            return new Bicycle();
        }
    };
}

class Tricycle implements Cycle {
    Tricycle() {} // Package access

    @Override
    public void ride() {
        print("Ride a tricycle");
    }

    public static CycleFactory factory = new CycleFactory() {
        @Override
        public Cycle getCycle() {
            return new Tricycle();
        }
    };
}

public class E16_AnonymousCycleFactories {
    public static void rideCycle(CycleFactory factory) {
        Cycle cycle = factory.getCycle();
        cycle.ride();
    }

    public static void main(String[] args) {
        rideCycle(Unicycle.factory);
        rideCycle(Bicycle.factory);
        rideCycle(Tricycle.factory);
    }
}
/* Output:
Ride a unicycle
Ride a bicycle
Ride a tricycle
 */