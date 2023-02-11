package typeinfo;

import net.mindview.util.TypeCounter;
import typeinfo.pets.Pet;
import typeinfo.pets.Pets;

import static net.mindview.util.Print.print;
import static net.mindview.util.Print.printnb;

public class PetCount4 {
    public static void main(String[] args) {
        TypeCounter counter = new TypeCounter(Pet.class);
        for (Pet pet : Pets.createArray(20)) {
            printnb(pet.getClass().getSimpleName() + " ");
            counter.count(pet);
        }
        print();
        print(counter);
    }
}
/* Output:
Rat Manx Cymric Mutt Pug Cymric Pug Manx Cymric Rat EgyptianMau Hamster EgyptianMau Mutt Mutt Cymric Mouse Pug Mouse Cymric
{Rodent=5, Dog=6, Mutt=3, Hamster=1, Mouse=2, Manx=7, Pet=20, Cat=9, Pug=3, Rat=2, EgyptianMau=2, Cymric=5}
 */