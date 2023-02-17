package exercises.chapter15;

/**
 * Exercise 34
 * Create a self-bounded generic type that contains an abstract
 * method that takes an argument of the generic type parameter
 * and produces a return value of the generic type parameter.
 * In a non-abstract method of the class, call the abstract
 * method and return its result. Inherit from the self-bounded
 * type and test the resulting class.
 */
abstract class Base<T extends Base<T>> {
    abstract T addAndGet(T t);

    T get(T t) {
        System.out.println("T get(T t)");
        return addAndGet(t);
    }
}

class Derived extends Base<Derived> {
    @Override
    Derived addAndGet(Derived arg) {
        System.out.println("Derived addAndGet(Derived arg)");
        return arg;
    }
}

public class E34_SelfBounded {
    public static void main(String[] args) {
        Derived d1 = new Derived();
        Derived d2 = d1.get(d1);
        System.out.println("(d1 == d2): " + (d1 == d2));
        System.out.println("d2.getClass.getSimpleName(): " + d2.getClass().getSimpleName());
    }
}
/* Output:
T get(T t)
Derived addAndGet(Derived arg)
(d1 == d2): true
d2.getClass.getSimpleName(): Derived
 */