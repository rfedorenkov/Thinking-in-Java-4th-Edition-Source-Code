package exercises.chapter8;

import polymorphism.music.Note;

import static net.mindview.util.Print.print;

/**
 * Exercise 7
 * Add a new type of Instrument to Music3.java and verify
 * that polymorphism works for you new type.
 */
class Piano extends Instrument {
    @Override
    void play(Note n) {
        print("Piano.play() " + n);
    }

    @Override
    public String toString() {
        return "Piano";
    }

    @Override
    void adjust() {
        print("Adjusting Piano");
    }
}

public class E07_NewInstrument {
    public static void printAll(Instrument[] e) {
        for (Instrument i : e) {
            System.out.println(i);
            i.adjust();
            i.play(Note.MIDDLE_C);
        }
    }

    public static void main(String[] args) {
        // Upcasting during addition to the array:
        Instrument[] orchestra = {
                new Wind(),
                new Percussion(),
                new Stringed(),
                new Brass(),
                new Woodwind(),
                new Piano()
        };
        printAll(orchestra);
    }
}
/* Output:
Wind
Adjusting Wind
Wind.play() MIDDLE_C
Percussion
Adjusting Percussion
Percussion.play() MIDDLE_C
Stringed
Adjusting Stringed
Stringed.play() MIDDLE_C
Wind
Adjusting Brass
Brass.play() MIDDLE_C
Woodwind
Adjusting Wind
Woodwind.play() MIDDLE_C
Piano
Adjusting Piano
Piano.play() MIDDLE_C
 */