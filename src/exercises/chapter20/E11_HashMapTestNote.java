package exercises.chapter20;

import net.mindview.atunit.Test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.HashMap;

/**
 * Exercise 11
 * Add an @TestNote annotation to @Unit, so that the
 * accompanying note is simply displayed during testing.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface TestNote {
    public String value();
}

public class E11_HashMapTestNote {
    HashMap<Integer, String> map = new HashMap<>();

    @Test
    @TestNote("Test map.get(Object key) method")
    void _get() {
        map.put(1, "Test");
        assert map.get(1).equals("Test");
    }

    @Test
    @TestNote("Test map.isEmpty() method")
    void _isEmpty() {
        assert map.isEmpty();
    }

    @Test
    @TestNote("Test map.remove(Object key) method")
    void _remove() {
        map.put(1, "Test");
        map.remove(1);
        assert map.isEmpty();
    }

    @Test
    @TestNote("Test map.containsKey(Object key) method")
    void _containsKey() {
        map.put(1, "Test");
        assert map.containsKey(1);
    }

    @Test
    @TestNote("Test map.containsValue(Object value) method")
    void _containsValue() {
        map.put(1, "Test");
        assert map.containsValue("Test");
    }

    @Test
    @TestNote("Test map.size() method")
    void _size() {
        map.put(1, "TestOne");
        map.put(2, "TestTwo");
        assert map.size() == 2;
    }

    @Test
    @TestNote("Test map.clear() method")
    void _clear() {
        map.put(1, "TestOne");
        map.put(2, "TestTwo");
        assert !map.isEmpty();
        map.clear();
        assert map.isEmpty();
    }
}
