package exercises.chapter15;

import generics.Holder3;
import typeinfo.pets.Dog;
import typeinfo.pets.Mouse;
import typeinfo.pets.Pet;

/**
 * Exercise 1
 * Use Holder3 with the typeinfo.pets library to show
 * that a Holder3 that is specified to hold a base type
 * ca also hold a derived type.
 */
public class E01_PetsHolder {
    public static void main(String[] args) {
        Holder3<Pet> h3 = new Holder3<>(new Dog("Spike"));
        System.out.println(h3.get());
        h3.set(new Mouse("Mickey"));
        System.out.println(h3.get());
    }
}
/* Output:
Dog Spike
Mouse Mickey
 */