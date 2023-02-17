package generics;

import generics.watercolors.Watercolors;

import java.util.EnumSet;
import java.util.Set;

import static generics.watercolors.Watercolors.*;
import static net.mindview.util.Print.print;
import static net.mindview.util.Sets.*;

public class WatercolorSets {
    public static void main(String[] args) {
        Set<Watercolors> set1 = EnumSet.range(BRILLIANT_RED, VIRIDIAN_HUE);
        Set<Watercolors> set2 = EnumSet.range(CERULEAN_BLUE_HUE, BURNT_UMBER);
        print("set1: " + set1);
        print("set2: " + set2);
        print("union(set1, set2): " + union(set1, set2));
        Set<Watercolors> subset = intersection(set1, set2);
        print("intersection(set1, set2): " + subset);
        print("difference(set1, subset): " + difference(set1, subset));
        print("complement(set1, set2): " + complement(set1, set2));
    }
}
/* Output: (Sample)
set1: [BRILLIANT_RED, CRIMSON, MAGENTA, ROSE_MADDER, VIOLET, CERULEAN_BLUE_HUE, PHTHALO_BLUE, ULTRAMARINE, COBALT_BLUE_HUE, PERMANENT_GREEN, VIRIDIAN_HUE]
set2: [CERULEAN_BLUE_HUE, PHTHALO_BLUE, ULTRAMARINE, COBALT_BLUE_HUE, PERMANENT_GREEN, VIRIDIAN_HUE, SAP_GREEN, YELLOW_OCHRE, BURNT_SIENNA, RAW_UMBER, BURNT_UMBER]
union(set1, set2): [CERULEAN_BLUE_HUE, PERMANENT_GREEN, SAP_GREEN, COBALT_BLUE_HUE, VIRIDIAN_HUE, ULTRAMARINE, PHTHALO_BLUE, YELLOW_OCHRE, MAGENTA, BRILLIANT_RED, VIOLET, BURNT_SIENNA, CRIMSON, BURNT_UMBER, ROSE_MADDER, RAW_UMBER]
intersection(set1, set2): [CERULEAN_BLUE_HUE, PERMANENT_GREEN, COBALT_BLUE_HUE, VIRIDIAN_HUE, ULTRAMARINE, PHTHALO_BLUE]
difference(set1, subset): [MAGENTA, BRILLIANT_RED, VIOLET, CRIMSON, ROSE_MADDER]
complement(set1, set2): [SAP_GREEN, YELLOW_OCHRE, MAGENTA, BRILLIANT_RED, VIOLET, BURNT_SIENNA, CRIMSON, BURNT_UMBER, ROSE_MADDER, RAW_UMBER]
 */