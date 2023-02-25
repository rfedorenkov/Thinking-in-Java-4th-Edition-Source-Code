package exercises.chapter17;

import net.mindview.util.CollectionData;
import net.mindview.util.Generated;
import net.mindview.util.Generator;
import net.mindview.util.RandomGenerator;

import java.util.*;

import static net.mindview.util.Print.print;

/**
 * Exercise 42
 * Modify Exercise 40 so that an alphabetic sort in used.
 */
class TwoString3 implements Comparable<TwoString3> {
    private String one;
    private String two;

    private TwoString3(String one, String two) {
        this.one = one;
        this.two = two;
    }

    @Override
    public int compareTo(TwoString3 o) {
        return one.compareToIgnoreCase(o.one);
    }

    private static Generator<String> gen = new RandomGenerator.String(4);

    public static Generator<TwoString3> generator() {
        return new Generator<>() {
            @Override
            public TwoString3 next() {
                return new TwoString3(gen.next(), gen.next());
            }
        };
    }

    public static class CompareSecond implements Comparator<TwoString3> {
        @Override
        public int compare(TwoString3 o1, TwoString3 o2) {
            return o1.two.compareToIgnoreCase(o2.two);
        }
    }

    @Override
    public String toString() {
        return one + "=" + two;
    }
}

public class E42_TwoStringAlphabeticComparator {
    public static void main(String[] args) {
        Comparator<TwoString3> comp = new TwoString3.CompareSecond();

        TwoString3[] array = new TwoString3[10];
        Generated.array(array, TwoString3.generator());
        print("array: " + Arrays.toString(array));
        print("Arrays.sort(array)");
        Arrays.sort(array);
        print("array: " + Arrays.toString(array));
        print("Arrays.sort(array, comp)");
        Arrays.sort(array, comp);
        print("array: " + Arrays.toString(array));
        TwoString3 key = array[7];
        int index = Arrays.binarySearch(array, key);
        print("Location of " + key + " is " + index +
                ", array[" + index + "] = " + array[index]);

        List<TwoString3> list = new ArrayList<>();
        list.addAll(CollectionData.list(TwoString3.generator(), 10));
        print("list: " + list);
        print("Collections.sort(list)");
        Collections.sort(list);
        print("list: " + list);
        print("Collections.sort(list, comp)");
        Collections.sort(list, comp);
        print("list: " + list);
        key = list.get(7);
        index = Collections.binarySearch(list, key);
        print("Location of " + key + " is " + index +
                ", list.get(" + index + ") = " + list.get(index));
    }
}
/* Output:
array: [YNzb=rnyG, cFOW=ZnTc, QrGs=eGZM, mJMR=oEsu, EcUO=neOE, dLsm=wHLG, EahK=cxrE, qUCB=bkIn, aMes=btWH, kjUr=UkZP]
Arrays.sort(array)
array: [aMes=btWH, cFOW=ZnTc, dLsm=wHLG, EahK=cxrE, EcUO=neOE, kjUr=UkZP, mJMR=oEsu, QrGs=eGZM, qUCB=bkIn, YNzb=rnyG]
Arrays.sort(array, comp)
array: [qUCB=bkIn, aMes=btWH, EahK=cxrE, QrGs=eGZM, EcUO=neOE, mJMR=oEsu, YNzb=rnyG, kjUr=UkZP, dLsm=wHLG, cFOW=ZnTc]
Location of kjUr=UkZP is 7, array[7] = kjUr=UkZP
list: [gwsq=PzDy, CyRF=JQAH, xxHv=HqXu, mcXZ=Joog, oYWM=Nvqe, uTpn=Xsgq, iaxx=EAJJ, mzMs=slJr, Lvpf=FvHV, EEqj=ncLd]
Collections.sort(list)
list: [CyRF=JQAH, EEqj=ncLd, gwsq=PzDy, iaxx=EAJJ, Lvpf=FvHV, mcXZ=Joog, mzMs=slJr, oYWM=Nvqe, uTpn=Xsgq, xxHv=HqXu]
Collections.sort(list, comp)
list: [iaxx=EAJJ, Lvpf=FvHV, xxHv=HqXu, mcXZ=Joog, CyRF=JQAH, EEqj=ncLd, oYWM=Nvqe, gwsq=PzDy, mzMs=slJr, uTpn=Xsgq]
Location of gwsq=PzDy is 7, list.get(7) = gwsq=PzDy
 */