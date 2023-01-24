package exercises.chapter12;

/**
 * Exercise 18
 * Add a second level of exception loss to LostMessage.java
 * so that the HoHumException is itself replaced by a third exception.
 */
class VeryImportantException extends Exception {
    @Override
    public String toString() {
        return "A very important exception!";
    }
}

class HoHumException extends Exception {
    @Override
    public String toString() {
        return "A trivial exception";
    }
}

class AnotherException extends Exception {
    @Override
    public String toString() {
        return "An another exception";
    }
}

class LostMessage2 {
    void f() throws VeryImportantException {
        throw new VeryImportantException();
    }

    void dispose() throws HoHumException {
        throw new HoHumException();
    }

    void g() throws AnotherException {
        throw new AnotherException();
    }
}

public class E18_LostMessageTest {
    public static void main(String[] args) {
        try {
            LostMessage2 lm = new LostMessage2();
            try {
                try {
                    lm.f();
                } finally {
                    lm.dispose();
                }
            } finally {
                lm.g();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
/* Output:
An another exception
 */