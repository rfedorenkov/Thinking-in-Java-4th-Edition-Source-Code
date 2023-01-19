package exercises.chapter11;

import java.util.ArrayList;

/**
 * Exercise 1
 * Create a new class called Gerbil with an int gerbilNumber
 * initialized in the constructor. Give it a method called hop()
 * that prints out which gerbil number this is, and that it's
 * hopping. Create an ArrayList and add Gerbil objects to
 * the List. Now use the get() method to move through the List
 * and call hop() for each Gerbil.
 */
class Gerbil {
    private int gerbilNumber;

    Gerbil(int gerbilNumber) {
        this.gerbilNumber = gerbilNumber;
    }

    public void hop() {
        System.out.println("Gerbil " + gerbilNumber + " is hopping");
    }
}
public class E01_GerbilTest {
    public static void main(String[] args) {
        ArrayList<Gerbil> gerbils = new ArrayList<>();
        for (int i = 0; i < 5; i++)
            gerbils.add(new Gerbil(i));
        for (int i = 0; i < gerbils.size(); i++)
            gerbils.get(i).hop();
    }
}
/* Output:
Gerbil 0 is hopping
Gerbil 1 is hopping
Gerbil 2 is hopping
Gerbil 3 is hopping
Gerbil 4 is hopping
 */