package exercises.chapter14;

import exercises.chapter14.e25package.A;

import java.lang.reflect.Method;

/**
 * Exercise 25
 * Create a class containing private, protected and package
 * access methods. Write code to access these methods from
 * outside of the class's package.
 */
public class E25_ReflectionHiddenMethodCalls {
    static void callHiddenMethod(Object o, String methodName) throws Exception {
        Method m = o.getClass().getDeclaredMethod(methodName);
        m.setAccessible(true);
        m.invoke(o);
    }

    public static void main(String[] args) throws Exception {
        A a = new A();
        // a.f(); // Compile time error
        // a.g(); // Compile time error
        // a.h(); // Compile time error
        callHiddenMethod(a, "f");
        callHiddenMethod(a, "g");
        callHiddenMethod(a, "h");
    }
}
/* Output:
A.f()
A.g()
A.h()
 */