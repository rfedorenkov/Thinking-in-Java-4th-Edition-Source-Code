package exercises.chapter11;

import polymorphism.shape.*;

import java.util.Iterator;
import java.util.Random;

/**
 * Exercise 31
 * Modify polymorphism/shape/RandomShapeGenerator.java
 * to make it Iterable. You'll need to add a constructor
 * that takes the number of elements that you want the
 * iterator to produce before stopping. Verify that is works.
 */
public class E31_RandomShapeGenerator implements Iterable<Shape> {
    private Random random = new Random(47);
    private final int amount;

    public E31_RandomShapeGenerator(int amount) {
        this.amount = amount;
    }

    public Shape next() {
        switch (random.nextInt(3)) {
            default:
            case 0: return new Circle();
            case 1: return new Square();
            case 2: return new Triangle();
        }
    }

    @Override
    public Iterator<Shape> iterator() {
        return new Iterator<Shape>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < amount;
            }

            @Override
            public Shape next() {
                index++;
                return E31_RandomShapeGenerator.this.next();
            }

            @Override
            public void remove() { // Not implemented
                throw new UnsupportedOperationException();
            }
        };
    }

    public static void main(String[] args) {
        E31_RandomShapeGenerator generator = new E31_RandomShapeGenerator(5);
        for (Shape shape : generator)
            System.out.println(shape.getClass().getSimpleName());
    }
}
/* Output:
Triangle
Triangle
Square
Triangle
Square
 */