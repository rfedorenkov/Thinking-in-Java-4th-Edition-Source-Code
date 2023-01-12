package exercises.chapter10;

import exercises.chapter10.e06package.DataInterface;
import exercises.chapter10.e06package2.Server;

/**
 * Exercise 6
 * Create an interface with at least one method, in its own package.
 * Create a class in a separate package. Add a protected inner class
 * that implements the interface. In a third package, inherit from your
 * class and, inside a method, return an object of the protected inner
 * class, upcasting to the interface during the return.
 */
public class E06_ProtectedInnerClass extends Server {
    DataInterface get() {
        return new SecretData();
    }

    public static void main(String[] args) {
        E06_ProtectedInnerClass pic = new E06_ProtectedInnerClass();
        DataInterface data = pic.get();
        data.showData();
    }
}
/* Output:
Server.SecretData.showData()
 */