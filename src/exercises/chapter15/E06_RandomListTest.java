package exercises.chapter15;

import generics.RandomList;
import typeinfo.pets.Pet;
import typeinfo.pets.Pets;

/**
 * Exercise 6
 * Use RandomList with two more types in addition
 * to the one shown in main().
 */
public class E06_RandomListTest {
    public static void printList(RandomList<?> list) {
        for (int i = 0; i < 11; i++)
            System.out.print(list.select() + " ");
        System.out.println();
    }

    public static void main(String[] args) {
        RandomList<String> rs = new RandomList<>();
        for (String s : ("The quick brown fox jumped over " +
                "the lazy brown dog").split(" "))
            rs.add(s);
        printList(rs);
        RandomList<Pet> rp = new RandomList<>();
        for (Pet pet : Pets.createArray(10))
            rp.add(pet);
        printList(rp);
        RandomList<Integer> ri = new RandomList<>();
        for (int i = 0; i < 10; i++)
            ri.add(i);
        printList(ri);
    }
}
/* Output:
brown over fox quick quick dog brown The brown lazy brown
Cymric Cymric Mutt Manx Manx Rat Cymric Rat Cymric Manx Cymric
8 5 3 1 1 9 8 0 2 7 8
 */