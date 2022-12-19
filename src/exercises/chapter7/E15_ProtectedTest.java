package exercises.chapter7;

import exercises.chapter7.e15protected.Protected;

/**
 * Exercise 15
 * Create a class with a protected method inside a package.
 * Try to call the protected method outside the package,
 * and explain the results. Now inherit from your class
 * and call the protected method from inside a method of
 * your derived class.
 */
class ExtendProtected extends Protected {
    @Override
    protected void show() {
        System.out.println("extendProtected.show()");
        super.show();
    }
}

public class E15_ProtectedTest {
    public static void main(String[] args) {
        //! new Protected().show(); // outside package, has protected access

        new ExtendProtected().show();
    }
}
/* Output:
extendProtected.show()
protected.show()
 */