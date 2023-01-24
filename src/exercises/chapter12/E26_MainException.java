package exercises.chapter12;

import java.io.FileInputStream;

/**
 * Exercise 26
 * Change the file name string in MainException.java to name a file
 * that doesn't exist. Run the program and note the result.
 * {ThrowsException}
 */
public class E26_MainException {
    // Pass all exceptions to the console:
    public static void main(String[] args) throws Exception {
        // Open the file:
        FileInputStream file =
                new FileInputStream("FakeFile");
        // Use the file ...
        // Close the file:
        file.close();
    }
}
/* Output:
Exception in thread "main" java.io.FileNotFoundException: FakeFile (No such file or directory)
	at java.base/java.io.FileInputStream.open0(Native Method)
	at java.base/java.io.FileInputStream.open(FileInputStream.java:213)
	at java.base/java.io.FileInputStream.<init>(FileInputStream.java:155)
	at java.base/java.io.FileInputStream.<init>(FileInputStream.java:110)
	at exercises.chapter12.E26_MainException.main(E26_MainException.java:14)
 */