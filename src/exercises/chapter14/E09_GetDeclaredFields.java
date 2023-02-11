package exercises.chapter14;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import static net.mindview.util.Print.print;

/**
 * Exercise 9
 * Modify the previous exercise so that it uses
 * Class.getDeclaredFields() to also display
 * information about the fields in a class.
 * {Args: java.util.ArrayList}
 */
public class E09_GetDeclaredFields {
    static void printInfo(Class<?> clazz) {
        if (clazz == null) return;
        print("Class: " + clazz.getCanonicalName());
        Field[] fields = clazz.getDeclaredFields();
        if (fields.length != 0)
            print("Fields:");
        for (Field field : fields)
            print("     " + Modifier.toString(field.getModifiers()) +
                    " " + field.getType() + " " + field.getName());
        Class<?>[] interfaces = clazz.getInterfaces();
        for (Class<?> i : interfaces) {
            print("Interface: " + i);
            printInfo(i.getSuperclass());
        }
        printInfo(clazz.getSuperclass());
    }

    public static void main(String[] args) {
        if (args.length < 1) {
            print("Usage: java E09_GetDeclaredFields java.util.ArrayList");
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
Fields:
     private static final long serialVersionUID
     private static final int DEFAULT_CAPACITY
     private static final class [Ljava.lang.Object; EMPTY_ELEMENTDATA
     private static final class [Ljava.lang.Object; DEFAULTCAPACITY_EMPTY_ELEMENTDATA
     transient class [Ljava.lang.Object; elementData
     private int size
Interface: interface java.util.List
Interface: interface java.util.RandomAccess
Interface: interface java.lang.Cloneable
Interface: interface java.io.Serializable
Class: java.util.AbstractList
Fields:
     protected transient int modCount
Interface: interface java.util.List
Class: java.util.AbstractCollection
Interface: interface java.util.Collection
Class: java.lang.Object
**********
 */