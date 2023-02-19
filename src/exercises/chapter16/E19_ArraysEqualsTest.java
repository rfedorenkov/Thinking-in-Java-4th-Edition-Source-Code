package exercises.chapter16;

import java.util.Arrays;

/**
 * Exercise 19
 * Create a class with an int field that's initialized from
 * a constructor argument. Create two arrays of these objects,
 * using identical initialization values for each array, and
 * shot that Arrays.equals() says that they are unequal.
 * Add an equals() method to your class to fix the problem.
 */
class WithoutEquals {
    int i;

    WithoutEquals(int i) {
        this.i = i;
    }

    @Override
    public String toString() {
        return String.valueOf(i);
    }
}

class WithEquals {
    int i;

    WithEquals(int i) {
        this.i = i;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WithEquals that = (WithEquals) o;
        return i == that.i;
    }

    @Override
    public String toString() {
        return String.valueOf(i);
    }
}

public class E19_ArraysEqualsTest {
    public static void main(String[] args) {
        WithoutEquals[] a1 = new WithoutEquals[5];
        WithoutEquals[] a2 = new WithoutEquals[5];
        Arrays.fill(a1, new WithoutEquals(5));
        Arrays.fill(a2, new WithoutEquals(5));
        System.out.println("Try without equals");
        System.out.println("a1 = " + Arrays.toString(a1));
        System.out.println("a2 = " + Arrays.toString(a2));
        System.out.println("Arrays.equals(a1, a2) = " + Arrays.equals(a1, a2));
        System.out.println("********************");
        WithEquals[] a3 = new WithEquals[5];
        WithEquals[] a4 = new WithEquals[5];
        Arrays.fill(a3, new WithEquals(5));
        Arrays.fill(a4, new WithEquals(5));
        System.out.println("Try with equals");
        System.out.println("a3 = " + Arrays.toString(a3));
        System.out.println("a4 = " + Arrays.toString(a4));
        System.out.println("Arrays.equals(a3, a4) = " + Arrays.equals(a3, a4));
    }
}
/* Output:
Try without equals
a1 = [5, 5, 5, 5, 5]
a2 = [5, 5, 5, 5, 5]
Arrays.equals(a1, a2) = false
********************
Try with equals
a3 = [5, 5, 5, 5, 5]
a4 = [5, 5, 5, 5, 5]
Arrays.equals(a3, a4) = true
 */

