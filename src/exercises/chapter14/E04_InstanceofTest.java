package exercises.chapter14;

/**
 * Exercise 4
 * Modify the previous exercise so that it uses instanceof
 * to check the type before performing the downcast.
 */
public class E04_InstanceofTest {
    public static void main(String[] args) {
        // Upcast to Shape
        HShape shape = new Rhomboid();
        // Downcast to Rhomboid
        Rhomboid rhomboid = (Rhomboid) shape;
        // Downcast to Circle
        HCircle circle = null;
        if (shape instanceof HCircle)
            circle = (HCircle) shape;
        else
            System.out.println("shape \"" + shape + "\" not a circle");
    }
}
/* Output:
shape "Rhomboid" not a circle
 */