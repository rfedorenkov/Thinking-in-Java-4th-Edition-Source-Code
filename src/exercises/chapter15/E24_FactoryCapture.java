package exercises.chapter15;

import java.util.HashMap;
import java.util.Map;

/**
 * Exercise 24
 * Modify Exercise 21 so that factory objects are
 * held in the Map instead of Class<?>.
 */
public class E24_FactoryCapture {
    Map<String, FactoryI<?>> factories = new HashMap<>();

    public void addFactory(String factoryName, FactoryI<?> factory) {
        factories.put(factoryName, factory);
    }

    public Object createNew(String factoryName, int arg) {

        try {
            FactoryI<?> factory = factories.get(factoryName);
            return factory.create(arg);
        } catch (NullPointerException e) {
            System.out.println("Unregistered factoryName: " + factoryName);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public static void main(String[] args) {
        E24_FactoryCapture fc = new E24_FactoryCapture();
        fc.addFactory("Integer", new IntegerFactory());
        fc.addFactory("Widget", new Widget.Factory());
        System.out.println(fc.createNew("Integer", 1).getClass().getSimpleName());
        System.out.println(fc.createNew("Widget", 1).getClass().getSimpleName());
        System.out.println(fc.createNew("House", 2).getClass().getSimpleName());
    }
}
/* Output:
Integer
Widget
Unregistered factoryName: House
Exception in thread "main" java.lang.NullPointerException
	at exercises.chapter15.E24_FactoryCapture.main(E24_FactoryCapture.java:37)
 */