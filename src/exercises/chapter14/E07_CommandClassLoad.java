package exercises.chapter14;

import static net.mindview.util.Print.print;

/**
 * Exercise 7
 * Modify SweetShop.java so that each type of object
 * creation is controlled by a command-line argument.
 * That is, if your command line is "java SweetShop Candy",
 * then only the Candy object is created. Notice how
 * you can control which Class objects are loaded via
 * the command-line argument.
 * {Args: exercises.chapter14.Gum exercises.chapter14.Cookie}
 */
class Candy {
    static {
        print("Loading Candy");
    }
}

class Gum {
    static {
        print("Loading Gum");
    }
}

class Cookie {
    static {
        print("Loading Cookie");
    }
}

public class E07_CommandClassLoad {
    public static void main(String[] args) {
        if (args.length < 1) {
            print("Usage: java E07_CommandClassLoad exercises.chapter14.Gum");
            System.exit(0);
        }

        for (String s : args) {
            try {
                Class.forName(s);
            } catch (ClassNotFoundException e) {
                print("Can't find class: " + s);
                System.exit(1);
            }
        }
    }
}
/* Output:
Loading Gum
Loading Cookie
 */