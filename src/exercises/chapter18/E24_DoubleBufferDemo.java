package exercises.chapter18;

import java.nio.ByteBuffer;
import java.nio.DoubleBuffer;

/**
 * Exercise 24
 * Modify IntBufferDemo.java to use doubles.
 */
public class E24_DoubleBufferDemo {
    private static final int BSIZE = 1024;

    public static void main(String[] args) {
        ByteBuffer bb = ByteBuffer.allocate(BSIZE);
        DoubleBuffer db = bb.asDoubleBuffer();
        // Store an array of double:
        db.put(new double[]{ 1.1, 4.2, 4.7, 9.9, 1.43, 8.11, 10.16 });
        // Absolute location read and write:
        System.out.println(db.get(3));
        db.put(3, 18.11);
        // Setting a new limit before rewinding the buffer.
        db.flip();
        while (db.hasRemaining()) {
            double d = db.get();
            System.out.println(d);
        }
    }
}
/* Output:
9.9
1.1
4.2
4.7
18.11
1.43
8.11
10.16
 */