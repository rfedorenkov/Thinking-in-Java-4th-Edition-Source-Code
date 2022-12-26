package exercises.chapter9;

/**
 * Exercise 3
 * Create a base class with an abstract print() method
 * that it overridden in derived class. The overridden
 * version of the method prints the value of an int variable
 * defined in the derived class. Define this variable with a
 * nonzero value. Call print() in the base-class constructor.
 * Create an object of the derived type in main(), then call its
 * print() method. Explain the results.
 */
abstract class Printer {
    public Printer() {
        System.out.println("Printer()");
        print();
    }
    abstract void print();
}

class LaserPrinter extends Printer {
    private int i = 1;

    public LaserPrinter() {
        System.out.println("LaserPrinter()");
    }

    @Override
    void print() {
        System.out.println("LaserPrinter.print() i = " + i);
    }
}

public class E03_AbstractPrintMethodTest {
    public static void main(String[] args) {
        Printer a = new LaserPrinter();
        a.print();
    }
}
/* Output:
Printer()
LaserPrinter.print() i = 0
LaserPrinter()
LaserPrinter.print() i = 1
 */