package exercises.chapter14;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.regex.Pattern;

import static net.mindview.util.Print.print;

/**
 * Exercise 17
 * Modify the regular expression in ShowMethods.java to also
 * strip off the keywords native and final.
 * (Hint: Use the OR operator '|'.)
 * {Args: exercises.chapter14.E17_ShowMethods2}
 */
public class E17_ShowMethods2 {
    private static String usage = "usage:\n" +
            "E17_ShowMethods2 qualified.class.name\n" +
            "To show all methods in class or:\n" +
            "E17_ShowMethods2 qualified.class.name word\n" +
            "To search for methods involving 'word'";

    private static Pattern p = Pattern.compile("\\w+\\.|final\\s|native\\s");

    public static void main(String[] args) {
        if (args.length < 1) {
            print(usage);
            System.exit(0);
        }
        int lines = 0;
        try {
            Class<?> c = Class.forName(args[0]);
            Method[] methods = c.getMethods();
            Constructor<?>[] ctors = c.getConstructors();
            if (args.length == 1) {
                for (Method method : methods)
                    print(p.matcher(method.toString()).replaceAll(""));
                for (Constructor<?> ctor : ctors)
                    print(p.matcher(ctor.toString()).replaceAll(""));
                lines = methods.length + ctors.length;
            } else {
                for (Method method : methods)
                    if (method.toString().indexOf(args[1]) != -1) {
                        print(p.matcher(method.toString()).replaceAll(""));
                        lines++;
                    }
                for (Constructor<?> ctor : ctors)
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
public void wait(long) throws InterruptedException
public void wait(long,int) throws InterruptedException
public void wait() throws InterruptedException
public boolean equals(Object)
public String toString()
public int hashCode()
public Class getClass()
public void notify()
public void notifyAll()
public E17_ShowMethods2()
 */