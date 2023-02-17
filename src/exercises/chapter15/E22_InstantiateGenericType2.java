package exercises.chapter15;

import java.lang.reflect.Constructor;

import static net.mindview.util.Print.print;

/**
 * Exercise 22
 * Use a type tag along with reflection to create a method
 * that uses the argument version of newInstance() to create
 * an object of a class with a constructor that has arguments.
 */
class ClassAsFactory<T> {
    Class<T> kind;

    public ClassAsFactory(Class<T> kind) {
        this.kind = kind;
    }

    public T create(int arg) {
        // Version 1
        for (Constructor<?> c : kind.getConstructors()) {
            Class<?>[] types = c.getParameterTypes();
            if (types.length == 1)
                if (types[0] == int.class)
                    return kind.cast(arg);
        }
        // Version 2
//        try {
//            Constructor<T> c = kind.getConstructor(int.class);
//            return c.newInstance(arg);
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
        return null;
    }
}

class Employee {
}

public class E22_InstantiateGenericType2 {
    public static void main(String[] args) {
        ClassAsFactory<Employee> fe = new ClassAsFactory<>(Employee.class);
        Employee employee = fe.create(10);
        if (employee == null)
            print("Employee cannot be initialized!");
        ClassAsFactory<Integer> fi = new ClassAsFactory<>(Integer.class);
        Integer integer = fi.create(10);
        if (integer == null) {
            print("Integer cannot be initialized!");
        }
    }
}
/* Output:
Employee cannot be initialized!
 */