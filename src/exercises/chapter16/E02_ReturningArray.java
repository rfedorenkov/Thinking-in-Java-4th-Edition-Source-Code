package exercises.chapter16;

import java.util.Arrays;

/**
 * Exercise 2
 * Write a method that takes an int argument and returns
 * an array of that size, filled with BerylliumSphere objects.
 */
public class E02_ReturningArray {
    public static BerylliumSphere[] createSpheres(int size) {
        BerylliumSphere[] result = new BerylliumSphere[size];
        for (int i = 0; i < size; i++)
            result[i] = new BerylliumSphere();
        return result;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(createSpheres(5)));
    }
}
/* Output:
[Sphere 0, Sphere 1, Sphere 2, Sphere 3, Sphere 4]
 */