package exercises.chapter15;

import generics.watercolors.Watercolors;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

import static generics.watercolors.Watercolors.*;
import static net.mindview.util.Print.print;

/**
 * Exercise 17
 * Study the JDK documentation for EnumSet. You'll see there's
 * a clone() method.
 * However, you cannot clone() from the reference to the Set
 * interface passed in Sets.java. Can you modify Sets.java to
 * handle both the general case of a Set interface as shown,
 * and the special case of an EnumSet, using clone() instead
 * of creating a new HashSet?
 */
public class E17_Sets2 {
    private static <T> Set<T> copy(Set<T> set) {
        if (set instanceof EnumSet) {
            return ((EnumSet) set).clone();
        }
        return new HashSet<>(set);
    }

    public static <T> Set<T> union(Set<T> a, Set<T> b) {
        Set<T> result = copy(a);
        result.addAll(b);
        return result;
    }

    public static <T> Set<T> intersection(Set<T> a, Set<T> b) {
        Set<T> result = copy(a);
        result.retainAll(b);
        return result;
    }

    // Subtract subset from superset:
    public static <T> Set<T> difference(Set<T> superset, Set<T> subset) {
        Set<T> result = copy(superset);
        result.removeAll(subset);
        return result;
    }

    // Reflexive--everything not in the intersection:
    public static <T> Set<T> complement(Set<T> a, Set<T> b) {
        return difference(union(a, b), intersection(a, b));
    }

    public static void main(String[] args) {
        Set<Watercolors> set1 = EnumSet.range(BRILLIANT_RED, MAGENTA);
        Set<Watercolors> set2 = EnumSet.range(CERULEAN_BLUE_HUE, COBALT_BLUE_HUE);
        print("set1: " + set1);
        print("set2: " + set2);
        print("union(set1, set2): " + union(set1, set2));
        print("union(set1, set2) type: " + union(set1, set2).getClass().getSimpleName());
        Set<Integer> set3 = new HashSet<>(Arrays.asList(1, 2, 3));
        Set<Integer> set4 = new HashSet<>(Arrays.asList(3, 4, 5));
        print("set3: " + set3);
        print("set4: " + set4);
        print("union(set3, set3): " + union(set3, set4));
        print("union(set3, set4) type: " + union(set3, set4).getClass().getSimpleName());
    }
}
/* Output:
set1: [BRILLIANT_RED, CRIMSON, MAGENTA]
set2: [CERULEAN_BLUE_HUE, PHTHALO_BLUE, ULTRAMARINE, COBALT_BLUE_HUE]
union(set1, set2): [BRILLIANT_RED, CRIMSON, MAGENTA, CERULEAN_BLUE_HUE, PHTHALO_BLUE, ULTRAMARINE, COBALT_BLUE_HUE]
union(set1, set2) type: RegularEnumSet
set3: [1, 2, 3]
set4: [3, 4, 5]
union(set3, set3): [1, 2, 3, 4, 5]
union(set3, set4) type: HashSet
 */