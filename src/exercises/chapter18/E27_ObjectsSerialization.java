package exercises.chapter18;

import java.io.*;
import java.util.Arrays;
import java.util.Random;

import static net.mindview.util.Print.print;

/**
 * Exercise 27
 * Create a Serializable class containing a reference to an object
 * of a second Serializable class. Create an instance of your class,
 * serialize it to disk, then restore it and verify that the process
 * worked correctly.
 */
class A implements Serializable {
    private B b;
    public A(B b) {
        this.b = b;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " (" + b + ")";
    }
}

class B implements Serializable {
    private static Random rand = new Random(47);
    private int[] data;

    public B() {
        data = new int[5];
        for (int i = 0; i < data.length; i++) {
            data[i] = rand.nextInt(10);
        }
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() +
                ", data = " + Arrays.toString(data);
    }
}

public class E27_ObjectsSerialization {
    public static void main(String[] args) throws ClassNotFoundException, IOException {
        print("Create A(new B())");
        A a = new A(new B());
        print("a = " + a);
        print("Save object..");
        ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream("src/exercises/chapter18/E27_data.out"));
        out.writeObject(a);
        out.close();
        print("Load object...");
        ObjectInputStream in = new ObjectInputStream(
                new FileInputStream("src/exercises/chapter18/E27_data.out"));
        A a2 = (A) in.readObject();
        print("a2 = " + a2);
    }
}
/* Output:
Create A(new B())
a = A (B, data = [8, 5, 3, 1, 1])
Save object..
Load object...
a2 = A (B, data = [8, 5, 3, 1, 1])
 */