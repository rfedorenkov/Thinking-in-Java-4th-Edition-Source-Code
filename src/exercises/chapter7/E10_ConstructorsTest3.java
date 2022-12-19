package exercises.chapter7;

/**
 * Exercise 10
 * Modify Exercise 9 so each class only has non-default constructors.
 */
class ComponentB1 {
    ComponentB1(int i) {
        System.out.println("Component1(" + i + ")");
    }
}

class ComponentB2 {
    ComponentB2(int i) {
        System.out.println("Component2(" + i + ")");
    }
}

class ComponentB3 {
    ComponentB3(int i) {
        System.out.println("Component3(" + i + ")");
    }
}

class RootB {
    ComponentB1 componentB1 = new ComponentB1(10);
    ComponentB2 componentB2 = new ComponentB2(20);
    ComponentB3 componentB3 = new ComponentB3(30);

    RootB(int i) {
        System.out.println("Root(" + i + ")");
    }
}

class StemB extends RootB {
    ComponentB1 componentB1 = new ComponentB1(50);
    ComponentB2 componentB2 = new ComponentB2(60);
    ComponentB3 componentB3 = new ComponentB3(70);

    StemB(int i) {
        super(i);
        System.out.println("Stem(" + i + ")");
    }
}

public class E10_ConstructorsTest3 {
    public static void main(String[] args) {
        StemB stem = new StemB(47);
    }
}
/* Output:
Component1(10)
Component2(20)
Component3(30)
Root(47)
Component1(50)
Component2(60)
Component3(70)
Stem(47)
 */