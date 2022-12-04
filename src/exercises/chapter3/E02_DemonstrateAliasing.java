package exercises.chapter3;

import static net.mindview.util.Print.print;

/**
 * Exercise 2
 * Create a class containing a float
 * and use it to demonstrate aliasing.
 */
public class E02_DemonstrateAliasing {
    public static void main(String[] args) {
        Angle a1 = new Angle();
        Angle a2 = new Angle();
        a1.angle = 90f;
        a2.angle = 45f;
        print("1: a1.angle: " + a1.angle + ", a2.angle: " + a2.angle);
        a1 = a2;
        print("2: a1.angle: " + a1.angle + ", a2.angle: " + a2.angle);
        a2.angle = 180f;
        print("3: a1.angle: " + a1.angle + ", a2.angle: " + a2.angle);
    }
}
/* Output:
1: a1.angle: 90.0, a2.angle: 45.0
2: a1.angle: 45.0, a2.angle: 45.0
3: a1.angle: 180.0, a2.angle: 180.0
 */

class Angle {
    float angle;
}