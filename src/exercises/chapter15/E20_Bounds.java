package exercises.chapter15;

/**
 * Exercise 20
 * Create an interface with two methods, and a class that
 * implements that interface and add another method.
 * In another class, create a generic method with an argument
 * type that is bounded by the interface, and show that the methods
 * in the interface are callable inside this generic method.
 * In main(), pass an instance of the implementing class to
 * the generic method.
 */
interface Switcher {
    void on();
    void off();
}

class Controller implements Switcher {
    @Override
    public void on() {
        System.out.println("Switcher.on()");
    }

    @Override
    public void off() {
        System.out.println("Switcher.off()");
    }
    
    public void controller() {
        System.out.println("Controller.controller()");
    }
}

public class E20_Bounds {
    public static <T extends Switcher> void test(T obj) {
        obj.on();
        obj.off();
        //! x.controller(); // Compile error
    }

    public static void main(String[] args) {
        test(new Controller());
    }
}
/* Output:
Switcher.on()
Switcher.off()
 */