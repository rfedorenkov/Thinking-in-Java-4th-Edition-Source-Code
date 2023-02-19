package exercises.chapter16;

import java.util.Arrays;

import static net.mindview.util.Print.print;

/**
 * Exercise 18
 * Create and fill an array of BerylliumSphere.
 * Copy this array to a new array and show that it's a shallow copy.
 */
public class E18_SystemArrayCopyTest {
    public static void main(String[] args) {
        BerylliumSphere[] spheres1 = new BerylliumSphere[10];
        BerylliumSphere[] spheres2 = new BerylliumSphere[5];
        Arrays.fill(spheres1, new BerylliumSphere());
        print("sphere1 = " + Arrays.toString(spheres1));
        print("sphere2 = " + Arrays.toString(spheres2));
        spheres1[3] = new BerylliumSphere();
        System.arraycopy(spheres1, 0, spheres2, 0, spheres2.length);
        print("sphere1 = " + Arrays.toString(spheres1));
        print("sphere2 = " + Arrays.toString(spheres2));
    }
}
/* Output:
sphere1 = [Sphere 0, Sphere 0, Sphere 0, Sphere 0, Sphere 0, Sphere 0, Sphere 0, Sphere 0, Sphere 0, Sphere 0]
sphere2 = [null, null, null, null, null]
sphere1 = [Sphere 0, Sphere 0, Sphere 0, Sphere 1, Sphere 0, Sphere 0, Sphere 0, Sphere 0, Sphere 0, Sphere 0]
sphere2 = [Sphere 0, Sphere 0, Sphere 0, Sphere 1, Sphere 0]
 */