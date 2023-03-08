package enumerated;

import net.mindview.util.OSExecute;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Set;
import java.util.TreeSet;

import static net.mindview.util.Print.print;
import static net.mindview.util.Print.printnb;

/**
 * Analyzing enums using reflection.
 */
enum Explore {
    HERE, THERE
}

public class Reflection {
    public static Set<String> analyze(Class<?> enumClass) {
        print("----- Analyzing " + enumClass + " -----");
        print("Interfaces:");
        for (Type t : enumClass.getGenericInterfaces())
            print(t);
        print("Base: " + enumClass.getSuperclass());
        print("Methods: ");
        Set<String> methods = new TreeSet<>();
        for (Method m : enumClass.getMethods())
            methods.add(m.getName());
        print(methods);
        return methods;
    }

    public static void main(String[] args) {
        Set<String> exploreMethods = analyze(Explore.class);
        Set<String> enumMethods = analyze(Enum.class);
        print("Explore.containsAll(Enum)? " +
                exploreMethods.containsAll(enumMethods));
        printnb("Explore.removeAll(Enum): ");
        exploreMethods.removeAll(enumMethods);
        print(exploreMethods);
        // Decompile the code for the enum:
        OSExecute.command("javap out/classes/enumerated/Explore.class");
    }
}
/* Output:
----- Analyzing class enumerated.Explore -----
Interfaces:
Base: class java.lang.Enum
Methods:
[compareTo, describeConstable, equals, getClass, getDeclaringClass, hashCode, name, notify, notifyAll, ordinal, toString, valueOf, values, wait]
----- Analyzing class java.lang.Enum -----
Interfaces:
interface java.lang.constant.Constable
java.lang.Comparable<E>
interface java.io.Serializable
Base: class java.lang.Object
Methods:
[compareTo, describeConstable, equals, getClass, getDeclaringClass, hashCode, name, notify, notifyAll, ordinal, toString, valueOf, wait]
Explore.containsAll(Enum)? true
Explore.removeAll(Enum): [values]
Compiled from "Reflection.java"
final class enumerated.Explore extends java.lang.Enum<enumerated.Explore> {
  public static final enumerated.Explore HERE;
  public static final enumerated.Explore THERE;
  public static enumerated.Explore[] values();
  public static enumerated.Explore valueOf(java.lang.String);
  static {};
}
 */