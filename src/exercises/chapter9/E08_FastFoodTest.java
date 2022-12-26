package exercises.chapter9;

import polymorphism.Sandwich;

/**
 * Exercise 8
 * Create an interface called FastFood (with appropriate methods)
 * in polymorphism.Sandwich.java, and change Sandwich so it also
 * implements FastFood.
 */
interface FastFood {
    void order();
}

class FastSandwich extends Sandwich implements FastFood {
    @Override
    public void order() {
        System.out.println("Your sandwich is ready!");
    }
}

public class E08_FastFoodTest {
    public static void main(String[] args) {
        FastFood sandwich = new FastSandwich();
        sandwich.order();
    }
}
/* Output:
Meal()
Lunch()
PortableLunch
Bread()
Cheese()
Lettuce()
Sandwich()
Your sandwich is ready!
 */