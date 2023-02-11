package exercises.chapter14;

import typeinfo.pets.*;
import typeinfo.pets.factory.Factory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Exercise 15
 * Implement a new PetCreator using RegisteredFactories,
 * and modify the Pets Facade so that is uses this one
 * instead of the other two. Ensure that time rest of
 * the examples that use Pets.java still work correctly.
 */
class Cymric extends Manx {
    public static class Factory implements typeinfo.pets.factory.Factory<Cymric> {
        @Override
        public Cymric create() {
            return new Cymric();
        }
    }
}

class EgyptianMau extends Cat {
    public static class Factory implements typeinfo.pets.factory.Factory<EgyptianMau> {
        @Override
        public EgyptianMau create() {
            return new EgyptianMau();
        }
    }
}

class Hamster extends Rodent {
    public static class Factory implements typeinfo.pets.factory.Factory<Hamster> {
        @Override
        public Hamster create() {
            return new Hamster();
        }
    }
}

class Manx extends Cat {
    public static class Factory implements typeinfo.pets.factory.Factory<Manx> {
        @Override
        public Manx create() {
            return new Manx();
        }
    }
}

class Mouse extends Rodent {
    public static class Factory implements typeinfo.pets.factory.Factory<Mouse> {
        @Override
        public Mouse create() {
            return new Mouse();
        }
    }
}

class Mutt extends Dog {
    public static class Factory implements typeinfo.pets.factory.Factory<Mutt> {
        @Override
        public Mutt create() {
            return new Mutt();
        }
    }
}
class Pug extends Dog {
    public static class Factory implements typeinfo.pets.factory.Factory<Pug> {
        @Override
        public Pug create() {
            return new Pug();
        }
    }
}

class Rat extends Rodent {
    public static class Factory implements typeinfo.pets.factory.Factory<Rat> {
        @Override
        public Rat create() {
            return new Rat();
        }
    }
}

class PetCreatorFactories extends PetCreator {
    static List<Factory<? extends Pet>> petFactories = new ArrayList<>();

    static {
        petFactories.add(new Cymric.Factory());
        petFactories.add(new EgyptianMau.Factory());
        petFactories.add(new Hamster.Factory());
        petFactories.add(new Manx.Factory());
        petFactories.add(new Mouse.Factory());
        petFactories.add(new Mutt.Factory());
        petFactories.add(new Pug.Factory());
        petFactories.add(new Rat.Factory());
    }

    @Override
    public List<Class<? extends Pet>> types() {
        throw new RuntimeException("This method is not used!");
    }

    private static Random rand = new Random(47);

    @Override
    public Pet randomPet() {
        int n = rand.nextInt(petFactories.size());
        return petFactories.get(n).create();
    }
}

class Pets {
    public static final PetCreator creator = new PetCreatorFactories();

    public static Pet randomPet() {
        return creator.randomPet();
    }

    public static Pet[] createArray(int size) {
        return creator.createArray(size);
    }

    public static ArrayList<Pet> arrayList(int size) {
        return creator.arrayList(size);
    }
}

public class E15_PetsRegisteredFactories {
    public static void main(String[] args) {
        for (Pet pet : Pets.createArray(20)) {
            System.out.println(pet);
        }
    }
}
/* Output:
Mutt
Manx
Mouse
Cymric
EgyptianMau
Mouse
EgyptianMau
Manx
Mouse
Mutt
Hamster
Rat
Hamster
Cymric
Cymric
Mouse
Pug
EgyptianMau
Pug
Mouse
 */