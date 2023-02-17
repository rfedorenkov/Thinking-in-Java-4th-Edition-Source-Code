package exercises.chapter15;

import typeinfo.pets.Cat;

import java.util.HashMap;
import java.util.Map;

/**
 * Exercise 21
 * Modify ClassTypeCapture.java by adding a Map<String, Class<?>>,
 * a method addType(String typename, Class<?> king), and a method
 * createNew(String typename). createNew() will either produce a new
 * instance of the class associated with its argument string, or
 * produce an error message.
 */
class Building {
}

class House extends Building {
}

public class E21_ClassTypeCapture2<T> {
    Map<String, Class<?>> map = new HashMap<>();

    public void addType(String typename, Class<?> kind) {
        map.put(typename, kind);
    }

    public Object createNew(String typename) {
        try {
            Class<?> clazz = map.get(typename);
            return clazz.newInstance();
        } catch (NullPointerException e) {
            System.out.println("Unregistered typename: " + typename);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public static void main(String[] args) {
        E21_ClassTypeCapture2<Building> ctt1 = new E21_ClassTypeCapture2<>();
        ctt1.addType("Building", Building.class);
        ctt1.addType("House", House.class);
        ctt1.addType("Cat", Cat.class);

        System.out.println(ctt1.createNew("Building").getClass().getSimpleName());
        System.out.println(ctt1.createNew("House").getClass().getSimpleName());
        System.out.println(ctt1.createNew("Cat").getClass().getSimpleName());
        System.out.println(ctt1.createNew("Dog").getClass().getSimpleName());
    }
}
/* Output:
Building
House
Cat
Unregistered typename: Dog
Exception in thread "main" java.lang.NullPointerException
	at exercises.chapter15.E21_ClassTypeCapture2.main(E21_ClassTypeCapture2.java:50)
 */