package exercises.chapter7;

/**
 * Exercise 9
 * Create a class called Root and an instance of each
 * of three classes, Component1, Component2, and Component3.
 * Derive a class Stem from Root that also contains an instance
 * of each "component". Default constructors for each class
 * should print a message about that class.
 */
class Component1 {
    Component1() {
        System.out.println("Component1()");
    }
}

class Component2 {
    Component2() {
        System.out.println("Component2()");
    }
}

class Component3 {
    Component3() {
        System.out.println("Component3()");
    }
}

class Root {
    ComponentC1 componentB1 = new ComponentC1();
    ComponentC2 componentB2 = new ComponentC2();
    ComponentC3 componentB3 = new ComponentC3();

    Root() {
        System.out.println("Root()");
    }
}

class Stem extends Root {
    ComponentC1 componentB1 = new ComponentC1();
    ComponentC2 componentB2 = new ComponentC2();
    ComponentC3 componentB3 = new ComponentC3();

    Stem() {
        System.out.println("Stem()");
    }
}

public class E09_ConstructorsTest2 {
    public static void main(String[] args) {
        Stem stem = new Stem();
    }
}
/* Output:
Component1()
Component2()
Component3()
Root()
Component1()
Component2()
Component3()
Stem()
 */