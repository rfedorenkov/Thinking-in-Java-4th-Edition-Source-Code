package exercises.chapter2;

/**
 * Exercise 6
 * Write a program that includes and calls the
 * storage() method defined as a code fragment in
 * this chapter
 */
public class E06_Storage {
    public static void main(String[] args) {
        E06_Storage st = new E06_Storage();
        int storage = st.storage("Hello, World");
        System.out.println("storage = " + storage);
    }

    int storage(String s) {
        return s.length() * 2;
    }
}
/* Output:
storage = 24
 */