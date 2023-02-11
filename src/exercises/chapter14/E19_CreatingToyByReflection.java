package exercises.chapter14;

import java.lang.reflect.InvocationTargetException;

import static net.mindview.util.Print.print;

/**
 * Exercise 19
 * In ToyTest.java, use reflection to create
 * a Toy object using the non default constructor.
 */
interface HasBatteries2 {
}

interface Waterproof2 {
}

interface Shoots2 {
}

class Toy2 {
    private int i;

    Toy2(int i) {
        this.i = i;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + ", i = " + i;
    }
}

class FancyToy2 extends Toy2 implements HasBatteries2, Waterproof2, Shoots2 {
    FancyToy2(int i) {
        super(i);
    }
}

public class E19_CreatingToyByReflection {
    public static void main(String[] args) {
        Class<?> clazz = null;
        try {
            clazz = Class.forName("exercises.chapter14.Toy2");
        } catch (ClassNotFoundException e) {
            print("Can't find FancyToy");
            System.exit(1);
        }

        try {
            Toy2 toy = (Toy2) clazz.getDeclaredConstructor(int.class).newInstance(47);
            System.out.println(toy);
        } catch (InvocationTargetException e) {
            System.out.println("Target invocation problem: " + e);
        } catch (InstantiationException e) {
            System.out.println("Toy cannot be created: " + e);
        } catch (IllegalAccessException e) {
            System.out.println("Unable access: " + e);
        } catch (NoSuchMethodException e) {
            System.out.println("No such method: " + e);
        }
    }
}
/* Output:
Toy2, i = 47
 */