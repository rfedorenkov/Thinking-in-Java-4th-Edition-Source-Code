package exercises.chapter15;

import typeinfo.pets.Individual;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static net.mindview.util.Print.print;

/**
 * Exercise 40
 * Add a speak() method to all the pets in typeinfo.pets.
 * Modify Apply.java to call the speak() method for
 * a heterogeneous collection of Pet.
 */
class Pet2 extends Individual {
    public Pet2(String name) {
        super(name);
    }

    public Pet2() {
    }

    public void speak() {
        print(this + " speak!");
    }
}

class Dog2 extends Pet2 {
}

class Mutt2 extends Dog2 {
}

class Pug2 extends Dog2 {
}

class Cat2 extends Pet2 {
}

class EgyptianMau2 extends Cat2 {
}

class Manx2 extends Cat2 {
}

class Cymric2 extends Manx2 {
}

class Rodent2 extends Pet2 {
}

class Hamster2 extends Rodent2 {
}

class Mouse2 extends Rodent2 {
}

class Rat2 extends Rodent2 {
}

class Apply {
    public static <T, S extends Iterable<? extends T>>
    void apply(S seq, Method f, Object... args) {
        try {
            for (T t : seq)
                f.invoke(t, args);
        } catch (Exception e) {
            // Failures are programmer errors
            throw new RuntimeException(e);
        }
    }
}

public class E40_SpeakingPets {
    public static void main(String[] args) throws Exception {
        List<Pet2> pets = new ArrayList<>(
                Arrays.asList(new Cat2(), new Cymric2(), new Dog2(),
                        new EgyptianMau2(), new Hamster2(), new Manx2(), new Mouse2(),
                        new Mutt2(), new Pug2(), new Rat2(), new Rodent2()));
        Apply.apply(pets, Pet2.class.getMethod("speak"));
    }
}
/* Output:
Cat2 speak!
Cymric2 speak!
Dog2 speak!
EgyptianMau2 speak!
Hamster2 speak!
Manx2 speak!
Mouse2 speak!
Mutt2 speak!
Pug2 speak!
Rat2 speak!
Rodent2 speak!
 */