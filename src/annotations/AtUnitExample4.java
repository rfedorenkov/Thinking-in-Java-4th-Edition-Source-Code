package annotations;

import net.mindview.atunit.Test;
import net.mindview.atunit.TestObjectCreate;
import net.mindview.atunit.TestProperty;
import net.mindview.util.OSExecute;

import java.util.*;

import static net.mindview.util.Print.print;

public class AtUnitExample4 {
    static String theory = "All brontosauruses " +
            "are thin at one end, much MUCH thicker in the " +
            "middle, and then thin again at the far end.";
    private String word;
    private Random rand = new Random(); // Time-based seed

    public AtUnitExample4(String word) {
        this.word = word;
    }
//
    public String getWord() {
        return word;
    }

    public String scrambleWord() {
        List<Character> chars = new ArrayList<>();
        for (Character c : word.toCharArray())
            chars.add(c);
        Collections.shuffle(chars, rand);
        StringBuilder result = new StringBuilder();
        for (char ch : chars)
            result.append(ch);
        return result.toString();
    }

    @TestProperty
    static List<String> input = Arrays.asList(theory.split(" "));

    @TestProperty
    static Iterator<String> words = input.iterator();

    @TestObjectCreate
    static AtUnitExample4 create() {
        if (words.hasNext())
            return new AtUnitExample4(words.next());
        else
            return null;
    }

    @Test
    boolean words() {
        print(getWord());
        return getWord().equals("All");
    }

    @Test
    boolean scramble1() {
        // Change to a specific seed to get verifiable results:
        rand = new Random(47);
        print(getWord());
        String scrambled = scrambleWord();
        print(scrambled);
        return scrambled.equals("ntsaueorosurbs");
    }

    @Test
    boolean scramble2() {
        rand = new Random(74);
        print(getWord());
        String scrambled = scrambleWord();
        print(scrambled);
        return scrambled.equals("are");
    }

    public static void main(String[] args) throws Exception {
        System.out.println("starting");
        OSExecute.command("java net.mindview.atunit.AtUnit AtUnitExample4");
    }
}
/* Output:
starting
annotations.AtUnitExample4
  . words All

  . scramble1 brontosauruses
ntsaueorosurbs

  . scramble2 are
are

OK (3 tests)
 */