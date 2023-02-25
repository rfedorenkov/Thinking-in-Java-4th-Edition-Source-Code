package exercises.chapter17;

import net.mindview.util.Countries;

import java.util.*;

import static net.mindview.util.Print.print;

/**
 * Exercise 18
 * Using SlowMap.java for inspiration, create SlowSet
 */
class SlowSet<E> extends AbstractSet<E> {
    private List<E> keys = new ArrayList<>();

    @Override
    public boolean add(E e) {
        if (!keys.contains(e))
            return keys.add(e);
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return keys.iterator();
    }

    @Override
    public int size() {
        return keys.size();
    }
}

public class E18_SlowSetTest {
    public static void test(Set<String> set) {
        set.addAll(Countries.names(10));
        set.addAll(Countries.names(10)); // Try to add duplicates
        set.addAll(Countries.names(10));
        print(set);
    }

    public static void main(String[] args) {
        test(new SlowSet<>());
    }
}
/* Output:
[ALGERIA, ANGOLA, BENIN, BOTSWANA, BURKINA FASO, BURUNDI, CAMEROON, CAPE VERDE, CENTRAL AFRICAN REPUBLIC, CHAD]
 */