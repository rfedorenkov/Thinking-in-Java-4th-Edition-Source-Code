package io;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Testing for end of file while reading a byte at a time.
 */
public class TestEOF {
    public static void main(String[] args) throws IOException {
        DataInputStream in = new DataInputStream(
                new BufferedInputStream(
                        new FileInputStream("./src/io/TestEOF.java")));
        while (in.available() != 0)
            System.out.print((char) in.readByte());
    }
}
/* (Execute to see output) */