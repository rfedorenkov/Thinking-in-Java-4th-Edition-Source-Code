package containers;

/**
 * Looks plausible, but doesn't work as a HashMap key.
 */
public class GroundHog {
    protected int number;

    public GroundHog(int n) {
        number = n;
    }

    @Override
    public String toString() {
        return "Groundhog #" + number;
    }
}