package exercises.chapter3;

/**
 * Exercise 5
 * Create a class called Dog containing two Strings: name and says.
 * In main(), create two dog objects with names "spot" (who says "Ruff!") and
 * "scruffy" (who says "Wurf!"). Then display their names and what they say.
 */
public class E05_DogTest {
    public static void main(String[] args) {
        Dog dog1 = new Dog();
        dog1.name = "spot";
        dog1.says = "Ruff!";
        Dog dog2 = new Dog();
        dog2.name = "scruffy";
        dog2.says = "Wurf!";
        System.out.println(dog1.name + ": " + dog1.says);
        System.out.println(dog2.name + ": " + dog2.says);
    }

    static class Dog {
        String name;
        String says;
    }
}
/* Output:
spot: Ruff!
scruffy: Wurf!
 */