package exercises.chapter5;

/**
 * Exercise 2
 * Create a class with a String field initialized at the point of definition,
 * and another one initialized by the constructor.
 * What is the difference between the two approaches?
 */
class Test2 {
    String s1 = "Initialized at definition";
    String s2;
    Test2(String s) {
        s2 = s;
    }
}

public class E02_ConstructorTest2 {
    public static void main(String[] args) {
        Test2 test = new Test2("Initialization in the constructor");
        System.out.println("s1 = " + test.s1);
        System.out.println("s2 = " + test.s2);
    }
}
/* Output:
s1 = Initialized at definition
s2 = Initialization in the constructor
 */