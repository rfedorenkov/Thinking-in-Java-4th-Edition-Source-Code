package exercises.chapter16;

import java.util.ArrayList;
import java.util.List;

/**
 * Exercise 10
 * Modify ArrayOfGenerics.java to use containers instead of arrays.
 * Show that you can eliminate the compile-time warnings.
 */
public class E10_ArrayOfGenerics2 {
    public static void main(String[] args) {
        ArrayList<List<String>> ls;
        ArrayList<List<String>> la = new ArrayList<>(10);
        ls = la;
        ls.add(new ArrayList<String>());

        // Compile-tome checking produces an error:
        // ls.add(new ArrayList<Integer>());

        ArrayList<List<BerylliumSphere>> spheres = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            spheres.add(new ArrayList<BerylliumSphere>());
            spheres.get(i).add(new BerylliumSphere());
        }
        System.out.println(spheres);
    }
}
/* Output:
[[Sphere 0], [Sphere 1], [Sphere 2], [Sphere 3], [Sphere 4], [Sphere 5], [Sphere 6], [Sphere 7], [Sphere 8], [Sphere 9]]
 */