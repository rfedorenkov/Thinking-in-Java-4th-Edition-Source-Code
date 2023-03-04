package exercises.chapter18;

import java.io.IOException;
import java.io.RandomAccessFile;

import static net.mindview.util.Print.print;

/**
 * Exercise 16
 * Look up RandomAccessFile in the JDK documentation. Starting
 * with UsingRandomAccessFile.java, create a program that stores
 * and then retrieves all the different possible types provided
 * by the RandomAccessFile class. Verify that the values are
 * stored and retrieved accurately.
 */
public class E16_UsingRandomAccessFile2 {
    static String file = "src/exercises/chapter18/E16_rtest.dat";

    static void display() throws IOException {
        RandomAccessFile rf = new RandomAccessFile(file, "r");
        print("rf.readBoolean(): " + rf.readBoolean());
        print("rf.readChar(): " + rf.readChar());
        print("rf.readByte(): " + rf.readByte());
        print("rf.readUnsignedByte(): " + rf.readUnsignedByte());
        print("rf.readShort(): " + rf.readShort());
        print("rf.readUnsignedShort(): " + rf.readUnsignedShort());
        print("rf.readInt(): " + rf.readInt());
        print("rf.readLong(): " + rf.readLong());
        print("rf.readFloat(): " + rf.readFloat());
        print("rf.readDouble(): " + rf.readDouble());
        print("rf.readUTF(): " + rf.readUTF());
        rf.close();
    }

    public static void main(String[] args) throws IOException {
        RandomAccessFile rf = new RandomAccessFile(file, "rw");
        rf.writeBoolean(true);
        rf.writeChar('Y');
        rf.writeByte(255);
        rf.writeByte(255);
        rf.writeShort(65535);
        rf.writeShort(65535);
        rf.writeInt(Integer.MAX_VALUE);
        rf.writeLong(Long.MAX_VALUE);
        rf.writeFloat(1.1234f);
        rf.writeDouble(3.14159);
        rf.writeUTF("The end of the file");
        rf.close();
        display();
        print();
        rf = new RandomAccessFile(file, "rw");
        rf.seek(33);
        rf.writeUTF("Hello World");
        rf.seek(1);
        rf.writeChar('X');
        rf.seek(9);
        rf.writeInt(Integer.MIN_VALUE);
        rf.seek(0);
        rf.writeBoolean(false);
        rf.close();
        display();
    }
}
/* Output:
rf.readBoolean(): true
rf.readChar(): Y
rf.readByte(): -1
rf.readUnsignedByte(): 255
rf.readShort(): -1
rf.readUnsignedShort(): 65535
rf.readInt(): 2147483647
rf.readLong(): 9223372036854775807
rf.readFloat(): 1.1234
rf.readDouble(): 3.14159
rf.readUTF(): The end of the file

rf.readBoolean(): false
rf.readChar(): X
rf.readByte(): -1
rf.readUnsignedByte(): 255
rf.readShort(): -1
rf.readUnsignedShort(): 65535
rf.readInt(): -2147483648
rf.readLong(): 9223372036854775807
rf.readFloat(): 1.1234
rf.readDouble(): 3.14159
rf.readUTF(): Hello World
 */