package exercises.chapter20;

import net.mindview.atunit.Test;
import net.mindview.util.OSExecute;

import java.util.LinkedList;

/**
 * Exercise 6
 * Test LinkedList using the approach shown in HashSetTest.java.
 */
public class E06_LinkedListTest {
    LinkedList<String> testObject = new LinkedList<>();

    @Test
    void initialization() {
        assert testObject.isEmpty();
    }

    @Test
    void _contains() {
        testObject.add("one");
        assert testObject.contains("one");
    }

    @Test
    void _remove() {
        testObject.add("one");
        testObject.remove("one");
        assert testObject.isEmpty();
    }

    public static void main(String[] args) throws Exception {
        OSExecute.command("java net.mindview.atunit.AtUnit E06_LinkedListTest");
    }
}
/* Output:
exercises.chapter20.E06_LinkedListTest
  . _contains
  . _remove
  . initialization
OK (3 tests)
 */