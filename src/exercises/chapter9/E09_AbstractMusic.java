package exercises.chapter9;

import polymorphism.music.Note;

import static net.mindview.util.Print.print;

/**
 * Exercise 9
 * Refactor Music5.java by moving the common methods in Wind,
 * Percussion and Stringed into an abstract class.
 */
abstract class Instrument {
    // Compile-time constant:
    int VALUE = 5; // static & final

    // Cannot have method definitions:
    public void play(Note n) {
        print(this + ".play() " + n);
    }
    @Override
    abstract public String toString();
    public void adjust() {
        print(this + ".adjust()");
    }
}

class Wind extends Instrument {

    @Override
    public String toString() {
        return "Wind";
    }
}

class Percussion extends Instrument {

    @Override
    public String toString() {
        return "Percussion";
    }
}

class Stringed extends Instrument {

    @Override
    public String toString() {
        return "Stringed";
    }
}

class Brass extends Wind {
    @Override
    public String toString() {
        return "Brass";
    }
}

class Woodwind extends Wind {
    @Override
    public String toString() {
        return "Woodwind";
    }
}

public class E09_AbstractMusic {
    // Doesn't care about type, so new types
    // added to the system still work right:
    static void tune(Instrument i) {
        // ...
        i.play(Note.MIDDLE_C);
    }

    static void tuneAll(Instrument[] e) {
        for (Instrument i : e)
            tune(i);
    }

    public static void main(String[] args) {
        // Upcasting during addition to the array:
        Instrument[] orchestra = {
                new Wind(),
                new Percussion(),
                new Stringed(),
                new Woodwind()
        };
        tuneAll(orchestra);
    }
}
/* Output:
Wind.play() MIDDLE_C
Percussion.play() MIDDLE_C
Stringed.play() MIDDLE_C
Woodwind.play() MIDDLE_C
 */