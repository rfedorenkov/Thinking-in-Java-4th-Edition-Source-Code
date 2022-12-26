package exercises.chapter9;

/**
 * Exercise 13
 * Create an interface, inherit two new interfaces
 * from it, then multiply-inherit a third interface
 * from the second two.
 */
interface Action {
    void action();
}

interface SuperAction extends Action {
    void superAction();
}

interface FastAction extends Action {
    void fastAction();
}

interface MoreAction extends Action, SuperAction {
    void moreAction();
}

class TestAction implements MoreAction {

    @Override
    public void action() {
        System.out.println("TestAction.action()");
    }

    @Override
    public void superAction() {
        System.out.println("TestAction.superAction()");
    }

    @Override
    public void moreAction() {
        System.out.println("TestAction.moreAction()");
    }
}

public class E13_DiamondInheritance {
    public static void main(String[] args) {
        TestAction test = new TestAction();
        test.action();
        test.superAction();
        //! test.fastAction(); // Interface FastAction not implemented
        test.moreAction();
    }
}
/* Output:
TestAction.action()
TestAction.superAction()
TestAction.moreAction()
 */