package exercises.chapter17;

import net.mindview.util.Countries;

import java.util.*;

/**
 * Exercise 3
 * Using Countries, fill a Set with the same data multiple times,
 * then verify that the Set ends up with only one of each instance.
 * Try this with HashSet, LinkedHashSet, and TreeSet.
 */
public class E03_VerifySet {
    public static Set<String> fillSet(Set<String> set) {
        System.out.println(set.getClass().getSimpleName());
        for (int i = 0; i < 5; i++)
            set.addAll(Countries.names(10));
        return set;
    }

    public static void main(String[] args) {
        System.out.println(fillSet(new HashSet<>()));
        System.out.println(fillSet(new LinkedHashSet<>()));
        System.out.println(fillSet(new TreeSet<>()));
    }
}
/* Output:
HashSet
[BENIN, BOTSWANA, CENTRAL AFRICAN REPUBLIC, CHAD, CAMEROON, CAPE VERDE, ANGOLA, BURKINA FASO, ALGERIA, BURUNDI]
LinkedHashSet
[ALGERIA, ANGOLA, BENIN, BOTSWANA, BURKINA FASO, BURUNDI, CAMEROON, CAPE VERDE, CENTRAL AFRICAN REPUBLIC, CHAD]
TreeSet
[ALGERIA, ANGOLA, BENIN, BOTSWANA, BURKINA FASO, BURUNDI, CAMEROON, CAPE VERDE, CENTRAL AFRICAN REPUBLIC, CHAD]
 */