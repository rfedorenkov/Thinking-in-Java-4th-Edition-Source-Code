package exercises.chapter17;

import net.mindview.util.CollectionData;
import net.mindview.util.Generator;
import net.mindview.util.RandomGenerator;

import java.util.*;

import static net.mindview.util.Print.print;

/**
 * Exercise 41
 * Modify the class in Exercise 40 so that it works
 * with HashSets and as a key in HashMaps.
 */
class TwoString2 implements Comparable<TwoString2> {
    private String one;
    private String two;

    private TwoString2(String one, String two) {
        this.one = one;
        this.two = two;
    }

    @Override
    public int compareTo(TwoString2 o) {
        return one.compareTo(o.one);
    }

    private static Generator<String> gen = new RandomGenerator.String(4);

    public static Generator<TwoString2> generator() {
        return new Generator<>() {
            @Override
            public TwoString2 next() {
                return new TwoString2(gen.next(), gen.next());
            }
        };
    }

    public static class CompareSecond implements Comparator<TwoString2> {
        @Override
        public int compare(TwoString2 o1, TwoString2 o2) {
            return o1.two.compareTo(o2.two);
        }
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 37 * result + one.hashCode();
        result = 37 * result + two.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof TwoString2) {
            TwoString2 other = ((TwoString2) o);
            return one.equals(other.one) && two.equals(other.two);
        }
        return false;
    }

    @Override
    public String toString() {
        return one + "=" + two;
    }
}

public class E41_TwoStringHashedTest {
    public static void main(String[] args) {
        Set<TwoString2> set = new HashSet<>();
        set.addAll(CollectionData.list(TwoString2.generator(), 10));
        print("set: " + set);
        Map<TwoString2, Integer> map = new HashMap<>();
        Iterator<TwoString2> it = set.iterator();
        int count = 0;
        while (it.hasNext())
            map.put(it.next(), count++);
        print("map: " + map);
    }
}
/* Output:
set: [cFOW=ZnTc, EahK=cxrE, aMes=btWH, kjUr=UkZP, dLsm=wHLG, qUCB=bkIn, mJMR=oEsu, EcUO=neOE, YNzb=rnyG, QrGs=eGZM]
map: {cFOW=ZnTc=0, EahK=cxrE=1, aMes=btWH=2, kjUr=UkZP=3, dLsm=wHLG=4, qUCB=bkIn=5, mJMR=oEsu=6, EcUO=neOE=7, YNzb=rnyG=8, QrGs=eGZM=9}
 */