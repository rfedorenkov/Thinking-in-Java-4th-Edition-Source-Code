package exercises.chapter17;

import net.mindview.util.Countries;

import java.util.*;

/**
 * Exercise 1
 * Create a List (try both ArrayList and LinkedList) and fill it using
 * Countries. Sort the list and print it, then apply Collections.shuffle()
 * toe the list repeatedly, printing it each time so you can see how
 * the shuffle() method randomized the list differently each time.
 */
public class E01_CountryList {
    static void shuffleAndPrint(List<String> list) {
        Random rand = new Random(47);
        System.out.println(list.getClass().getSimpleName());
        Collections.sort(list);
        System.out.println("sorted: " + list);
        for (int i = 0; i < 3; i++) {
            Collections.shuffle(list, rand);
            System.out.println("shuffle " + i + " :" + list);
        }

    }

    public static void main(String[] args) {
        List<String> countries = new ArrayList<>(Countries.names(8));
        shuffleAndPrint(countries);
        countries = new LinkedList<>(Countries.names(8));
        shuffleAndPrint(countries);
    }
}
/* Output:
ArrayList
sorted: [ALGERIA, ANGOLA, BENIN, BOTSWANA, BURKINA FASO, BURUNDI, CAMEROON, CAPE VERDE]
shuffle 0 :[BURKINA FASO, BOTSWANA, CAMEROON, ALGERIA, CAPE VERDE, ANGOLA, BENIN, BURUNDI]
shuffle 1 :[BENIN, CAMEROON, BURKINA FASO, CAPE VERDE, BURUNDI, BOTSWANA, ANGOLA, ALGERIA]
shuffle 2 :[ALGERIA, BURKINA FASO, BOTSWANA, ANGOLA, CAMEROON, BURUNDI, CAPE VERDE, BENIN]
LinkedList
sorted: [ALGERIA, ANGOLA, BENIN, BOTSWANA, BURKINA FASO, BURUNDI, CAMEROON, CAPE VERDE]
shuffle 0 :[BURKINA FASO, BOTSWANA, CAMEROON, ALGERIA, CAPE VERDE, ANGOLA, BENIN, BURUNDI]
shuffle 1 :[BENIN, CAMEROON, BURKINA FASO, CAPE VERDE, BURUNDI, BOTSWANA, ANGOLA, ALGERIA]
shuffle 2 :[ALGERIA, BURKINA FASO, BOTSWANA, ANGOLA, CAMEROON, BURUNDI, CAPE VERDE, BENIN]
 */