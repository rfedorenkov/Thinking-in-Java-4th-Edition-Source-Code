package exercises.chapter10;

/**
 * Exercise 5
 * Create a class with an inner class.
 * In a separate class, make an instance of the inner class.
 */
class Outer3 {
    Outer3() {
        System.out.println("Outer constructed");
    }

    class Inner3 {
        Inner3() {
            System.out.println("Inner constructed");
        }
    }
}
public class E05_InstanceInnerClassTest {
    public static void main(String[] args) {
        Outer3 o = new Outer3();
        Outer3.Inner3 i = o.new Inner3();
    }
}
/* Output:
Outer constructed
Inner constructed
 */