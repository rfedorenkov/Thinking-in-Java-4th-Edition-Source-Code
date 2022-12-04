package exercises.chapter3;

import static net.mindview.util.Print.print;

/**
 * Exercise 3
 * Create a class containing a float and use it
 * to demonstrate aliasing during method calls.
 */
public class E03_DemonstrateAliasing2 {
    static void f(Line l) {
        l.length = 1.2345f;
    }

    public static void main(String[] args) {
        Line line = new Line();
        line.length = 10.5f;
        print("1: line.length: " + line.length);
        f(line);
        print("2: line.length: " + line.length);
    }
}
/* Output:
1: line.length: 10.5
2: line.length: 1.2345
 */

class Line {
    float length;
}