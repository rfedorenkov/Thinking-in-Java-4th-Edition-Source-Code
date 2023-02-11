package exercises.chapter14;

import static net.mindview.util.Print.print;

/**
 * Exercise 1
 * In ToyTest.java, comment out Toy's default constructor
 * and explain what happens.
 */
interface HasBatteries {
}

interface Waterproof {
}

interface Shoots {
}

class Toy {
    // Comment out the following default constructor
    // to see NoSuchMethodError from (*1*)
//    Toy() {
//    }

    Toy(int i) {
    }
}

class FancyToy extends Toy implements HasBatteries, Waterproof, Shoots {
    FancyToy() {
        super(1);
    }
}

public class E01_ToyTest {
    static void printInfo(Class cc) {
        print("Class name: " + cc.getName() +
                " is interface? [" + cc.isInterface() + "]");
        print("Simple name: " + cc.getSimpleName());
        print("Canonical name: " + cc.getCanonicalName());
    }

    public static void main(String[] args) {
        Class c = null;
        try {
            c = Class.forName("exercises.chapter14.FancyToy");
        } catch (ClassNotFoundException e) {
            print("Can't find FancyToy");
            System.exit(1);
        }
        printInfo(c);
        for (Class face : c.getInterfaces())
            printInfo(face);
        Class up = c.getSuperclass();
        Object obj = null;
        try {
            // Requires default constructor:
            obj = up.newInstance();
        } catch (InstantiationException e) {
            // If the class or its empty constructor is not available.
            print("Cannot instantiate");
            System.exit(1);
        } catch (IllegalAccessException e) {
            print("Cannot access");
            System.exit(1);
        }
        printInfo(obj.getClass());
    }
}
/* Output:
Class name: exercises.chapter14.FancyToy is interface? [false]
Simple name: FancyToy
Canonical name: exercises.chapter14.FancyToy
Class name: exercises.chapter14.HasBatteries is interface? [true]
Simple name: HasBatteries
Canonical name: exercises.chapter14.HasBatteries
Class name: exercises.chapter14.Waterproof is interface? [true]
Simple name: Waterproof
Canonical name: exercises.chapter14.Waterproof
Class name: exercises.chapter14.Shoots is interface? [true]
Simple name: Shoots
Canonical name: exercises.chapter14.Shoots
Cannot instantiate
 */