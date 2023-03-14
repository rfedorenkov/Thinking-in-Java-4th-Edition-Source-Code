package exercises.chapter20;

import net.mindview.atunit.Test;
import net.mindview.util.OSExecute;

import java.util.HashMap;

/**
 * Exercise 9
 * Write a basic @Unit tests for HashMap.
 */
public class E09_HashMapTest {
    HashMap<Integer, String> map = new HashMap<>();

    @Test
    void _get() {
        map.put(1, "Test");
        assert map.get(1).equals("Test");
    }

    @Test
    void _isEmpty() {
        assert map.isEmpty();
    }

    @Test
    void _remove() {
        map.put(1, "Test");
        map.remove(1);
        assert map.isEmpty();
    }

    @Test
    void _containsKey() {
        map.put(1, "Test");
        assert map.containsKey(1);
    }

    @Test
    void _containsValue() {
        map.put(1, "Test");
        assert map.containsValue("Test");
    }

    @Test
    void _size() {
        map.put(1, "TestOne");
        map.put(2, "TestTwo");
        assert map.size() == 2;
    }

    @Test
    void _clear() {
        map.put(1, "TestOne");
        map.put(2, "TestTwo");
        assert !map.isEmpty();
        map.clear();
        assert map.isEmpty();
    }

    public static void main(String[] args) throws Exception {
        OSExecute.command("java net.mindview.atunit.AtUnit E09_HashMapTest");
    }
}
/* Output:
exercises.chapter20.E09_HashMapTest
  . _get
  . _isEmpty
  . _remove
  . _size
  . _clear
  . _containsKey
  . _containsValue
OK (7 tests)
 */