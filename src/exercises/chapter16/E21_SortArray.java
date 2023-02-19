package exercises.chapter16;

import net.mindview.util.Generated;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collections;

import static net.mindview.util.Print.print;

/**
 * Exercise 21
 * Try to sort an array of the objects in Exercise 18.
 * Implement Comparable to fix the problem. Now create
 * a Comparator to sort the objects into reverse order.
 */
class ComparableBerylliumSphere extends BerylliumSphere implements Comparable<BerylliumSphere> {
    private long getId(BerylliumSphere bs) {
        try {
            Field id = BerylliumSphere.class.getDeclaredField("id");
            id.setAccessible(true);
            return id.getLong(bs);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int compareTo(BerylliumSphere o) {
        long id1 = getId(this);
        long id2 = getId(o);
        return id1 < id2 ? -1 : (id1 == id2 ? 0 : 1);
    }
}

public class E21_SortArray {
    public static void main(String[] args) {
        BerylliumSphere[] spheres = Generated.array(new BerylliumSphere[10], new BerylliumSphereGenerator());
        Collections.shuffle(Arrays.asList(spheres));
        print("before sort:");
        print(Arrays.toString(spheres));
        try {
            print("try sort...");
            Arrays.sort(spheres);
            print(Arrays.toString(spheres));
        } catch (ClassCastException e) {
            print("Array cannot be sorted! " + e.getClass());
        }
        for (int i = 0; i < spheres.length; i++)
            spheres[i] = new ComparableBerylliumSphere();
        Collections.shuffle(Arrays.asList(spheres));
        print("before sort:");
        print(Arrays.toString(spheres));
        Arrays.sort(spheres);
        print(Arrays.toString(spheres));
        print("reverse order:");
        Arrays.sort(spheres, Collections.reverseOrder());
        print(Arrays.toString(spheres));
    }
}
/* Output:
before sort:
[Sphere 6, Sphere 9, Sphere 3, Sphere 2, Sphere 8, Sphere 0, Sphere 7, Sphere 4, Sphere 5, Sphere 1]
try sort...
Array cannot be sorted! class java.lang.ClassCastException
before sort:
[Sphere 11, Sphere 18, Sphere 12, Sphere 16, Sphere 14, Sphere 10, Sphere 19, Sphere 13, Sphere 17, Sphere 15]
[Sphere 10, Sphere 11, Sphere 12, Sphere 13, Sphere 14, Sphere 15, Sphere 16, Sphere 17, Sphere 18, Sphere 19]
reverse order:
[Sphere 19, Sphere 18, Sphere 17, Sphere 16, Sphere 15, Sphere 14, Sphere 13, Sphere 12, Sphere 11, Sphere 10]
 */