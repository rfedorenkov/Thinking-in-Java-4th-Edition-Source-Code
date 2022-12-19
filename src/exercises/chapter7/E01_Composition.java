package exercises.chapter7;

/**
 * Exercise 1
 * Create a simple class. Inside a second class,
 * define a reference to an object of the first class.
 * Use lazy initialization to instantiate this object.
 */
class SimpleClass {
    @Override
    public String toString() {
        return "SimpleClass";
    }
}

class SecondClass {
    private SimpleClass simple;

    public void check() {
        System.out.println(simple == null ? "Not initialized" : "Initialized");
    }

    public SimpleClass getSimple() {
        return lazy();
    }

    private SimpleClass lazy() {
        if (simple == null) {
            simple = new SimpleClass();
            System.out.println("Constructed SimpleClass()");
        }
        return simple;
    }
}

public class E01_Composition {
    public static void main(String[] args) {
        SecondClass secondClass = new SecondClass();
        secondClass.check();
        System.out.println(secondClass.getSimple());
        secondClass.check();
        System.out.println(secondClass.getSimple());
        secondClass.check();
    }
}
/* Output:
Not initialized
Constructed SimpleClass()
SimpleClass
Initialized
SimpleClass
Initialized
 */