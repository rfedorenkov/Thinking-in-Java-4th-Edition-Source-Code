package exercises.chapter17;

import net.mindview.util.CollectionData;
import net.mindview.util.Generated;
import net.mindview.util.Generator;
import net.mindview.util.RandomGenerator;

import java.util.*;

import static net.mindview.util.Print.print;

/**
 * Exercise 40
 * Create a class containing two String objects and make it Comparable
 * so that the comparison only cares about the first String. Fill an
 * array and an ArrayList with objects of your class, using the
 * RandomGenerator generator. Demonstrate that sorting work properly.
 * Now make a Comparator that only cares about the second String, and
 * demonstrate that sorting works properly. Also perform a binary
 * search using your Comparator.
 */
class TwoString implements Comparable<TwoString> {
    private String one;
    private String two;

    private TwoString(String one, String two) {
        this.one = one;
        this.two = two;
    }

    @Override
    public int compareTo(TwoString o) {
        return one.compareTo(o.one);
    }

    private static Generator<String> gen = new RandomGenerator.String(4);

    public static Generator<TwoString> generator() {
        return new Generator<>() {
            @Override
            public TwoString next() {
                return new TwoString(gen.next(), gen.next());
            }
        };
    }

    public static class CompareSecond implements Comparator<TwoString> {
        @Override
        public int compare(TwoString o1, TwoString o2) {
            return o1.two.compareTo(o2.two);
        }
    }

    @Override
    public String toString() {
        return one + "=" + two;
    }
}

public class E40_TwoStringComparatorTest {
    public static void main(String[] args) {
        Comparator<TwoString> comp = new TwoString.CompareSecond();

        TwoString[] array = new TwoString[10];
        Generated.array(array, TwoString.generator());
        print("array: " + Arrays.toString(array));
        print("Arrays.sort(array)");
        Arrays.sort(array);
        print("array: " + Arrays.toString(array));
        print("Arrays.sort(array, comp)");
        Arrays.sort(array, comp);
        print("array: " + Arrays.toString(array));
        TwoString key = array[7];
        int index = Arrays.binarySearch(array, key);
        print("Location of " + key + " is " + index +
                ", array[" + index + "] = " + array[index]);

        List<TwoString> list = new ArrayList<>();
        list.addAll(CollectionData.list(TwoString.generator(), 10));
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
array: [EahK=cxrE, EcUO=neOE, QrGs=eGZM, YNzb=rnyG, aMes=btWH, cFOW=ZnTc, dLsm=wHLG, kjUr=UkZP, mJMR=oEsu, qUCB=bkIn]
Arrays.sort(array, comp)
array: [kjUr=UkZP, cFOW=ZnTc, qUCB=bkIn, aMes=btWH, EahK=cxrE, QrGs=eGZM, EcUO=neOE, mJMR=oEsu, YNzb=rnyG, dLsm=wHLG]
Location of mJMR=oEsu is 7, array[7] = mJMR=oEsu
list: [gwsq=PzDy, CyRF=JQAH, xxHv=HqXu, mcXZ=Joog, oYWM=Nvqe, uTpn=Xsgq, iaxx=EAJJ, mzMs=slJr, Lvpf=FvHV, EEqj=ncLd]
Collections.sort(list)
list: [CyRF=JQAH, EEqj=ncLd, Lvpf=FvHV, gwsq=PzDy, iaxx=EAJJ, mcXZ=Joog, mzMs=slJr, oYWM=Nvqe, uTpn=Xsgq, xxHv=HqXu]
Collections.sort(list, comp)
list: [iaxx=EAJJ, Lvpf=FvHV, xxHv=HqXu, CyRF=JQAH, mcXZ=Joog, oYWM=Nvqe, gwsq=PzDy, uTpn=Xsgq, EEqj=ncLd, mzMs=slJr]
Location of uTpn=Xsgq is 7, list.get(7) = uTpn=Xsgq
 */