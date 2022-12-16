package exercises.chapter6;

/**
 * Exercise 6
 * Create one class with protected data, and a second class
 * in the same file with a method that manipulates that protected data.
 */
class ProtectedData {
    protected int data = 99;

    protected void showData() {
        System.out.println(data);
    }
}

class SomeClass {
    public static void changeData(ProtectedData p, int newValue) {
        p.showData();
        System.out.println("data changed");
        p.data = newValue;
        p.showData();
    }
}

public class E06_ProtectedData {
    public static void main(String[] args) {
        ProtectedData data = new ProtectedData();
        SomeClass.changeData(data, 200);
    }
}
/* Output:
99
data changed
200
 */