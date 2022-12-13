package exercises.chapter5;

/**
 * Exercise 17
 * Crate a class with a constructor that takes a String argument.
 * During construction, print the argument. Create an array of object
 * references to this class, but don't create objects to assign into
 * the array. When you run the program, notice whether the initialization
 * messages from the constructor calls are printed.
 */
class InitTest {
    String s;

    InitTest(String s) {
        System.out.println("InitTest(" + s + ")");
        this.s = s;
    }
}

public class E17_ObjectReferences {
    public static void main(String[] args) {
        InitTest[] arr = new InitTest[5];
    }
}