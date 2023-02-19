package exercises.chapter16;

/**
 * Exercise 11
 * Show that autoboxing doesn't work with arrays.
 */
public class E11_AutoboxingWithArrays {
    public static void main(String[] args) {
        Integer[] arr1 = new Integer[1];
        arr1[0] = 47;
        int[] arr2 = new int[1];
        arr2[0] = new Integer(47);
        // Integer[] arr3 = arr2; // Error
        // int[] arr4 = arr1; // Error
    }
}