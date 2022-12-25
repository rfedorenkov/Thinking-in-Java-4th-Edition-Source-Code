package exercises.chapter8;

import static net.mindview.util.Print.print;

/**
 * Exercise 15
 * Add a RectangularGlyph to PolyConstructors.java
 * add demonstrate the problem described in this section.
 */
class Glyph {
    void draw() {
        print("Glyph.draw()");
    }

    Glyph() {
        print("Glyph() before draw()");
        draw();
        print("Glyph() after draw()");
    }
}

class RoundGlyph extends Glyph {
    private int radius = 1;

    RoundGlyph(int r) {
        radius = r;
        print("RoundGlyph.RoundGlyph(), radius = " + radius);
    }

    @Override
    void draw() {
        print("RoundGlyph.draw(), radius = " + radius);
    }
}

class RectangularGlyph extends Glyph {
    private int width = 1;
    private int height = 1;

    RectangularGlyph(int w, int h) {
        width = w;
        height = h;
        print("RoundGlyph.RoundGlyph(), width = " + width + ", height = " + height);
    }

    @Override
    void draw() {
        print("RoundGlyph.draw(), width = " + width + ", height = " + height);
    }
}

public class E15_PolyConstructors2 {
    public static void main(String[] args) {
        new RoundGlyph(5);
        new RectangularGlyph(10, 15);
    }
}
/* Output:
Glyph() before draw()
RoundGlyph.draw(), radius = 0
Glyph() after draw()
RoundGlyph.RoundGlyph(), radius = 5
Glyph() before draw()
RoundGlyph.draw(), width = 0, height = 0
Glyph() after draw()
RoundGlyph.RoundGlyph(), width = 10, height = 15
 */