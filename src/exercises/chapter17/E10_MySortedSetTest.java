package exercises.chapter17;

import java.util.*;

import static java.util.Collections.binarySearch;
import static net.mindview.util.Print.print;

/**
 * Exercise 10
 * Using a LinkedList as your underlying
 * implementation, define your own SortedSet.
 */
class MySortedSet<T> implements SortedSet<T> {

    private List<T> list;

    public MySortedSet() {
        list = new LinkedList<>();
    }

    public MySortedSet(List<T> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return list.toString();
    }

    @Override
    public int hashCode() {
        return list.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof MySortedSet
                && list.equals(((MySortedSet<?>) obj).list);
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        Objects.requireNonNull(o);
        return list.contains(o);
    }

    @Override
    public Iterator<T> iterator() {
        return list.iterator();
    }

    @Override
    public Object[] toArray() {
        return list.toArray();
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return list.toArray(a);
    }

    @Override
    @SuppressWarnings("unchecked")
    public boolean add(T t) {
        Objects.requireNonNull(t);
        int index = binarySearch((List<Comparable<T>>) list, t);
        if (index < 0) {
            index = -(index + 1);
            list.add(index, t);
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(Object o) {
        Objects.requireNonNull(o);
        return list.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        Objects.requireNonNull(c);
        return list.contains(c);
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        Objects.requireNonNull(c);
        boolean result = false;
        for (T t : c) {
            Objects.requireNonNull(t);
            result = result | add(t);
        }
        return result;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        Objects.requireNonNull(c);
        return list.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        Objects.requireNonNull(c);
        return list.retainAll(c);
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public Comparator<? super T> comparator() {
        return null;
    }

    @Override
    public T first() {
        try {
            return list.get(0);
        } catch (IndexOutOfBoundsException e) {
            throw new NoSuchElementException();
        }
    }

    @Override
    public T last() {
        try {
            return list.get(list.size() - 1);
        } catch (IndexOutOfBoundsException e) {
            throw new NoSuchElementException();
        }
    }

    @Override
    public SortedSet<T> subSet(T fromElement, T toElement) {
        Objects.requireNonNull(fromElement);
        Objects.requireNonNull(toElement);
        int fromIndex = list.indexOf(fromElement);
        int toIndex = list.indexOf(toElement);
        if (fromIndex != -1 && toIndex != -1)
            return new MySortedSet<>(list.subList(fromIndex, toIndex));
        throw new IllegalArgumentException();
    }

    @Override
    public SortedSet<T> headSet(T toElement) {
        Objects.requireNonNull(toElement);
        int toIndex = list.indexOf(toElement);
        if (toIndex != -1)
            return new MySortedSet<>(list.subList(0, toIndex));
        throw new IllegalArgumentException();
    }

    @Override
    public SortedSet<T> tailSet(T fromElement) {
        Objects.requireNonNull(fromElement);
        int fromIndex = list.indexOf(fromElement);
        if (fromIndex != -1)
            return new MySortedSet<>(list.subList(fromIndex, list.size()));
        throw new IllegalArgumentException();
    }
}

public class E10_MySortedSetTest {
    public static void main(String[] args) {
        SortedSet<String> sortedSet = new MySortedSet<>();
        Collections.addAll(sortedSet,
                "one two three four five six seven eight"
                        .split(" "));
        print(sortedSet);
        String low = sortedSet.first();
        String high = sortedSet.last();
        print(low);
        print(high);
        Iterator<String> it = sortedSet.iterator();
        for (int i = 0; i <= 6; i++) {
            if (i == 3) low = it.next();
            if (i == 6) high = it.next();
            else it.next();
        }
        print(low);
        print(high);
        print(sortedSet.subSet(low, high));
        print(sortedSet.headSet(high));
        print(sortedSet.tailSet(low));
        print(sortedSet.contains("three"));
        print(sortedSet.contains("eleven"));
        print(sortedSet.addAll(Arrays.asList("three", "eleven")));
        print(sortedSet.addAll(Arrays.asList("three", "eleven")));
        print(sortedSet);
        print(sortedSet.retainAll(Arrays.asList("three", "eleven")));
        print(sortedSet);
        try {
            sortedSet.addAll(Arrays.asList("zero", null));
        } catch (NullPointerException e) {
            System.out.println("null is not supported!");
        }
        print(sortedSet);
    }
}
/* Output:
[eight, five, four, one, seven, six, three, two]
eight
two
one
two
[one, seven, six, three]
[eight, five, four, one, seven, six, three]
[one, seven, six, three, two]
true
false
true
false
[eight, eleven, five, four, one, seven, six, three, two]
true
[eleven, three]
null is not supported!
[eleven, three, zero]
 */