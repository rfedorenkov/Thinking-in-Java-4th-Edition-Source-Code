package exercises.chapter2;

/**
 * Exercise 5
 * Modify the previous exercise so that
 * the values of the data in DataOnly are
 * assigned to and printed in main().
 * into a program that compiles and runs
 */
public class E05_DataOnlyTest {
    public static void main(String[] args) {
        DataOnly dataOnly = new DataOnly();
        dataOnly.i = 47;
        dataOnly.d = 1.1;
        dataOnly.b = false;

        System.out.println("dataOnly.i = " + dataOnly.i);
        System.out.println("dataOnly.d = " + dataOnly.d);
        System.out.println("dataOnly.b = " + dataOnly.b);
    }

    static class DataOnly {
        int i;
        double d;
        boolean b;
    }
}
/* Output:
dataOnly.i = 47
dataOnly.d = 1.1
dataOnly.b = false
 */