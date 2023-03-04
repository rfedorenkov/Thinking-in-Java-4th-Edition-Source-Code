package exercises.chapter18;

import java.io.*;

import static net.mindview.util.Print.print;

/**
 * Exercise 28
 * In Blips.java, copy the file and rename it to BlipCheck.java
 * and rename the class Blip2 to BlipCheck (making it public
 * and removing the public scope from the class Blips in the
 * process). Remove the //! marks in the file and execute the
 * program including the offending lines. Next, comment out
 * the default constructor for BlipCheck. Run it and explain
 * why it works. Note that after compiling, you must execute
 * the program with "java Blips" because the main() method
 * is still in class Blips.
 */
class Blip1 implements Externalizable {
    public Blip1() {
        print("Blip1 Constructor");
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        print("Blip1.writeExternal");
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        print("Blip1.readExternal");
    }
}

public class E28_BlipCheck implements Externalizable {
    // E28_BlipCheck() {
    //     print("BlipCheck Constructor");
    // }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        print("BlipCheck.writeExternal");
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        print("BlipCheck.readExternal");
    }
}

class Blips {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        print("Constructing objects:");
        Blip1 b1 = new Blip1();
        E28_BlipCheck b2 = new E28_BlipCheck();
        ObjectOutputStream o = new ObjectOutputStream(
                new FileOutputStream("src/exercises/chapter18/E28_BlipCheck.out"));
        print("Saving objects:");
        o.writeObject(b1);
        o.writeObject(b2);
        o.close();
        // Now get them back:
        ObjectInputStream in = new ObjectInputStream(
                new FileInputStream("src/exercises/chapter18/E28_BlipCheck.out"));
        print("Recovering b1:");
        b1 = (Blip1) in.readObject();
        // OOPS! Throws an exception:
        print("Recovering b2:");
        b2 = (E28_BlipCheck) in.readObject();
    }
}
/* Output:
Constructing objects:
Blip1 Constructor
Saving objects:
Blip1.writeExternal
BlipCheck.writeExternal
Recovering b1:
Blip1 Constructor
Blip1.readExternal
Recovering b2:
BlipCheck.readExternal
 */