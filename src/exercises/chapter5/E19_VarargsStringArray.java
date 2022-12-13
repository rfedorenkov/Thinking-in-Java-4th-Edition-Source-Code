package exercises.chapter5;

/**
 * Exercise 19
 * Write a method that takes a vararg String array.
 * Verify thar you can pass either a comma-separated list
 * of String or a String[] into this method.
 */
public class E19_VarargsStringArray {
    static void printStrings(String... args) {
        for (String s : args)
            System.out.print(s + " ");
        System.out.println();
    }
    public static void main(String[] args) {
        printStrings("one", "two", "three");
        printStrings(new String[]{ "one", "two", "three" });
    }
}
/* Output:
one two three
one two three
 */