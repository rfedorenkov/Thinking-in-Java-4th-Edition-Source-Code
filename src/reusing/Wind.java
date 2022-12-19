package reusing;

/**
 * Inheritance & upcasting.
 */
class Instrument {
    public void play() {
    }

    static void tune(Instrument i) {
        // ...
        i.play();
    }
}

// Wind object are instruments because they have the same interface:
public class Wind extends Instrument {
    public static void main(String[] args) {
        Wind flute = new Wind();
        Instrument.tune(flute); // Upcasting
    }
}