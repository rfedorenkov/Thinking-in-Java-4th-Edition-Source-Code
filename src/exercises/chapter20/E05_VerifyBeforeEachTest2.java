package exercises.chapter20;

import net.mindview.atunit.Test;
import net.mindview.util.OSExecute;

import java.util.HashSet;

/**
 * Exercise 5
 * Modify the above example to use the inheritance approach.
 */
public class E05_VerifyBeforeEachTest2 extends HashSet<String> {
    @Test
    void testOne() {
        assert isEmpty();
        add("one");
    }

    @Test
    void testTwo() {
        assert isEmpty();
        add("one");
    }

    public static void main(String[] args) throws Exception {
        OSExecute.command("java net.mindview.atunit.AtUnit E05_VerifyBeforeEachTest2");
    }
}
/* Output:
exercises.chapter20.E05_VerifyBeforeEachTest2
  . testOne
  . testTwo
OK (2 tests)
 */