package exercises.chapter9;

import polymorphism.music.Note;

import static net.mindview.util.Print.print;

/**
 * Exercise 10
 * Add a Playable to Modify Music5.java, and move the play()
 * declaration from Instrument to Playable. Include Playable
 * in the implements list to add it to the derived classes.
 * Change tune() so it takes a Playable instead of an Instrument.
 */
interface Instrument2 {
    // Compile-time constant:
    int VALUE = 5; // static & final

    // Cannot have method definitions:
    void adjust();
}

interface Playable {
    void play(Note n);
}

class Wind2 implements Instrument2, Playable {

    @Override
    public void play(Note n) {
        print(this + ".play() " + n);
    }

    @Override
    public String toString() {
        return "Wind";
    }

    @Override
    public void adjust() {
        print(this + ".adjust()");
    }
}

class Percussion2 implements Instrument2, Playable {

    @Override
    public void play(Note n) {
        print(this + ".play() " + n);
    }

    @Override
    public String toString() {
        return "Percussion";
    }

    @Override
    public void adjust() {
        print(this + ".adjust()");
    }
}

class Stringed2 implements Instrument2, Playable {

    @Override
    public void play(Note n) {
        print(this + ".play() " + n);
    }

    @Override
    public String toString() {
        return "Stringed";
    }

    @Override
    public void adjust() {
        print(this + ".adjust()");
    }
}

class Brass2 extends Wind2 {
    @Override
    public String toString() {
        return "Brass";
    }
}

class Woodwind2 extends Wind2 {
    @Override
    public String toString() {
        return "Woodwind";
    }
}

public class E10_PlayableMusic {
    // Doesn't care about type, so new types
    // added to the system still work right:
    static void tune(Playable p) {
        // ...
        p.play(Note.MIDDLE_C);
    }

    static void tuneAll(Playable[] e) {
        for (Playable p : e)
            tune(p);
    }

    public static void main(String[] args) {
        // Upcasting during addition to the array:
        Playable[] orchestra = {
                new Wind2(),
                new Percussion2(),
                new Stringed2(),
                new Woodwind2()
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