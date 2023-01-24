package exercises.chapter12;

/**
 * Exercise 25
 * Create a three-level hierarchy of exceptions.
 * Now create a base class A with a method that
 * throws an exception at the base of your hierarchy.
 * Inherit B from A and override the method so it
 * throws an exception at level two of your hierarchy.
 * Repeat by inheriting class C from B. In main(),
 * create a C and upcast it to A, then call the method.
 */
class LevelOneException extends Exception {
}
class LevelTwoException extends LevelOneException {
}
class LevelThreeException extends LevelTwoException {
}

class A {
    public void f() throws LevelOneException {
        throw new LevelOneException();
    }
}

class B extends A {
    @Override
    public void f() throws LevelTwoException {
        throw new LevelTwoException();
    }
}

class C extends B {
    @Override
    public void f() throws LevelThreeException {
        throw new LevelThreeException();
    }
}

public class E25_TreeLevelException {
    public static void main(String[] args) {
        A c = new C();
        try {
            c.f();
        } catch (LevelOneException e) {
            System.out.println("Caught " + e);
        }
    }
}
/* Output:
Caught exercises.chapter12.LevelThreeException
 */