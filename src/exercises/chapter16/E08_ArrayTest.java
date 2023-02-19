package exercises.chapter16;

/**
 * Exercise 8
 * "Erasure gets in the way again-this example attempts to create
 * arrays of types that have been erased, and are thus unknown types.
 * Notice that you can create an array of Object, and cast it, but
 * you get an "unchecked" warning at compile time because the array
 * doesn't really hold or dynamically check for type T. That is, if
 * I create a String[], Java will enforce at both compile time and
 * tun time that I can only place String objects in that array.
 * However, if I create an Object[], I can put anything except
 * primitive types in that array."
 *
 * Demonstrate the assertions in the previous paragraph.
 */
public class E08_ArrayTest<T> {
    @SuppressWarnings("unchecked")
    T[] arr = (T[]) new Object[5];

    public static void main(String[] args) {
        E08_ArrayTest<String> test = new E08_ArrayTest<String>();
        // test.arr[0] = new String("String"); // Error ClassCastException
        // test.arr[1] = new Object(); // Error Incompatible types
        String[] arr1 = new String[5];
        arr1[1] = new String("String");
        Object[] arr2 = new Object[5];
        arr2[0] = new String("String");
        arr2[1] = new Object();
        arr2[2] = new Integer(47);
        arr2[3] = 2.2f; // Autoboxing
        arr2 = arr1;
        // arr2[3] = 2.2f; // ArrayStoreException
    }
}