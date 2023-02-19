package exercises.chapter16;

import java.util.Arrays;

/**
 * Exercise 7
 * Repeat the previous exercise for a 3-D array.
 */
public class E07_FillingThreeDimensionalArray {
    static BerylliumSphere[][][] createArray(int sizeX, int sizeY, int sizeZ) {
        BerylliumSphere[][][] result = new BerylliumSphere[sizeX][sizeY][sizeZ];
        for (int i = 0; i < result.length; i++)
            for (int j = 0; j < result[i].length; j++)
                for (int k = 0; k < result[i][j].length; k++)
                    result[i][j][k] = new BerylliumSphere();

        return result;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(createArray(2, 2, 3)));
    }
}
/* Output:
[[[Sphere 0, Sphere 1, Sphere 2], [Sphere 3, Sphere 4, Sphere 5]], [[Sphere 6, Sphere 7, Sphere 8], [Sphere 9, Sphere 10, Sphere 11]]]
 */