package exercises.chapter5;

import static net.mindview.util.Print.print;

/**
 * Exercise 13
 * Verify the statements in the previous paragraph.
 */
class Cup {
    Cup(int marker) {
        print("Cup(" + marker + ")");
    }

    void f(int marker) {
        print("f(" + marker + ")");
    }
}

class Cups {
    static Cup cup1;
    static Cup cup2;

    static {
        cup1 = new Cup(1);
        cup2 = new Cup(2);
    }

    Cups() {
        System.out.println("Cups");
    }
}

public class E13_ExplicitStaticTest {
    public static void main(String[] args) {
        print("Inside main()");
        Cups.cup1.f(99); // (1)
    }

    static Cups cups1 = new Cups(); // (2)
    static Cups cups2 = new Cups(); // (2)
}
/* Output:
Cup(1)
Cup(2)
Cups
Cups
Inside main()
f(99)
 */