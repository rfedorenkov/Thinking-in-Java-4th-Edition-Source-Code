package holding;

import typeinfo.pets.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static net.mindview.util.Print.print;

public class MapOfList {
    public static Map<Person, List<? extends Pet>> petPeople = new HashMap<>();

    static {
        petPeople.put(new Person("Dawn"),
                Arrays.asList(new Cymric("Molly"), new Mutt("Spoy")));
        petPeople.put(new Person("Kate"),
                Arrays.asList(new Cat("Shackleton"), new Cat("Elsie May"), new Dog("Margrett")));
        petPeople.put(new Person("Marilyn"),
                Arrays.asList(
                        new Pug("Louie aka Louis Snorkelstein Dupree"),
                        new Cat("Stanford aka Stinky el Negro"),
                        new Cat("Pinkola")));
        petPeople.put(new Person("Luke"),
                Arrays.asList(new Rat("Fuzzy"), new Rat("Fizzy")));
        petPeople.put(new Person("Isaac"),
                Arrays.asList(new Rat("Freckly")));
    }

    public static void main(String[] args) {
        print("People: " + petPeople.keySet());
        print("Pets: " + petPeople.values());
        for (Person person : petPeople.keySet()) {
            print(person + " has: ");
            for (Pet pet : petPeople.get(person))
                print("    " + pet);
        }
    }
}
/* Output:
People: [Person Marilyn, Person Dawn, Person Luke, Person Isaac, Person Kate]
Pets: [[Pug Louie aka Louis Snorkelstein Dupree, Cat Stanford aka Stinky el Negro, Cat Pinkola],
[Cymric Molly, Mutt Spoy], [Rat Fuzzy, Rat Fizzy], [Rat Freckly], [Cat Shackleton, Cat Elsie May, Dog Margrett]]
Person Marilyn has:
    Pug Louie aka Louis Snorkelstein Dupree
    Cat Stanford aka Stinky el Negro
    Cat Pinkola
Person Dawn has:
    Cymric Molly
    Mutt Spoy
Person Luke has:
    Rat Fuzzy
    Rat Fizzy
Person Isaac has:
    Rat Freckly
Person Kate has:
    Cat Shackleton
    Cat Elsie May
    Dog Margrett
 */