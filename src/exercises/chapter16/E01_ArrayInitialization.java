package exercises.chapter16;

import java.util.Arrays;

/**
 * Exercise 1
 * Create a method that takes an array of BerylliumSphere
 * as an argument. Call the method, creating the argument
 * dynamically. Demonstrate that ordinary aggregate array
 * initialization doesn't work in this case. Discover the
 * only situations where ordinary aggregate array initialization
 * works, and where dynamic aggregate initialization is redundant.
 */
class BerylliumSphere {
    private static long counter;
    private final long id = counter++;

    @Override
    public String toString() {
        return "Sphere " + id;
    }
}

public class E01_ArrayInitialization {
    public static void show(BerylliumSphere[] bs) {
        System.out.println("show: " + Arrays.toString(bs) + ", length " + bs.length);
    }

    public static void main(String[] args) {
        // Dynamic aggregate initialization
        show(new BerylliumSphere[]{ new BerylliumSphere(), new BerylliumSphere() });
        // Aggregate initialization
        //! hide({ new BerylliumSphere(), new BerylliumSphere() }); // Illegal start of expression
        // Dynamic aggregate initialization
        BerylliumSphere[] bs1 = new BerylliumSphere[] { new BerylliumSphere(), new BerylliumSphere() };
        show(bs1);
        // Aggregate initialization
        BerylliumSphere[] bs2 = { new BerylliumSphere(), new BerylliumSphere() };
        show(bs2);
    }
}
/* Output:
show: [Sphere 0, Sphere 1], length 2
show: [Sphere 2, Sphere 3], length 2
show: [Sphere 4, Sphere 5], length 2
 */