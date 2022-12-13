package initialization;

/**
 * Using array syntax to create variable argument lists.
 */
public class NewVarArgs {
    static void printArray(Object... args) {
        for (Object obj : args)
            System.out.print(obj + " ");
        System.out.println();
    }

    public static void main(String[] args) {
        // Can take individual elements:
        printArray(new Integer(47), new Float(3.14), new Double(11.11));
        printArray(47, 3.14F, 11.11);
        printArray("one", "two", "three");
        printArray(new A(), new A(), new A());
        // Or an array:
        printArray((Object[]) new Integer[] { 1, 2, 3, 4});
        printArray(); // Empty list is OK
    }
}
/* Output:
47 3.14 11.11
47 3.14 11.11
one two three
initialization.A@49e4cb85 initialization.A@2133c8f8 initialization.A@43a25848
1 2 3 4
 */