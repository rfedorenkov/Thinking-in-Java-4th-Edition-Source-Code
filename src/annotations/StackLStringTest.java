package annotations;

import net.mindview.atunit.Test;
import net.mindview.util.OSExecute;

/**
 * Applying @Unit to generics.
 */
public class StackLStringTest extends StackL<String> {
    @Test
    void _push() {
        push("one");
        assert top().equals("one");
        push("two");
        assert top().equals("two");
    }

    @Test
    void _pop() {
        push("one");
        push("two");
        assert pop().equals("two");
        assert pop().equals("one");
    }

    @Test
    void _top() {
        push("A");
        push("B");
        assert top().equals("B");
        assert top().equals("B");
    }

    public static void main(String[] args) {
        OSExecute.command("java net.mindview.atunit.AtUnit StackLStringTest");
    }
}
/* Output:
annotations.StackLStringTest
  . _push
  . _pop
  . _top
OK (3 tests)
 */