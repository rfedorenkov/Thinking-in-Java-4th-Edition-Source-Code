package exercises.chapter7;

import reusing.Detergent;

import static net.mindview.util.Print.print;

/**
 * Exercise 2
 * Inherit a new class from class Detergent.
 * Override scrub() and add a new method called sterilize().
 */
class Sterilizer extends Detergent {
    @Override
    public void scrub() {
        append(" Sterilizer.scrub()");
        super.scrub();
    }

    public void sterilize() {
        append(" sterilize()");
    }
}

public class E02_Inheritance {
    public static void main(String[] args) {
        Sterilizer x = new Sterilizer();
        x.dilute();
        x.apply();
        x.scrub();
        x.foam();
        x.sterilize();
        print(x);
        print("Testing a base class:");
        Detergent.main(args);
    }
}
/* Output:
Cleanser dilute() apply() Sterilizer.scrub() Detergent.scrub() scrub() foam() sterilize()
Testing a base class:
Cleanser dilute() apply() Detergent.scrub() scrub() foam()
Testing base class:
Cleanser dilute() apply() scrub()
 */