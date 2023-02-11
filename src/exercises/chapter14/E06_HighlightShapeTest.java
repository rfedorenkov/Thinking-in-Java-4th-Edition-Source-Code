package exercises.chapter14;

import java.util.Arrays;
import java.util.List;

/**
 * Exercise 6
 * Modify Shapes.java so that it can "highlight"
 * (set a flag) in all shapes of a particular type.
 * The toString() method for each derived Shape should
 * indicate whether that Shape is "highlighted".
 */
class HShape {
    private boolean highlighted;

    void draw() {
        System.out.println(this + "draw()");
    }

    void switchHighlight(boolean highlighted) {
        this.highlighted = highlighted;
    }

    @Override
    public String toString() {
        String s = highlighted ? " highlighted" : " default";
        return getClass().getSimpleName() + s + " color ";
    }
}

class HCircle extends HShape {
}

class HSquare extends HShape {
}

class HTriangle extends HShape {
}

public class E06_HighlightShapeTest {
    private static List<HShape> shapeList = Arrays.asList(
            new HCircle(), new HSquare(), new HTriangle(),
            new HCircle(), new HSquare(), new HCircle()
    );

    public static void highlight(Class<? extends HShape> type, boolean highlight) {
        for (HShape shape : shapeList) {
            if (shape.getClass().equals(type)) {
                shape.switchHighlight(highlight);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Shapes highlighting by default:");
        for (HShape shape : shapeList)
            shape.draw();
        System.out.println("**********");
        System.out.println("HCircle highlight on:");
        for (HShape shape : shapeList) {
            highlight(HCircle.class, true);
            shape.draw();
        }
    }
}
/* Output:
Shapes highlighting by default:
HCircle default color draw()
HSquare default color draw()
HTriangle default color draw()
HCircle default color draw()
HSquare default color draw()
HCircle default color draw()
**********
HCircle highlight on:
HCircle highlighted color draw()
HSquare default color draw()
HTriangle default color draw()
HCircle highlighted color draw()
HSquare default color draw()
HCircle highlighted color draw()
 */