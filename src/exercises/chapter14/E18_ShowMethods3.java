package exercises.chapter14;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.regex.Pattern;

import static net.mindview.util.Print.print;

/**
 * Exercise 18
 * Make ShowMethods a non-public class and verify that the synthesized
 * default constructor no longer appears in the output.
 * {Args: exercises.chapter14.E18_ShowMethods3}
 */
class E18_ShowMethods3 {
    private static String usage = "usage:\n" +
            "E18_ShowMethods3 qualified.class.name\n" +
            "To show all methods in class or:\n" +
            "E18_ShowMethods3 qualified.class.name word\n" +
            "To search for methods involving 'word'";

    private static Pattern p = Pattern.compile("\\w+\\.");

    public static void main(String[] args) {
        if (args.length < 1) {
            print(usage);
            System.exit(0);
        }
        int lines = 0;
        try {
            Class<?> c = Class.forName(args[0]);
            Method[] methods = c.getMethods();
            Constructor[] ctors = c.getConstructors();
            if (args.length == 1) {
                for (Method method : methods)
                    print(p.matcher(method.toString()).replaceAll(""));
                for (Constructor ctor : ctors)
                    print(p.matcher(ctor.toString()).replaceAll(""));
                lines = methods.length + ctors.length;
            } else {
                for (Method method : methods)
                    if (method.toString().indexOf(args[1]) != -1) {
                        print(p.matcher(method.toString()).replaceAll(""));
                        lines++;
                    }
                for (Constructor ctor : ctors)
                    if (ctor.toString().indexOf(args[1]) != -1) {
                        print(p.matcher(ctor.toString()).replaceAll(""));
                        lines++;
                    }
            }
        } catch (ClassNotFoundException e) {
            print("No such class: " + e);
        }
    }
}
/* Output:
public static void main(String[])
public final native void wait(long) throws InterruptedException
public final void wait(long,int) throws InterruptedException
public final void wait() throws InterruptedException
public boolean equals(Object)
public String toString()
public native int hashCode()
public final native Class getClass()
public final native void notify()
public final native void notifyAll()
 */