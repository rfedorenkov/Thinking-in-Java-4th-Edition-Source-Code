package exercises.chapter20;

import net.mindview.atunit.Test;
import net.mindview.util.OSExecute;

import java.util.HashSet;

/**
 * Exercise 4
 * Verify that a new testObject is created before each test.
 */
public class E04_VerifyBeforeEachTest {
    HashSet<String> testObject = new HashSet<>();

    @Test
    void testOne() {
        assert testObject.isEmpty();
        testObject.add("one");
    }

    @Test
    void testTwo() {
        assert testObject.isEmpty();
        testObject.add("one");
    }

    public static void main(String[] args) throws Exception {
        OSExecute.command("java net.mindview.atunit.AtUnit E04_VerifyBeforeEachTest");
    }
}
/* Output:
exercises.chapter20.E04_VerifyBeforeEachTest
  . testOne
  . testTwo
OK (2 tests)
 */