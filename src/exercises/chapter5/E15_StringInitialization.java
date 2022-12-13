package exercises.chapter5;

/**
 * Exercise 15
 * Create a class with a String that is
 * initialized using "instance initialization".
 */
class NonStatic {
    String string;

    {
        string = "Initializing string";
        System.out.println("string initialized");
    }

    public NonStatic() {
        System.out.println("default constructor, string = " + string);
    }
}

public class E15_StringInitialization {
    public static void main(String[] args) {
        System.out.println("Inside main()");
        new NonStatic();
    }
}
/* Output:
Inside main()
string initialized
default constructor, string = Initializing string
 */