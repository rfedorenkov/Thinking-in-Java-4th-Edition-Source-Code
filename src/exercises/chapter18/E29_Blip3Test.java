package exercises.chapter18;

import java.io.*;

import static net.mindview.util.Print.print;

/**
 * Exercise 29
 * In Blip3.java, comment out the two lines after
 * the phrases "You must do this:" and run the program.
 * Explain the result and why it differs from when
 * the two lines are in the program.
 */
class Blip3 implements Externalizable {
    private int i;
    private String s; // No initialization

    public Blip3() {
        print("Blip3 Constructor");
        // s, i not initialized
    }

    public Blip3(String x, int a) {
        print("Blip3(String x, int a)");
        s = x;
        i = a;
        // s & i initialized only in non-default constructor.
    }

    @Override
    public String toString() {
        return s + i;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        print("Blip3.writeExternal");
        // You must do this:
        // out.writeObject(s);
        // out.writeInt(i);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        print("Blip3.readExternal");
        // You must do this:
        // s = (String) in.readObject();
        // i = in.readInt();
    }
}

public class E29_Blip3Test {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        print("Constructing objects:");
        Blip3 b3 = new Blip3("A String ", 47);
        print(b3);
        ObjectOutputStream o = new ObjectOutputStream(
                new FileOutputStream("src/exercises/chapter18/E29_Blip3.out"));
        print("Saving object:");
        o.writeObject(b3);
        o.close();
        // Now get it back:
        ObjectInputStream in = new ObjectInputStream(
                new FileInputStream("src/exercises/chapter18/E29_Blip3.out"));
        print("Recovering b3:");
        b3 = (Blip3) in.readObject();
        print(b3);
    }
}
/* Output:
Constructing objects:
Blip3(String x, int a)
A String 47
Saving object:
Blip3.writeExternal
Recovering b3:
Blip3 Constructor
Blip3.readExternal
null0
 */