package exercises.chapter10;

/**
 * Exercise 18
 * Create a class containing a nested class.
 * In main(), create an instance of the nested class.
 */
class OtherNestedClass {
    static class OtherNested {
        OtherNested() {
            System.out.println("OtherNested constructed");
        }
    }
}

public class E18_NestedClass {
    static class Nested {
        Nested() {
            System.out.println("Nested constructed");
        }
    }

    public static void main(String[] args) {
        Nested nested1 = new Nested();
        OtherNestedClass.OtherNested nested2 = new OtherNestedClass.OtherNested();
    }
}
/* Output:
Nested constructed
OtherNested constructed
 */