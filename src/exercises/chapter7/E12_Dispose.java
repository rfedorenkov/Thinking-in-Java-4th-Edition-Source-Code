package exercises.chapter7;

/**
 * Exercise 12
 * Add a proper hierarchy of dispose() methods
 * to all the classes in Exercise 9.
 */
class ComponentC1 {
    ComponentC1() {
        System.out.println("ComponentC1()");
    }

    void dispose() {
        System.out.println("ComponentC1.dispose()");
    }
}

class ComponentC2 {
    ComponentC2() {
        System.out.println("Component2C()");
    }

    void dispose() {
        System.out.println("ComponentC2.dispose()");
    }
}

class ComponentC3 {
    ComponentC3() {
        System.out.println("Component3()");
    }

    void dispose() {
        System.out.println("ComponentC3.dispose()");
    }
}

class RootC {
    ComponentC1 componentC1 = new ComponentC1();
    ComponentC2 componentC2 = new ComponentC2();
    ComponentC3 componentC3 = new ComponentC3();

    RootC() {
        System.out.println("RootC()");
    }

    void dispose() {
        System.out.println("RootC.dispose()");
        componentC3.dispose();
        componentC2.dispose();
        componentC1.dispose();
    }
}

class StemC extends RootC {
    ComponentC1 componentC1 = new ComponentC1();
    ComponentC2 componentC2 = new ComponentC2();
    ComponentC3 componentC3 = new ComponentC3();

    StemC() {
        System.out.println("StemC()");
    }

    @Override
    void dispose() {
        System.out.println("StemC.dispose()");
        componentC3.dispose();
        componentC2.dispose();
        componentC1.dispose();
        super.dispose();
    }
}

public class E12_Dispose {
    public static void main(String[] args) {
        StemC stem = new StemC();
        try {
            // Code and exception handling...
        } finally {
            stem.dispose();
        }
    }
}
/* Output:
ComponentC1()
Component2C()
Component3()
RootC()
ComponentC1()
Component2C()
Component3()
StemC()
StemC.dispose()
ComponentC3.dispose()
ComponentC2.dispose()
ComponentC1.dispose()
RootC.dispose()
ComponentC3.dispose()
ComponentC2.dispose()
ComponentC1.dispose()
 */