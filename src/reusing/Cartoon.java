package reusing;

import static net.mindview.util.Print.print;

/**
 * Constructor calls during inheritance.
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

public class Cartoon extends Drawing {
    Cartoon() {
        print("Cartoon constructor");
    }

    public static void main(String[] args) {
        Cartoon x = new Cartoon();
    }
}
/* Output:
Art constructor
Drawing constructor
Cartoon constructor
 */