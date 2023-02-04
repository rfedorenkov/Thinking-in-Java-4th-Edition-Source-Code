package exercises.chapter13;

/**
 * Exercise 6
 * Create a class that contains int, long, float and double fields.
 * Create a toString() method for this class that uses String.format(),
 * and demonstrate that your class works correctly.
 */
public class E06_StringFormatClassTest {
    private int i;
    private long l;
    private float f;
    private double d;

    public E06_StringFormatClassTest(int i, long l, float f, double d) {
        this.i = i;
        this.l = l;
        this.f = f;
        this.d = d;
    }

    @Override
    public String toString() {
        return String.format("%s: i = %d, l = %d, f = %f, d = %e", getClass().getSimpleName(), i, l, f, d);
    }

    public static void main(String[] args) {
        E06_StringFormatClassTest test = new E06_StringFormatClassTest(10, -10, 22.22f, 47.47d);
        System.out.println(test);
    }
}
/* Output:
E06_StringFormatClassTest: i = 10, l = -10, f = 22.219999, d = 4.747000e+01
 */