package exercises.chapter14;

import java.util.Arrays;
import java.util.List;

/**
 * Exercise 5
 * Implement a rotate (Shape) method in Shapes.java,
 * such that it check to see if it is rotating a Circle
 * (and, if so, doesn't perform the operation).
 */
public class E05_RotateShapes {
    static void rotate(HShape shape) {
        if (!(shape instanceof HCircle)) {
            System.out.println(shape + " rotated");
        }
    }

    public static void main(String[] args) {
        List<HShape> shapeList = Arrays.asList(
                new HCircle(), new HSquare(), new HTriangle()
        );
        for (HShape shape : shapeList)
            rotate(shape);
    }
}
/* Output:
Square rotated
Triangle rotated
 */