package exercises.chapter14;

import java.util.Arrays;
import java.util.List;

/**
 * Exercise 3
 * Add Rhomboid in Shapes.java. Create a Rhomboid,
 * upcast it to a Shape, then downcast it back to
 * a Rhomboid. Try downcasting to a Circle and see
 * what happens.
 */
abstract class Shape {
    void draw() {
        System.out.println(this + ".draw()");
    }

    @Override
    abstract public String toString();
}

class Circle extends HShape {
    @Override
    public String toString() {
        return "Circle";
    }
}

class Square extends HShape {
    @Override
    public String toString() {
        return "Square";
    }
}

class Triangle extends HShape {
    @Override
    public String toString() {
        return "Triangle";
    }
}

class Rhomboid extends HShape {
    @Override
    public String toString() {
        return "Rhomboid";
    }
}

public class E03_RhomboidToCircleTest {
    public static void main(String[] args) {
        List<HShape> shapeList = Arrays.asList(
                new HCircle(), new HSquare(), new HTriangle(), new Rhomboid()
        );
        for (HShape shape : shapeList)
            shape.draw();

        try {
            // Upcast to Shape
            HShape shape = new Rhomboid();
            // Downcast to Rhomboid
            Rhomboid rhomboid = (Rhomboid) shape;
            // Downcast to Circle
            HCircle fakeCircle = (HCircle) shape; // throw ClassCastException
        } catch (Exception e) {
            System.out.println("Caught exception: " + e.getClass());
        }
    }
}
/* Output:
Circle.draw()
Square.draw()
Triangle.draw()
Rhomboid.draw()
Caught exception: class java.lang.ClassCastException
 */