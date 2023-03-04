package exercises.chapter18;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;

import static net.mindview.util.Print.print;

/**
 * Exercise 25
 * Experiment with changing the ByteBuffer.allocate() statements
 * in the examples is this chapter to ByteBuffer.allocateDirect().
 * Demonstrate performance differences, but also notice whether
 * the startup time of the programs noticeably changes.
 */
abstract class CompareAllocations {
    private String name;
    protected ByteBuffer buffer;
    private int size;

    public CompareAllocations(String name, int size) {
        this.name = name;
        this.size = size;
    }

    public void runComparison() {
        print("Program name: \"" + name + "\"");
        try {
            long start = System.nanoTime();
            directAllocate();
            long end = System.nanoTime();
            print("Direct Allocation cost for buffer of size: "
                    + size + " is " + (end - start));
            start = System.nanoTime();
            execute();
            end = System.nanoTime();
            print("Execution cost using direct buffer: " + (end - start));
            start = System.nanoTime();
            indirectAllocate();
            end = System.nanoTime();
            print("Indirect Allocation cost for buffer of size: "
                    + size + " is " + (end - start));
            start = System.nanoTime();
            execute();
            end = System.nanoTime();
            print("Execution cost using indirect buffer: " + (end - start));
            print();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void directAllocate() {
        buffer = ByteBuffer.allocateDirect(size);
    }

    public void indirectAllocate() {
        buffer = ByteBuffer.allocate(size);
    }

    public abstract void execute() throws IOException;
}

public class E25_AllocateDirectTest {
    public static void main(String[] args) {
        CompareAllocations[] comparisons = {
                new CompareAllocations("GetChannel", 8 * 1024) {
                    @Override
                    public void execute() throws IOException {
                        FileChannel fc = new FileInputStream(
                                "src/exercises/chapter18/E25_AllocateDirectTest.java")
                                .getChannel();
                        fc.read(buffer);
                        buffer.flip();
                        while (buffer.hasRemaining())
                            buffer.get();
                    }
                },
                new CompareAllocations("ChannelCopy", 16 * 1024) {
                    @Override
                    public void execute() throws IOException {
                        FileChannel
                                in = new FileInputStream(
                                        "src/exercises/chapter18/E25_AllocateDirectTest.java").getChannel(),
                                out = new FileOutputStream(
                                        "src/exercises/chapter18/E25_AllocateDirectTest2.txt").getChannel();
                        while (in.read(buffer) != -1) {
                            buffer.flip(); // Prepare for writing
                            out.write(buffer);
                            buffer.clear(); // Prepare for reading
                        }
                    }
                },
                new CompareAllocations("BufferToText", 8 * 1024) {
                    @Override
                    public void execute() throws IOException {
                        FileChannel fc = new FileOutputStream(
                                "src/exercises/chapter18/E25_data2.txt")
                                .getChannel();
                        fc.write(ByteBuffer.wrap("Some text".getBytes()));
                        fc.close();
                        fc = new FileInputStream(
                                "src/exercises/chapter18/E25_data2.txt")
                                .getChannel();
                        fc.read(buffer);
                        buffer.flip();
                        buffer.asCharBuffer();
                        // Decode using this system's default Charset:
                        buffer.rewind();
                        Charset.forName(
                                System.getProperty("file.encoding"))
                                .decode(buffer);
                        fc = new FileOutputStream(
                                "src/exercises/chapter18/E25_data2.txt")
                                .getChannel();
                        fc.write(ByteBuffer.wrap("Some text".getBytes("UTF-16BE")));
                        fc.close();
                        // Now try reading again:
                        fc = new FileInputStream(
                                "src/exercises/chapter18/E25_data2.txt")
                                .getChannel();
                        buffer.clear();
                        fc.read(buffer);
                        buffer.flip();
                        buffer.asCharBuffer();
                        // Use a CharBuffer to write through:
                        fc = new FileOutputStream(
                                "src/exercises/chapter18/E25_data2.txt")
                                .getChannel();
                        buffer.clear();
                        buffer.asCharBuffer().put("Some text");
                        fc.write(buffer);
                        fc.close();
                        // Read and display:
                        fc = new FileInputStream(
                                "src/exercises/chapter18/E25_data2.txt")
                                .getChannel();
                        buffer.clear();
                        fc.read(buffer);
                        buffer.flip();
                        buffer.asCharBuffer();
                    }
                },
                new CompareAllocations("GetData", 1024) {
                    @Override
                    public void execute() throws IOException {
                        // Store and read a char array:
                        buffer.asCharBuffer().put("Howdy!");
                        // Store and read a short:
                        buffer.asShortBuffer().put((short) 471142);
                        buffer.getShort();
                        buffer.rewind();
                        // Store and read an int:
                        buffer.asIntBuffer().put(99471142);
                        buffer.getInt();
                        buffer.rewind();
                        // Store and read a long:
                        buffer.asLongBuffer().put(99471142);
                        buffer.getLong();
                        buffer.rewind();
                        // Store and read a float:
                        buffer.asFloatBuffer().put(99471142);
                        buffer.getFloat();
                        buffer.rewind();
                        // Store and read a double:
                        buffer.asDoubleBuffer().put(99471142);
                        buffer.getDouble();
                        buffer.rewind();
                    }
                },
                new CompareAllocations("IntBufferDemo", 1024) {
                    @Override
                    public void execute() throws IOException {
                        IntBuffer ib = buffer.asIntBuffer();
                        // Store an array of int:
                        ib.put(new int[]{ 11, 42, 47, 99, 143, 811, 1016 });
                        // Absolute location read and write:
                        ib.get(3);
                        ib.put(3, 1811);
                        // Setting a new limit before rewinding the buffer.
                        ib.flip();
                        while (ib.hasRemaining()) {
                            int i = ib.get();
                        }
                    }
                },
                new CompareAllocations("UsingBuffers", 32) {
                    @Override
                    public void execute() throws IOException {
                        char[] data = "UsingBuffers".toCharArray();
                        CharBuffer cb = buffer.asCharBuffer();
                        cb.put(data);
                        cb.rewind();
                        symmetricScramble(cb);
                        cb.rewind();
                        symmetricScramble(cb);
                        cb.rewind();
                    }

                    private void symmetricScramble(CharBuffer buffer) {
                        while (buffer.hasRemaining()) {
                            buffer.mark();
                            char c1 = buffer.get();
                            char c2 = buffer.get();
                            buffer.reset();
                            buffer.put(c2).put(c1);
                        }
                    }
                }
        };

        for (CompareAllocations comparison : comparisons) {
            comparison.runComparison();
        }
    }
}
/* Output:
Program name: "GetChannel"
Direct Allocation cost for buffer of size: 8192 is 114917
Execution cost using direct buffer: 2292083
Indirect Allocation cost for buffer of size: 8192 is 5000
Execution cost using indirect buffer: 469542

Program name: "ChannelCopy"
Direct Allocation cost for buffer of size: 16384 is 11666
Execution cost using direct buffer: 515458
Indirect Allocation cost for buffer of size: 16384 is 4417
Execution cost using indirect buffer: 112417

Program name: "BufferToText"
Direct Allocation cost for buffer of size: 8192 is 4375
Execution cost using direct buffer: 1530208
Indirect Allocation cost for buffer of size: 8192 is 4667
Execution cost using indirect buffer: 390833

Program name: "GetData"
Direct Allocation cost for buffer of size: 1024 is 9083
Execution cost using direct buffer: 636333
Indirect Allocation cost for buffer of size: 1024 is 1292
Execution cost using indirect buffer: 201750

Program name: "IntBufferDemo"
Direct Allocation cost for buffer of size: 1024 is 6042
Execution cost using direct buffer: 24958
Indirect Allocation cost for buffer of size: 1024 is 1083
Execution cost using indirect buffer: 11000

Program name: "UsingBuffers"
Direct Allocation cost for buffer of size: 32 is 4208
Execution cost using direct buffer: 40750
Indirect Allocation cost for buffer of size: 32 is 1333
Execution cost using indirect buffer: 27500
 */