package exercises.chapter14;

import static net.mindview.util.Print.print;

/**
 * Exercise 8
 * Write a method that takes an object and recursively
 * prints all the classes in that object's hierarchy.
 * {Args: java.util.ArrayList java.util.HashMap}
 */
public class E08_RecursiveClassHierarchyPrint {
    static void printInfo(Class<?> clazz) {
        if (clazz == null) return;
        print("Class: " + clazz.getCanonicalName());
        Class<?>[] interfaces = clazz.getInterfaces();
        for (Class<?> i : interfaces) {
            print("Interface: " + i);
            printInfo(i.getSuperclass());
        }
        printInfo(clazz.getSuperclass());
    }

    public static void main(String[] args) {
        if (args.length < 1) {
            print("Usage: java E08_RecursiveClassHierarchyPrint java.util.ArrayList");
            System.exit(0);
        }

        for (String s : args) {
            try {
                System.out.println("View: " + s);
                printInfo(Class.forName(s));
                System.out.println("**********");
            } catch (ClassNotFoundException e) {
                print("Can't find class: " + s);
                System.exit(1);
            }
        }
    }
}
/* Output:
View: java.util.ArrayList
Class: java.util.ArrayList
Interface: interface java.util.List
Interface: interface java.util.RandomAccess
Interface: interface java.lang.Cloneable
Interface: interface java.io.Serializable
Class: java.util.AbstractList
Interface: interface java.util.List
Class: java.util.AbstractCollection
Interface: interface java.util.Collection
Class: java.lang.Object
**********
View: java.util.HashMap
Class: java.util.HashMap
Interface: interface java.util.Map
Interface: interface java.lang.Cloneable
Interface: interface java.io.Serializable
Class: java.util.AbstractMap
Interface: interface java.util.Map
Class: java.lang.Object
**********
 */