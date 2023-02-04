package exercises.chapter13;

import java.util.Formatter;

/**
 * Exercise 4
 * Modify Receipt.java so that the widths are all
 * controlled by a single set of constant values.
 * The goal is to allow you to easily change a width
 * by changing a single value is one place.
 */
class Receipt {
    private static final int ITEM_WIDTH = 15;
    private static final int QTY_WIDTH = 5;
    private static final int PRICE_WIDTH = 10;
    private static final String TITLE_FORMAT = // %-15s %5s %10s
            "%-" + ITEM_WIDTH + "s %" + QTY_WIDTH + "s %" + PRICE_WIDTH + "s\n";
    private static final String PRINT_FORMAT = // %-15.15s %5d %10.2f
            "%-" + ITEM_WIDTH + "." + ITEM_WIDTH + "s %" + QTY_WIDTH + "d %" + PRICE_WIDTH + ".2f\n";
    private static final String TOTAL_FORMAT = // %-15s %5s %10.2f
            "%-" + ITEM_WIDTH + "s %" + QTY_WIDTH + "s %" + PRICE_WIDTH + ".2f\n";

    private double total = 0;
    private Formatter f = new Formatter(System.out);

    public void printTitle() {
        f.format(TITLE_FORMAT, "Item", "Qty", "Price");
        f.format(TITLE_FORMAT, "----", "---", "-----");
    }

    public void print(String name, int qty, double price) {
        f.format(PRINT_FORMAT, name, qty, price);
        total += price;
    }

    public void printTotal() {
        f.format(TOTAL_FORMAT, "Tax", "", total * 0.06);
        f.format(TITLE_FORMAT, "", "", "-----");
        f.format(TOTAL_FORMAT, "Total", "", total * 1.06);
    }
}

public class E04_CustomizableReceiptTest {
    public static void main(String[] args) {
        Receipt receipt = new Receipt();
        receipt.printTitle();
        receipt.print("Jack's Magic Beans", 4, 4.25);
        receipt.print("Princess Peas", 3, 5.1);
        receipt.print("Three Bears Porridge", 1, 14.29);
        receipt.printTotal();
    }
}
/* Output:
Item              Qty      Price
----              ---      -----
Jack's Magic Be     4       4.25
Princess Peas       3       5.10
Three Bears Por     1      14.29
Tax                         1.42
                           -----
Total                      25.06
 */