package exercises.chapter7;

import static net.mindview.util.Print.print;

/**
 * Exercise 11
 * Modify Detergent.java so it uses delegation.
 */
class Cleanser {
    private String s = "Cleanser";

    public void append(String a) {
        s += a;
    }

    public void dilute() {
        append(" dilute()");
    }

    public void apply() {
        append(" apply()");
    }

    public void scrub() {
        append(" scrub()");
    }

    @Override
    public String toString() {
        return s;
    }

    public static void main(String[] args) {
        Cleanser x = new Cleanser();
        x.dilute(); x.apply(); x.scrub();
        print(x);
    }
}

class Detergent {
    private String s = "Detergent";
    private Cleanser cleanser = new Cleanser();

    public void append(String s) {
        cleanser.append(s);
    }

    public void dilute() {
        cleanser.dilute();
    }

    public void apply() {
        cleanser.apply();
    }

    public void scrub() {
        append(" Detergent.scrub()");
        cleanser.scrub();
    }

    public void foam() {
        append(" foam()");
    }

    @Override
    public String toString() {
        return s + " (" + cleanser.toString() + ")";
    }
}

public class E11_Delegation {
    public static void main(String[] args) {
        Detergent x = new Detergent();
        x.dilute();
        x.apply();
        x.scrub();
        x.foam();
        print(x);
        print("Testing base class:");
        Cleanser.main(args);
    }
}
/* Output:
Detergent (Cleanser dilute() apply() Detergent.scrub() scrub() foam())
Testing base class:
Cleanser dilute() apply() scrub()
 */