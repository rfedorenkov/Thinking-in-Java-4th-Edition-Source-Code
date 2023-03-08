package exercises.chapter19;

import net.mindview.util.Generator;
import net.mindview.util.TextFile;

import java.util.*;

import static exercises.chapter19.Input2.*;
import static net.mindview.util.Print.print;

/**
 * Exercise 11
 * In a real vending machine you will want to easily add
 * change the type of vended items, so the limits imposed
 * by an enum on Input are impractical (remember that enums
 * are for a restricted set of types). Modify VendingMachine.java
 * so that the vended items are represented by a class instead
 * of being part of Input, and initialize an ArrayList of these
 * objects from a text file (using net.mindview.util.TextFile).
 * {Args: E11_VendedItems.txt E11_VendingMachineInput.txt}
 */
class Product {
    private static ArrayList<Product> products = new ArrayList<>();
    private static Random rand = new Random(47);
    private String name;
    private int amount;

    public Product(String name, int amount) {
        this.name = name;
        this.amount = amount;
    }

    public static Product parse(String data) {
        String[] arr = data.trim().split(" ");
        return new Product(arr[0], Integer.parseInt(arr[1]));
    }

    public int amount() {
        return amount;
    }

    public String name() {
        return name;
    }

    public static void addProduct(Product product) {
        products.add(product);
    }

    public static Product search(String name) {
        for (Product item : products)
            if (item.name.equals(name))
                return item;
        return null;
    }

    public static Product randomSelection() {
        // Don't include STOP:
        return products.get(rand.nextInt(products.size()));
    }
}

class ProductInput {
    Input2 input;
    Product product;

    public ProductInput(Input2 input, Product product) {
        this.input = input;
        this.product = product;
    }

    public int amount() {
        return product == null ? input.amount() : product.amount();
    }

    public Input2 getInput() {
        return input;
    }

    @Override
    public String toString() {
        return product == null ? input.name() : product.name();
    }
}

enum Input2 {
    NICKEL(5), DIME(10), QUARTER(25), DOLLAR(100),
    PRODUCT,
    ABORT_TRANSACTION {
        @Override
        public int amount() { // Disallow
            throw new RuntimeException("ABORT.amount()");

        }
    },
    STOP {
        @Override
        public int amount() { // Disallow
            throw new RuntimeException("SHUT_DOWN.amount()");
        }
    };

    int value; // In cents

    Input2(int value) {
        this.value = value;
    }

    Input2() {
    }

    public int amount() {
        return value;
    }

    static Random rand = new Random(47);

    public static Input2 randomSelection() {
        // Don't include STOP:
        return values()[rand.nextInt(values().length - 1)];
    }
}

enum Category2 {
    MONEY(NICKEL, DIME, QUARTER, DOLLAR),
    ITEM_SELECTION(PRODUCT),
    QUIT_TRANSACTION(ABORT_TRANSACTION),
    SHUT_DOWN(STOP);

    private Input2[] values;

    Category2(Input2... types) {
        values = types;
    }

    private static EnumMap<Input2, Category2> categories = new EnumMap<>(Input2.class);

    static {
        for (Category2 c : Category2.class.getEnumConstants())
            for (Input2 type : c.values)
                categories.put(type, c);
    }

    public static Category2 categorize(Input2 input) {
        return categories.get(input);
    }
}

public class E11_VendingMachine3 {
    private static State state = State.RESTING;
    private static int amount = 0;
    private static ProductInput selection = null;

    enum StateDuration { // Tagging enum
        TRANSIENT
    }

    enum State {
        RESTING {
            @Override
            void next(ProductInput productInput) {
                switch (Category2.categorize(productInput.getInput())) {
                    case MONEY:
                        amount += productInput.amount();
                        state = ADDING_MONEY;
                        break;
                    case SHUT_DOWN:
                        state = TERMINAL;
                    default:
                }
            }
        },
        ADDING_MONEY {
            @Override
            void next(ProductInput productInput) {
                switch (Category2.categorize(productInput.getInput())) {
                    case MONEY:
                        amount += productInput.amount();
                        break;
                    case ITEM_SELECTION:
                        selection = productInput;
                        if (amount < selection.amount())
                            print("Insufficient money for " + selection);
                        else
                            state = DISPENSING;
                        break;
                    case QUIT_TRANSACTION:
                        state = GIVING_CHANGE;
                        break;
                    case SHUT_DOWN:
                        state = TERMINAL;
                    default:
                }
            }
        },
        DISPENSING(StateDuration.TRANSIENT) {
            @Override
            void next() {
                print("here is your " + selection);
                amount -= selection.amount();
                state = GIVING_CHANGE;
            }
        },
        GIVING_CHANGE(StateDuration.TRANSIENT) {
            @Override
            void next() {
                if (amount > 0) {
                    print("Your change: " + amount);
                    amount = 0;
                }
                state = RESTING;
            }
        },
        TERMINAL {
            @Override
            void output() {
                print("Halted");
            }
        };

        private boolean isTransient = false;

        State() {
        }

        State(StateDuration trans) {
            isTransient = true;
        }

        void next(ProductInput productInput) {
            throw new RuntimeException("Only call " +
                    "next(ProductInput productInput) for non-transient states");
        }
        void next() {
            throw new RuntimeException("Only call next() for " +
                    "StateDuration.TRANSIENT states");
        }
        void output() {
            print(amount);
        }
    }

    static void run(Generator<ProductInput> gen) {
        while (state != State.TERMINAL) {
            state.next(gen.next());
            while (state.isTransient)
                state.next();
            state.output();
        }
    }

    public static void main(String[] args) {
        Generator<ProductInput> gen;
        String productFile;
        if (args.length == 2) {
            gen = new FileProductInputGenerator2(args[1]);
            productFile = args[0];
        } else {
            gen = new RandomProductInputGenerator2();
            productFile = "src/exercises/chapter19/E11_VendedItems.txt";
        }

        for (String data : new TextFile(productFile, ";"))
            Product.addProduct(Product.parse(data));
        run(gen);
    }
}

// Create Inputs from a file of ';' - separated strings:
class RandomProductInputGenerator2 implements Generator<ProductInput> {
    @Override
    public ProductInput next() {
        return new ProductInput(Input2.randomSelection(), Product.randomSelection());
    }
}

// Create Inputs from a file of ';' - separated strings:
class FileProductInputGenerator2 implements Generator<ProductInput> {
    private Iterator<String> input;

    public FileProductInputGenerator2(String fileName) {
        input = new TextFile(fileName, ";").iterator();
    }

    @Override
    public ProductInput next() {
        if (!input.hasNext())
            return null;
        String s = input.next().trim();
        try {
            return new ProductInput(Enum.valueOf(Input2.class, s), null);
        } catch (IllegalArgumentException e) {
            Product item = Product.search(s);
            if (item != null)
                return new ProductInput(PRODUCT, item);
            throw e;
        }

    }
}
/* Output:
25
50
75
here is your CHIPS
0
100
200
here is your TOOTHPASTE
0
25
35
Your change: 35
0
25
35
Insufficient money for SODA
35
60
70
75
Insufficient money for SODA
75
Your change: 75
0
Halted
 */