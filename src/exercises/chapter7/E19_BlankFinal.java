package exercises.chapter7;

/**
 * Exercise 19
 * Create a class with a blank final reference to an object.
 * Perform initialization of the blank final inside all constructors.
 * Demonstrate that the final must be initialized before use, and cannot
 * be changed once initialized.
 */
class Human {
    private final String name;

    public Human() {
        this("anonymous");
    }

    public Human(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

//!    public void setName(String name) {
//!        this.name = name; // Error: Can't change reference
//!    }

    @Override
    public String toString() {
        return name;
    }
}

public class E19_BlankFinal {
    public static void main(String[] args) {
        Human human1 = new Human();
        Human human2 = new Human("Jack");
        System.out.println(human1);
        System.out.println(human2);
        //! human1.getName() = "Sarah"; // Error: Can't change reference
    }
}