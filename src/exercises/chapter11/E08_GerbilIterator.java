package exercises.chapter11;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Exercise 8
 * Modify Exercise 1 so it uses an Iterator to move
 * through the List while calling hop().
 */
public class E08_GerbilIterator {
    public static void main(String[] args) {
        ArrayList<Gerbil> gerbils = new ArrayList<>();
        for (int i = 0; i < 5; i++)
            gerbils.add(new Gerbil(i));
        Iterator<Gerbil> it = gerbils.iterator();
        while (it.hasNext()) {
            it.next().hop();
        }
    }
}
/* Output:
Gerbil 0 is hopping
Gerbil 1 is hopping
Gerbil 2 is hopping
Gerbil 3 is hopping
Gerbil 4 is hopping
 */