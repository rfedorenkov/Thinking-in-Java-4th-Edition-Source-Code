package exercises.chapter16;

/**
 * Exercise 4
 * Repeat the previous exercise for a three-dimensional array.
 */
public class E04_ThreeDimensionalArrayOfDouble {
    static double[][][] createArray(int sizeX, int sizeY, int sizeZ, double startValue, double endValue) {
        double[][][] result = new double[sizeX][sizeY][sizeZ];
        double increment = (endValue - startValue) / (sizeX * sizeY * sizeZ);
        double value = startValue;
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                for (int k = 0; k < result[i][j].length; k++) {
                    result[i][j][k] = value;
                    value += increment;
                }
            }
        }
        return result;
    }

    static void printDeepArray(double[][][] arr) {
        for (double[][] value : arr) {
            for (double[] doubles : value) {
                for (double d : doubles)
                    System.out.printf("%.2f ", d);
                System.out.println();
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        printDeepArray(createArray(1, 7, 2, 1.0, 99.0));
        System.out.println("********************");
        printDeepArray(createArray(2, 3, 3,50.0, 80.0));
        System.out.println("********************");
        printDeepArray(createArray(5, 4, 6, 33.3, 66.6));
    }
}
/* Output:
1.00 8.00
15.00 22.00
29.00 36.00
43.00 50.00
57.00 64.00
71.00 78.00
85.00 92.00

********************
50.00 51.67 53.33
55.00 56.67 58.33
60.00 61.67 63.33

65.00 66.67 68.33
70.00 71.67 73.33
75.00 76.67 78.33

********************
33.30 33.58 33.86 34.13 34.41 34.69
34.97 35.24 35.52 35.80 36.08 36.35
36.63 36.91 37.19 37.46 37.74 38.02
38.30 38.57 38.85 39.13 39.41 39.68

39.96 40.24 40.52 40.79 41.07 41.35
41.63 41.90 42.18 42.46 42.74 43.01
43.29 43.57 43.85 44.12 44.40 44.68
44.96 45.23 45.51 45.79 46.07 46.34

46.62 46.90 47.18 47.45 47.73 48.01
48.29 48.56 48.84 49.12 49.40 49.67
49.95 50.23 50.51 50.78 51.06 51.34
51.62 51.89 52.17 52.45 52.73 53.00

53.28 53.56 53.84 54.11 54.39 54.67
54.95 55.22 55.50 55.78 56.06 56.33
56.61 56.89 57.17 57.44 57.72 58.00
58.28 58.55 58.83 59.11 59.39 59.66

59.94 60.22 60.50 60.77 61.05 61.33
61.61 61.88 62.16 62.44 62.72 62.99
63.27 63.55 63.83 64.10 64.38 64.66
64.94 65.21 65.49 65.77 66.05 66.32
 */