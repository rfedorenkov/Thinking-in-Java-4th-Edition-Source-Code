package exercises.chapter15;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;

/**
 * Exercise 33
 * Repair GenericCast.java using an ArrayList.
 */
class FullStackException extends RuntimeException {
}

class FixedSizeStack2<T> {
    private int index = 0;
    private final int size;
    private final List<T> storage;

    public FixedSizeStack2(int size) {
        storage = new ArrayList<>(size);
        this.size = size;
    }

    public void push(T item) {
        if (index < size) {
            storage.add(item);
            index++;
        } else
            throw new FullStackException();
    }

    @SuppressWarnings("unchecked")
    public T pop() {
        if (index > 0)
            return storage.remove(--index);
        throw new EmptyStackException();
    }
}

public class E33_GenericCast2 {
    public static void main(String[] args) {
        FixedSizeStack2<String> strings = new FixedSizeStack2<>(1);
        strings.push("A");
        System.out.println(strings.pop());
        try {
            System.out.println("Try second pop(): ");
            System.out.println(strings.pop());
        } catch (EmptyStackException e) {
            System.out.println("Stack is empty");
        }
        strings = new FixedSizeStack2<>(1);
        strings.push("A");
        try {
            System.out.println("Try second push():");
            strings.push("B");
        } catch (FullStackException e) {
            System.out.println("Stack is full");
        }
    }
}
/* Output:
A
Try second pop():
Stack is empty
Try second push():
Stack is full
 */