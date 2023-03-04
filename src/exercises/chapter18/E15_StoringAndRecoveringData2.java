package exercises.chapter18;

import java.io.*;

import static net.mindview.util.Print.print;

/**
 * Exercise 15
 * Look up DataOutputStream and DataInputStream in the JDK
 * documentation. Starting with StoringAndRecoveringData.java,
 * create a program that stores and then retrieves all the
 * different possible types provided by the DataOutputStream
 * and DataInputStream classes. Verify that the values are stored
 * and retrieved accurately.
 */
public class E15_StoringAndRecoveringData2 {
    public static void main(String[] args) throws IOException {
        DataOutputStream out = new DataOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream("src/exercises/chapter18/E15_Data.txt")));
        out.writeBoolean(true);
        out.writeChar('Y');
        out.writeByte(255);
        out.writeByte(255);
        out.writeShort(65535);
        out.writeShort(65535);
        out.writeInt(Integer.MAX_VALUE);
        out.writeLong(Long.MAX_VALUE);
        out.writeFloat(1.1234f);
        out.writeDouble(3.14159);
        out.writeUTF("That was pi");
        out.close();

        DataInputStream in = new DataInputStream(
                new BufferedInputStream(
                        new FileInputStream("./src/exercises/chapter18/E15_Data.txt")));
        print("in.readBoolean(): " + in.readBoolean());
        print("in.readChar(): " + in.readChar());
        print("in.readByte(): " + in.readByte());
        print("in.readUnsignedByte(): " + in.readUnsignedByte());
        print("in.readShort(): " + in.readShort());
        print("in.readUnsignedShort(): " + in.readUnsignedShort());
        print("in.readInt(): " + in.readInt());
        print("in.readLong(): " + in.readLong());
        print("in.readFloat(): " + in.readFloat());
        print("in.readDouble(): " + in.readDouble());
        // Only readUTF() will recover the
        // Java-UTF String properly:
        print("in.readUTF(): " + in.readUTF());
    }
}
/* Output:
in.readBoolean(): true
in.readChar(): Y
in.readByte(): -1
in.readUnsignedByte(): 255
in.readShort(): -1
in.readUnsignedShort(): 65535
in.readInt(): 2147483647
in.readLong(): 9223372036854775807
in.readFloat(): 1.1234
in.readDouble(): 3.14159
in.readUTF(): That was pi
 */