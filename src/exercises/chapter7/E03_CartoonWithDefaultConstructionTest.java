package exercises.chapter7;

import static net.mindview.util.Print.print;

/**
 * Exercise 3
 * Even if you don't create a constructor for Cartoon(),
 * the compiler will synthesize a default constructor
 * that calls the base-class constructor.
 * Prove that assertion.
 */
class Art {
    Art() {
        print("Art constructor");
    }
}

class Drawing extends Art {
    Drawing() {
        print("Drawing constructor");
    }
}

class Cartoon extends Drawing {

}

public class E03_CartoonWithDefaultConstructionTest {
    public static void main(String[] args) {
        Cartoon x = new Cartoon();
    }
}
/* Output:
Art constructor
Drawing constructor
 */