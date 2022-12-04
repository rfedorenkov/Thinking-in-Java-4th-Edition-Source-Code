package operators;

import static net.mindview.util.Print.print;

class Letter {
    char c;
}

/**
 * Passing objects to methods may
 * not be what you're used to.
 */
public class PassObject {
    static void f(Letter y) {
        y.c = 'z';
    }

    public static void main(String[] args) {
        Letter x = new Letter();
        x.c = 'a';
        print("1: x.c: " + x.c);
        f(x);
        print("2: x.c: " + x.c);
    }
}