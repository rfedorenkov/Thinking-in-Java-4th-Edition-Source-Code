package exercises.chapter14;

import polymorphism.music.Note;

import static net.mindview.util.Print.print;

/**
 * Exercise 26
 * Implement clearSpitValve() as described in the summary.
 */
interface Instrument {
    void play();
    String what();
    void adjust();
    void prepareInstrument();
}

class Wind implements Instrument {
    @Override
    public void play() {
        print("Wind.play()");
    }

    @Override
    public String what() {
        return "Wind";
    }

    @Override
    public void adjust() {
        print("Adjusting Wind");
    }

    @Override
    public void prepareInstrument() {
        clearSpitValve();
    }

    public void clearSpitValve() {
        print("Wind.clearSpitValve");
    }
}

class Percussion implements Instrument {
    @Override
    public void play() {
        print("Percussion.play()");
    }

    @Override
    public String what() {
        return "Percussion";
    }

    @Override
    public void adjust() {
        print("Adjusting Percussion");
    }

    @Override
    public void prepareInstrument() {
        print("Percussion.prepareInstrument");
    }
}

class Stringed implements Instrument {
    @Override
    public void play() {
        print("Stringed.play()");
    }

    @Override
    public String what() {
        return "Stringed";
    }

    @Override
    public void adjust() {
        print("Adjusting Stringed");
    }

    @Override
    public void prepareInstrument() {
        print("Stringed.prepareInstrument");
    }
}

class Brass extends Wind {
    @Override
    public void play() {
        print("Brass.play()");
    }

    @Override
    public void adjust() {
        print("Adjusting Brass");
    }

    @Override
    public void clearSpitValve() {
        print("Brass.clearSpitValve");
    }
}

class Woodwind extends Wind {
    @Override
    public void play() {
        print("Woodwind.play()");
    }

    @Override
    public String what() {
        return "Woodwind";
    }

    @Override
    public void clearSpitValve() {
        print("Woodwind.clearSpitValve");
    }
}

class Music {
    public static void tune(Instrument i) {
        i.play();
    }

    public static void tuneAll(Instrument[] e) {
        for (Instrument i : e)
            tune(i);
    }

    public static void prepareAll(Instrument[] e) {
        for (Instrument i : e)
            i.prepareInstrument();
    }
}

public class E26_ClearSpitValve {
    public static void main(String[] args) {
        // Upcasting during addition to the array:
        Instrument[] orchestra = {
                new Wind(),
                new Percussion(),
                new Stringed(),
                new Brass(),
                new Woodwind()
        };
        Music.prepareAll(orchestra);
        Music.tuneAll(orchestra);
    }
}
/* Output:
Wind.clearSpitValve
Percussion.prepareInstrument
Stringed.prepareInstrument
Brass.clearSpitValve
Woodwind.clearSpitValve
Wind.play()
Percussion.play()
Stringed.play()
Brass.play()
Woodwind.play()
 */