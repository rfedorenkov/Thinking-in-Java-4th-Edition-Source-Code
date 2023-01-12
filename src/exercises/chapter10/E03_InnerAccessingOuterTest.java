package exercises.chapter10;

/**
 * Exercise 3
 * Modify Exercise 1 so Outer has a private String field
 * (initialized by the constructor), and Inner has a toString()
 * that displays this field. Create an object of type Inner and display it.
 */
class Outer2 {
    private String s;

    Outer2(String s) {
        this.s = s;
    }
    class Inner2 {

        @Override
        public String toString() {
            return s;
        }
    }

    public Inner2 getInner() {
        return new Inner2();
    }
}
public class E03_InnerAccessingOuterTest {
    public static void main(String[] args) {
        Outer2 o = new Outer2("Hello world!");
        Outer2.Inner2 i = o.getInner();
        System.out.println(i);
    }
}
/* Output:
Hello world!
 */