package exercises.chapter15;

import net.mindview.util.Generator;

import java.util.Iterator;
import java.util.Random;

/**
 * Exercise 8
 * Following the form of the Coffee example, create a hierarchy
 * of StoryCharacters from your favorite movie, dividing them
 * into GoodGuys and BadGuys. Create a generator for StoryCharacters,
 * following the form of CoffeeGenerator.
 */
class StoryCharacters {
    private static long counter = 0;
    private final long id = counter++;

    @Override
    public String toString() {
        return getClass().getSimpleName() + " " + id;
    }
}

class BadGuys extends StoryCharacters {
    @Override
    public String toString() {
        return super.toString() + " bad guy";
    }
}

class LordVoldemort extends BadGuys {
}

class DracoMalfoy extends BadGuys {
}

class Dementors extends BadGuys {
}

class GoodGuys extends StoryCharacters {
    @Override
    public String toString() {
        return super.toString() + " good guy";
    }
}

class HarryPotter extends GoodGuys {
}

class AlbusDumbledore extends GoodGuys {
}

class RubeusHagrid extends GoodGuys {
}


class StoryCharactersGenerator implements Generator<StoryCharacters>, Iterable<StoryCharacters> {
    private Class[] types = { LordVoldemort.class, DracoMalfoy.class,
            Dementors.class, HarryPotter.class, AlbusDumbledore.class, RubeusHagrid.class };
    private static Random rand = new Random(47);

    public StoryCharactersGenerator() {
    }

    // For iteration:
    private int size = 0;

    public StoryCharactersGenerator(int sz) {
        size = sz;
    }

    @Override
    public StoryCharacters next() {
        try {
            return (StoryCharacters) types[rand.nextInt(types.length)].newInstance();
            // Report programmer errors at run time:
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    class CoffeeIterator implements Iterator<StoryCharacters> {
        int count = size;

        @Override
        public boolean hasNext() {
            return count > 0;
        }

        @Override
        public StoryCharacters next() {
            count--;
            return StoryCharactersGenerator.this.next();
        }

        @Override
        public void remove() { // Not implemented
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public Iterator<StoryCharacters> iterator() {
        return new CoffeeIterator();
    }
}

public class E08_MovieCharacterGenerator {
    public static void main(String[] args) {
        StoryCharactersGenerator gen = new StoryCharactersGenerator();
        for (int i = 0; i < 8; i++)
            System.out.println(gen.next());
        for (StoryCharacters c : new StoryCharactersGenerator(8))
            System.out.println(c);
    }
}
/* Output:
Dementors 0 bad guy
RubeusHagrid 1 good guy
DracoMalfoy 2 bad guy
RubeusHagrid 3 good guy
DracoMalfoy 4 bad guy
RubeusHagrid 5 good guy
AlbusDumbledore 6 good guy
Dementors 7 bad guy
LordVoldemort 8 bad guy
DracoMalfoy 9 bad guy
LordVoldemort 10 bad guy
LordVoldemort 11 bad guy
HarryPotter 12 good guy
DracoMalfoy 13 bad guy
RubeusHagrid 14 good guy
AlbusDumbledore 15 good guy
 */