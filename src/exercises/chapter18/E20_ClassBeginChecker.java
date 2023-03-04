package exercises.chapter18;

import net.mindview.util.BinaryFile;
import net.mindview.util.Directory;

import java.io.File;
import java.io.IOException;

import static net.mindview.util.Print.print;
import static net.mindview.util.Print.printnb;

/**
 * Exercise 20
 * Using Directory.walk() and BinaryFile, verify that
 * all .class files in a directory tree begin with
 * the hex characters 'CAFEBABE'
 */
public class E20_ClassBeginChecker {
    public static void main(String[] args) throws IOException {
        String startDirectory = "out/classes/exercises/chapter18/";
        for (File file : Directory.walk(startDirectory, ".*\\.class").files) {
            printnb(file.getName() + ": ");
            byte[] bytes = BinaryFile.read(file);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 4; i++)
                sb.append(Integer.toHexString(bytes[i] & 0xff).toUpperCase());
            if (!sb.toString().equals("CAFEBABE"))
                throw new RuntimeException(file + " is corrupt");
            printnb(sb.toString());
            print();
        }
    }
}
// TODO