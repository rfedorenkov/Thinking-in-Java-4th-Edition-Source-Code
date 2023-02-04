package exercises.chapter13;

import java.util.Scanner;

/**
 * Exercise 20
 * Create a class that contains int, long, float and double
 * and String fields. Create a constructor for this class
 * that takes a single String argument, and scans that
 * string into the various fields. Add a toString() method
 * and demonstrate that your class works correctly.
 */
class Data {
    private int i;
    private long l;
    private float f;
    private double d;
    private String s;

    public Data(String data) {
        Scanner scanner = new Scanner(data);
        i = scanner.nextInt();
        l = scanner.nextLong();
        f = scanner.nextFloat();
        d = scanner.nextDouble();
        s = scanner.next();
    }

    @Override
    public String toString() {
        return String.format(
                "i = %d, l = %d, f = %f, d = %f, s = %s",
                i, l, f, d, s);
    }
}

public class E20_ScannerDataTest {
    public static void main(String[] args) {
        Data data = new Data("1 10 15.5 47.7 SecretData");
        System.out.println(data);
    }
}
/* Output:
i = 1, l = 10, f = 15.500000, d = 47.700000, s = SecretData
 */