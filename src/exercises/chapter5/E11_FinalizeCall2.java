package exercises.chapter5;

/**
 * Exercise 11
 * Modify Exercise 10 so your finalize() will always be called.
 */
class Book2 {
    @Override
    protected void finalize() {
        System.out.println("finalize()");
    }
}
public class E11_FinalizeCall2 {
    public static void main(String[] args) {
        new Book();
        System.gc();
    }
}
/* Output:
finalize()
 */