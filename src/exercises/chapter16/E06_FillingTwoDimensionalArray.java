package exercises.chapter16;

import java.util.Arrays;

/**
 * Exercise 6
 * Write a method that takes two int arguments, indicating
 * the two sizes of a 2-D array. The method should create
 * and fill a 2-D array of BerylliumSphere according to
 * the size arguments.
 */
public class E06_FillingTwoDimensionalArray {
    static BerylliumSphere[][] createArray(int sizeX, int sizeY) {
        BerylliumSphere[][] result = new BerylliumSphere[sizeX][sizeY];
        for (int i = 0; i < result.length; i++)
            for (int j = 0; j < result[i].length; j++)
                result[i][j] = new BerylliumSphere();
        return result;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(createArray(2, 5)));
    }
}
/* Output:
[[Sphere 0, Sphere 1, Sphere 2, Sphere 3, Sphere 4], [Sphere 5, Sphere 6, Sphere 7, Sphere 8, Sphere 9]]
 */