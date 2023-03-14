package exercises.chapter20;

import net.mindview.atunit.Test;
import net.mindview.atunit.TestProperty;
import net.mindview.util.OSExecute;

/**
 * Exercise 8
 * Add a non-private @TestProperty method (described above)
 * to create a class with a private method.
 * Call this method in your test code.
 */
class Private {
    private String hiddenMethod() {
        System.out.println("hiddenMethod()");
        return "This is hidden method";
    }

    @TestProperty
    public String callHiddenMethod() {
        System.out.println("callHiddenMethod()");
        return hiddenMethod();
    }
}

public class E08_PrivateMethodTest extends Private {
    @Test
    public boolean testHiddenMethod() {
        return callHiddenMethod().equals("This is hidden method");
    }

    public static void main(String[] args) throws Exception {
        OSExecute.command("java net.mindview.atunit.AtUnit E08_PrivateMethodTest");
    }
}
/* Output:
exercises.chapter20.E08_PrivateMethodTest
  . testHiddenMethod callHiddenMethod()
hiddenMethod()

OK (1 tests)
 */