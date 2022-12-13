package exercises.chapter5;

/**
 * Exercise 16
 * Assign a string to each element of an array of String objects.
 * Print the array using a for loop.
 */
public class E16_StringArray {
    public static void main(String[] args) {
        String[] arr = new String[5];
        arr[0] = "cat";
        arr[1] = "dog";
        arr[2] = "horse";
        arr[3] = "cow";
        arr[4] = "duck";
        for (String s : arr)
            System.out.println(s);
    }
}
/* Output:
cat
dog
horse
cow
duck
 */