package exercises.chapter15;

/**
 * Exercise 2
 * Create a holder class that holds three objects of the same type,
 * along with the methods to store and fetch those objects and
 * a constructor to initialize all three.
 */
public class E02_Holder<T> {
    private T x;
    private T y;
    private T z;

    E02_Holder(T x, T y, T z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public T getX() {
        return x;
    }

    public void setX(T x) {
        this.x = x;
    }

    public T getY() {
        return y;
    }

    public void setY(T y) {
        this.y = y;
    }

    public T getZ() {
        return z;
    }

    public void setZ(T z) {
        this.z = z;
    }

    public static void main(String[] args) {
        E02_Holder<String> h = new E02_Holder<>("X", "Y", "Z");
        System.out.println(h.getX());
        System.out.println(h.getY());
        System.out.println(h.getZ());
        h.setX("A");
        h.setY("B");
        h.setZ("C");
        System.out.println(h.getX());
        System.out.println(h.getY());
        System.out.println(h.getZ());
    }
}
/* Output:
X
Y
Z
A
B
C
 */