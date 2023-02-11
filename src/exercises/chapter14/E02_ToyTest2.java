package exercises.chapter14;

import static net.mindview.util.Print.print;

/**
 * Exercise 2
 * Incorporate a new kind of interface into ToyTest.java
 * and verify that it is detected and displayed properly.
 */
interface HasCPU {
}

class Robot extends FancyToy implements HasCPU {
}

public class E02_ToyTest2 {
    static void printInfo(Class cc) {
        print("Class name: " + cc.getName() +
                " is interface? [" + cc.isInterface() + "]");
        print("Simple name: " + cc.getSimpleName());
        print("Canonical name: " + cc.getCanonicalName());
    }

    public static void main(String[] args) {
        Class c = null;
        try {
            c = Class.forName("exercises.chapter14.Robot");
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
Class name: exercises.chapter14.Robot is interface? [false]
Simple name: Robot
Canonical name: exercises.chapter14.Robot
Class name: exercises.chapter14.HasCPU is interface? [true]
Simple name: HasCPU
Canonical name: exercises.chapter14.HasCPU
Class name: exercises.chapter14.FancyToy is interface? [false]
Simple name: FancyToy
Canonical name: exercises.chapter14.FancyToy
 */