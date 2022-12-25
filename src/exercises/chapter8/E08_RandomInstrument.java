package exercises.chapter8;

import polymorphism.music.Note;

import java.util.Random;

/**
 * Exercise 8
 * Modify Music3.java so it randomly creates
 * Instrument objects the way Shapes.java does.
 */
class RandomInstrumentGenerator {
    private Random random = new Random(47);

    public Instrument next() {
        switch (random.nextInt(6)) {
            default:
            case 0: return new Wind();
            case 1: return new Percussion();
            case 2: return new Stringed();
            case 3: return new Brass();
            case 4: return new Woodwind();
            case 5: return new Piano();
        }
    }
}
public class E08_RandomInstrument {
    private static RandomInstrumentGenerator gen = new RandomInstrumentGenerator();

    public static void main(String[] args) {
        Instrument[] orchestra = new Instrument[9];
        for (int i = 0; i < orchestra.length; i++)
            orchestra[i] = gen.next();
        for (Instrument i : orchestra)
            i.play(Note.MIDDLE_C);

    }
}
/* Output:
Stringed.play() MIDDLE_C
Piano.play() MIDDLE_C
Percussion.play() MIDDLE_C
Piano.play() MIDDLE_C
Percussion.play() MIDDLE_C
Piano.play() MIDDLE_C
Woodwind.play() MIDDLE_C
Stringed.play() MIDDLE_C
Wind.play() MIDDLE_C
 */