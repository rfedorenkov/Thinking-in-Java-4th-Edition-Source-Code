package exercises.chapter15;

/**
 * Exercise 32
 * Verify that FixedSizeStack in GenericCast.java generates
 * exceptions if you try go out of its bounds. Does this mean
 * that bounds-checking code is not required?
 */
class FixedSizeStack<T> {
    private int index = 0;
    private Object[] storage;

    public FixedSizeStack(int size) {
        storage = new Object[size];
    }

    public void push(T item) {
        storage[index++] = item;
    }

    @SuppressWarnings("unchecked")
    public T pop() {
        return (T) storage[--index];
    }
}

public class E32_FixedSizeStackTest {
    public static void main(String[] args) {
        FixedSizeStack<String> strings = new FixedSizeStack<>(1);
        strings.push("A");
        System.out.println(strings.pop());
        try {
            System.out.println("Try second pop(): ");
            System.out.println(strings.pop());
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e);
        }
        strings = new FixedSizeStack<>(1);
        strings.push("A");
        try {
            System.out.println("Try second push():");
            strings.push("B");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println(e);
        }
    }
}
/* Output:
A
Try second pop():
java.lang.ArrayIndexOutOfBoundsException: Index -1 out of bounds for length 1
Try second push():
java.lang.ArrayIndexOutOfBoundsException: Index 1 out of bounds for length 1
 */