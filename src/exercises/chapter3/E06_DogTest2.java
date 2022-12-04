package exercises.chapter3;

/**
 * Exercise 6
 * Following Exercise 5 assign, a new Dog reference to spot's object.
 * Test for comparison using == and equals() for all references.
 */
public class E06_DogTest2 {
    public static void compare(Dog dog1, Dog dog2, String msg) {
        System.out.println(msg);
        System.out.println("Compare == : " + (dog1 == dog2));
        System.out.println("Compare equals() : " + dog1.equals(dog2));
        System.out.println("Compare == (names) : " + (dog1.name == dog2.name));
        System.out.println("Compare equals() (names) : " + dog1.name.equals(dog2.name));
        System.out.println("Compare == (says) : " + (dog1.says == dog2.says));
        System.out.println("Compare equals() (says) : " + dog1.equals(dog2));
        System.out.println("***");
    }

    public static void main(String[] args) {
        Dog dog1 = new Dog();
        dog1.name = "spot";
        dog1.says = "Ruff!";
        Dog dog2 = new Dog();
        dog2.name = "scruffy";
        dog2.says = "Wurf!";
        Dog dog3 = dog1;
        compare(dog1, dog2, "Comparing dog1 and dog2:");
        compare(dog1, dog3, "Comparing dog1 and dog3:");
        compare(dog2, dog3, "Comparing dog2 and dog3:");
    }

    static class Dog {
        String name;
        String says;
    }
}
/* Output:
Comparing dog1 and dog2:
Compare == : false
Compare equals() : false
Compare == (names) : false
Compare equals() (names) : false
Compare == (says) : false
Compare equals() (says) : false
***
Comparing dog1 and dog3:
Compare == : true
Compare equals() : true
Compare == (names) : true
Compare equals() (names) : true
Compare == (says) : true
Compare equals() (says) : true
***
Comparing dog2 and dog3:
Compare == : false
Compare equals() : false
Compare == (names) : false
Compare equals() (names) : false
Compare == (says) : false
Compare equals() (says) : false
***
 */