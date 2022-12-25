package exercises.chapter8;

import static net.mindview.util.Print.print;

/**
 * Exercise 4
 * Add a new type of Shape to Shapes.java and
 * verify in main() that polymorphism works for
 * your new type as it does in the old types.
 */
class Line extends Shape {
    @Override
    public void draw() {
        print("Line.draw()");
    }

    @Override
    public void erase() {
        print("Line.erase()");
    }
}

public class E04_Shape4 {
    public static void main(String[] args) {
        Shape[] s = { new Circle(), new Square(), new Triangle(), new Line() };
        // Make polymorphic method calls
        for (Shape shp : s) {
            shp.draw();
            shp.erase();
        }
    }
}
/* Output:
Circle.draw()
Circle.erase()
Square.draw()
Square.erase()
Triangle.draw()
Triangle.erase()
Line.draw()
Line.erase()
 */