package exercises.chapter17;

import java.util.*;

import static net.mindview.util.Print.print;

/**
 * Exercise 8
 * Create a generic, singly-linked list class called SList,
 * which, to keep things simple, does not implement the List
 * interface. Each Link object in the list should contain
 * a reference to the next element in the list, but not
 * the previous one (LinkedList, in contrast, is a doubly-linked
 * list, which means it maintains links in both directions).
 * Create your own SListIterator which, again for simplicity,
 * does not implement ListIterator. The only method in SList
 * other than toString() should be iterator(), which produces
 * an SListIterator. The only way to insert and remove elements
 * from an SList in through SListIterator. Write code to
 * demonstrate SList.
 */
interface SListIterator<T> {
    boolean hasNext();

    T next();

    void add(T t);

    void remove();
}

class SList<T> {
    private final Node<T> head = new Node<>(null, null);

    public SList() {
        head.next = head;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (SListIterator<T> it = iterator(); it.hasNext();) {
            sb.append(it.next());
            if (it.hasNext())
                sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    SListIterator<T> iterator() {
        return new SListIter();
    }

    private static class Node<T> {
        T element;
        Node<T> next;

        Node(T element, Node<T> next) {
            this.element = element;
            this.next = next;
        }
    }

    private class SListIter implements SListIterator<T> {
        Node<T> current = head;
        Node<T> next = head.next;

        @Override
        public boolean hasNext() {
            return next != head;
        }

        @Override
        public T next() {
            if (next == head)
                throw new NoSuchElementException();
            current = next;
            next = next.next;
            return current.element;
        }

        @Override
        public void add(T element) {
            current = head;
            Node<T> newNode = new Node<>(element, next);
            if (head.next == head)
                head.next = newNode;
            else {
                for (Node<T> node = head; ; node = node.next)
                    if (node.next == next) {
                        node.next = newNode;
                        break;
                    }
            }
        }

        @Override
        public void remove() {
            if (current == head)
                throw new IllegalStateException();
            for (Node<T> node = head; ; node = node.next) {
                if (node.next == current) {
                    node.next = current.next;
                    break;
                }
            }
            current = head;
        }
    }
}

public class E08_SListTest {
    public static void main(String[] args) {
        print("SListIterator:");
        SList<String> list = new SList<>();
        print(list);
        SListIterator<String> it = list.iterator();
        it.add("Dog");
        it.add("Cat");
        it.add("Horse");
        print(it.hasNext());
        print(list);
        it = list.iterator();
        it.add("Dinosaur");
        while (it.hasNext())
            print(it.next());
        print(list);
        it = list.iterator();
        print(it.next());
        it.remove();
        print(list);
        print();
        System.out.println("ListIterator:");
        List<String> list2 = new LinkedList<>();
        print(list2);
        ListIterator<String> it2 = list2.listIterator();
        it2.add("Dog");
        it2.add("Cat");
        it2.add("Horse");
        print(it2.hasNext());
        print(list2);
        it2 = list2.listIterator();
        it2.add("Dinosaur");
        while (it2.hasNext())
            print(it2.next());
        print(list2);
        it2 = list2.listIterator();
        print(it2.next());
        it2.remove();
        print(list2);
    }
}
/* Output:
SListIterator:
[]
false
[Dog, Cat, Horse]
Dog
Cat
Horse
[Dinosaur, Dog, Cat, Horse]
Dinosaur
[Dog, Cat, Horse]

ListIterator:
[]
false
[Dog, Cat, Horse]
Dog
Cat
Horse
[Dinosaur, Dog, Cat, Horse]
Dinosaur
[Dog, Cat, Horse]
 */