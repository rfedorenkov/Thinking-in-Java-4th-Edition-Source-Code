package containers;

/**
 * A class that's used as a key in a HashMap
 * must override hashCode() and equals().
 */
public class GroundHog2 extends GroundHog {
    public GroundHog2(int n) {
        super(n);
    }

    @Override
    public int hashCode() {
        return number;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof GroundHog2 &&
                (number == ((GroundHog2) o).number);
    }
}