package exercises.chapter18;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;

import static net.mindview.util.Print.print;
import static net.mindview.util.Print.printnb;

/**
 * Exercise 23
 * Create and test a utility method to print the contents
 * of a CharBuffer up to the point where the characters
 * are no longer printable.
 */
public class E23_PrintCharBuffer {

    private static boolean checkPrintable(char c) {
        return c >= 32 && c <= 127;
    }
    public static void setLimit(CharBuffer cb) {
        cb.rewind();
        while (cb.hasRemaining())
            if (!checkPrintable(cb.get()))
                break;
        cb.limit(cb.position() - 1);
        cb.rewind();
    }

    public static void main(String[] args) {
        char[] chars = ("Hello world" + (char) 0x0F + " Don't print").toCharArray();
        CharBuffer buffer = ByteBuffer.allocate(64).asCharBuffer();
        buffer.put(chars);
        buffer.rewind();
        printnb("Default print: ");
        print(buffer);
        setLimit(buffer);
        printnb("After set limit: ");
        print(buffer);
    }
}
/* Output:
Default print: Hello world Don't print        
After set limit: Hello world
 */