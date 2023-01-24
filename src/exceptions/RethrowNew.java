package exceptions;

/**
 * Rethrow a different object from the one that was caught.
 */
class OneException extends Exception {
    public OneException(String s) {
        super(s);
    }
}

class TwoException extends Exception {
    public TwoException(String s) {
        super(s);
    }
}

public class RethrowNew {
    public static void f() throws OneException {
        System.out.println("originating the exception in f()");
        throw new OneException("thrown from f()");
    }

    public static void main(String[] args) {
        try {
            try {
                f();
            } catch (OneException e) {
                System.out.println("Caught in inner try, e.printStackTrace()");
                e.printStackTrace(System.out);
                throw new TwoException("from inner try");
            }
        } catch (TwoException e) {
            System.out.println("Caught in outer try, e.printStackTrace()");
            e.printStackTrace(System.out);
        }
    }
}
/* Output:
originating the exception in f()
Caught in inner try, e.printStackTrace()
exceptions.OneException: thrown from f()
	at exceptions.RethrowNew.f(RethrowNew.java:21)
	at exceptions.RethrowNew.main(RethrowNew.java:27)
Caught in outer try, e.printStackTrace()
exceptions.TwoException: from inner try
	at exceptions.RethrowNew.main(RethrowNew.java:31)
 */