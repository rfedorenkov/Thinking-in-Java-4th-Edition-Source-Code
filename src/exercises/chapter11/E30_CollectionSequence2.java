package exercises.chapter11;

import holding.InterfaceVsIterator;
import typeinfo.pets.Pet;
import typeinfo.pets.Pets;

import java.util.Collection;
import java.util.Iterator;

/**
 * Exercise 30
 * Modify CollectionSequence.java so that it does
 * not inherit from AbstractCollection, but instead
 * implements Collection.
 */
public class E30_CollectionSequence2 implements Collection<Pet> {
    private Pet[] pets = Pets.createArray(8);

    @Override
    public int size() {
        return pets.length;
    }

    @Override
    public boolean isEmpty() {
        return pets.length == 0;
    }

    @Override
    public boolean contains(Object o) {
        if (o == null) return false;
        for (Pet pet : pets)
            if (pet.equals(o))
                return true;
        return false;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        for (Object o : c)
            if (contains(o))
                return true;
        return false;
    }

    @Override
    public Iterator<Pet> iterator() {
        return new Iterator<Pet>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < pets.length;
            }

            @Override
            public Pet next() {
                return pets[index++];
            }

            @Override
            public void remove() { // Not implemented
                throw new UnsupportedOperationException();
            }
        };
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[pets.length];
        System.arraycopy(pets, 0, array, 0, pets.length);
        return array;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T[] toArray(T[] a) {
        T[] result = a.length >= size() ? a :
                (T[]) java.lang.reflect.Array
                        .newInstance(a.getClass().getComponentType(), size());
        System.arraycopy(pets, 0, result, 0, pets.length);
        if (result.length > pets.length)
            result[pets.length] = null;
        return result;
    }

    // Unsupported methods:

    @Override
    public boolean add(Pet pet) { // Not implemented
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean remove(Object o) { // Not implemented
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean addAll(Collection<? extends Pet> c) { // Not implemented
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean removeAll(Collection<?> c) { // Not implemented
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean retainAll(Collection<?> c) { // Not implemented
        throw new UnsupportedOperationException();
    }

    @Override
    public void clear() { // Not implemented
        throw new UnsupportedOperationException();
    }

    public static void main(String[] args) {
        E30_CollectionSequence2 c = new E30_CollectionSequence2();
        InterfaceVsIterator.display(c);
        InterfaceVsIterator.display(c.iterator());
    }
}
/* Output:
0:Rat 1:Manx 2:Cymric 3:Mutt 4:Pug 5:Cymric 6:Pug 7:Manx
0:Rat 1:Manx 2:Cymric 3:Mutt 4:Pug 5:Cymric 6:Pug 7:Manx
 */