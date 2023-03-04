package io;

import java.io.*;
import java.nio.IntBuffer;
import java.nio.channels.FileChannel;

public class MappedIO {
    private static int numOfInts = 4000000;
    private static int numOfUbuffInts = 200000;

    private abstract static class Tester {
        private String name;

        public Tester(String name) {
            this.name = name;
        }

        public void runTest() {
            System.out.print(name + ": ");
            try {
                long start = System.nanoTime();
                test();
                double duration = System.nanoTime() - start;
                System.out.printf("%.2f\n", duration / 1.0e9);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        public abstract void test() throws IOException;
    }

    private static Tester[] tests = {
            new Tester("Stream Write") {
                @Override
                public void test() throws IOException {
                    DataOutputStream dos = new DataOutputStream(
                            new BufferedOutputStream(
                                    new FileOutputStream("src/io/temp.tmp")));
                    for (int i = 0; i < numOfInts; i++)
                        dos.writeInt(i);
                    dos.close();
                }
            },
            new Tester("Mapped Write") {
                @Override
                public void test() throws IOException {
                    FileChannel fc = new RandomAccessFile("src/io/temp.tmp", "rw")
                            .getChannel();
                    IntBuffer ib = fc.map(
                            FileChannel.MapMode.READ_WRITE, 0, fc.size())
                            .asIntBuffer();
                    for (int i = 0; i < numOfInts; i++)
                        ib.put(i);
                    fc.close();
                }
            },
            new Tester("Stream Read") {
                @Override
                public void test() throws IOException {
                    DataInputStream dis = new DataInputStream(
                            new BufferedInputStream(
                                    new FileInputStream("src/io/temp.tmp")));
                    for (int i = 0; i < numOfInts; i++)
                        dis.readInt();
                    dis.close();
                }
            },
            new Tester("Mapped Read") {
                @Override
                public void test() throws IOException {
                    FileChannel fc = new FileInputStream(
                            "src/io/temp.tmp")
                            .getChannel();
                    IntBuffer ib = fc.map(
                            FileChannel.MapMode.READ_ONLY, 0, fc.size())
                            .asIntBuffer();
                    while (ib.hasRemaining())
                        ib.get();
                    fc.close();
                }
            },
            new Tester("Stream Read/Write") {
                @Override
                public void test() throws IOException {
                    RandomAccessFile raf = new RandomAccessFile(
                            new File("src/io/temp.tmp"), "rw");
                    raf.writeInt(1);
                    for (int i = 0; i < numOfUbuffInts; i++) {
                        raf.seek(raf.length() - 4);
                        raf.writeInt(raf.readInt());
                    }
                    raf.close();
                }
            },
            new Tester("Mapped Read/Write") {
                @Override
                public void test() throws IOException {
                    FileChannel fc = new RandomAccessFile(
                            new File("src/io/temp.tmp"), "rw")
                            .getChannel();
                    IntBuffer ib = fc.map(
                            FileChannel.MapMode.READ_WRITE, 0, fc.size())
                            .asIntBuffer();
                    ib.put(0);
                    for (int i = 1; i < numOfUbuffInts; i++)
                        ib.put(ib.get(i - 1));
                    fc.close();
                }
            }
    };

    public static void main(String[] args) {
        for (Tester test : tests)
            test.runTest();
    }
}
/* Output:
Stream Write: 0.045
Mapped Write: 0.015
Stream Read: 0.149
Mapped Read: 0.012
Stream Read/Write: 2.640
Mapped Read/Write: 0.005
 */