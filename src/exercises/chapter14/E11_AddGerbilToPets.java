package exercises.chapter14;

import typeinfo.pets.Rodent;

/**
 * Exercise 11
 * Add Gerbil to the typeinfo.pets library and modify all
 * the examples in this chapter to adapt to this new class.
 */
class Gerbil extends Rodent {
    public Gerbil(String name) {
        super(name);
    }

    public Gerbil() {
        super();
    }
}

public class E11_AddGerbilToPets {
    // In typeinfo/pets/ForNameCreator.java add new line "typeinfo.pets1.Gerbil";
    // In typeinfo/pets/LiteralPetCreator1.java add Gerbil.class in allTypes;
    // In typeinfo/PetCount.java add if(pet instanceof Gerbil) counter.count("Gerbil");
}