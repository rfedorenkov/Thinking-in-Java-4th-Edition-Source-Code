package exercises.chapter9;

import java.nio.CharBuffer;
import java.util.Random;
import java.util.Scanner;

/**
 * Exercise 16
 * Create a class that produces a sequence of chars.
 * Adapt this class so that it can be an input to a Scanner object.
 */
class RandomChars {
    private static Random rand = new Random(47);
    private static final char[] capitals = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
    private static final char[] lowers = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    private static final char[] vowels = "aeiou".toCharArray();

    public char[] generate() {
        char[] result = new char[10];
        int j = 0;
        result[j++] = capitals[rand.nextInt(capitals.length)];
        for (int i = 0; i < 4; i++) {
            result[j++] = vowels[rand.nextInt(vowels.length)];
            result[j++] = lowers[rand.nextInt(lowers.length)];
        }
        result[j] = ' ';
        return result;
    }
}

class AdapterRandomChars extends RandomChars implements Readable {
    private int count ;

    public AdapterRandomChars(int count) {
        this.count = count;
    }

    @Override
    public int read(CharBuffer cb) {
        if (count-- == 0)
            return -1;
        char[] result = generate();
        cb.put(result);
        return result.length;
    }
}

public class E16_CharacterGenerator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(new AdapterRandomChars(10));
        while (scanner.hasNext())
            System.out.println(scanner.next());
    }
}
/* Output:
Yazeruyac
Fowenucor
Goeazimom
Raeuuacio
Nuoadesiw
Hageaikux
Ruqicibui
Numasetih
Kuuuuozog
Waqizeyoy
 */