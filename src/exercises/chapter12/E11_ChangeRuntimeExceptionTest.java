package exercises.chapter12;

/**
 * Exercise 11
 * Repeat the previous exercise, but inside the catch
 * clause, wrap g()'s exception in a RuntimeException.
 * {ThrowsException}
 */
class ChangeRuntimeException extends ChangeException {
    @Override
    void f() {
        try {
            g();
        } catch (MainException e) {
            throw new RuntimeException();
        }
    }
}

public class E11_ChangeRuntimeExceptionTest {
    public static void main(String[] args) {
        ChangeRuntimeException cre = new ChangeRuntimeException();
        cre.f(); // Throws RuntimeException
    }
}