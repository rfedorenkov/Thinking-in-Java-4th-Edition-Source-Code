package exercises.chapter8;

import static net.mindview.util.Print.print;

/**
 * Exercise 3
 * Add a new method in the base class of Shapes.java
 * that prints a message, but don't override it in the
 * derived classes. Explain what happens. Now override
 * it in only one of the derived classes and see what
 * happens. Finally, override it in all the derived classes.
 */
class Shape2 {
    public void draw() {
    }

    public void erase() {
    }

    public void rotate() {
        print("Shape.rotate()");
    }
}

class Circle2 extends Shape2 {
    @Override
    public void draw() {
        print("Circle.draw()");
    }

    @Override
    public void erase() {
        print("Circle.erase()");
    }

    @Override
    public void rotate() {
        print("Circle.rotate()");
    }
}

class Square2 extends Shape2 {
    @Override
    public void draw() {
        print("Square.draw()");
    }

    @Override
    public void erase() {
        print("Square.erase()");
    }

    @Override
    public void rotate() {
        print("Square.rotate()");
    }
}

class Triangle2 extends Shape2 {
    @Override
    public void draw() {
        print("Triangle.draw()");
    }

    @Override
    public void erase() {
        print("Triangle.erase()");
    }

    @Override
    public void rotate() {
        print("Triangle.rotate()");
    }
}

public class E03_Shape3 {
    public static void main(String[] args) {
        Shape2[] s = { new Circle2(), new Square2(), new Triangle2() };
        // Make polymorphic method calls
        for (Shape2 shp : s) {
            shp.draw();
            shp.erase();
            shp.rotate();
        }
    }
}
/* Output:
Circle.draw()
Circle.erase()
Circle.rotate()
Square.draw()
Square.erase()
Square.rotate()
Triangle.draw()
Triangle.erase()
Triangle.rotate()
 */