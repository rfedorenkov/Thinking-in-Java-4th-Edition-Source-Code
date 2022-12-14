package exercises.chapter2;

/**
 * Exercise 1
 * Create a class containing an int and a char
 * that are not initialized. Print their values
 * to verity that Java performs default initialization.
 */
public class E01_DefaultInitialization {
    public static void main(String[] args) {
        PrimitiveTest test = new PrimitiveTest();
        System.out.println("test.i = " + test.i);
        System.out.println("test.c = " + test.c);
    }
}
/* Output:
test.i = 0
test.c =  
 */

class PrimitiveTest {
    int i;
    char c;
}