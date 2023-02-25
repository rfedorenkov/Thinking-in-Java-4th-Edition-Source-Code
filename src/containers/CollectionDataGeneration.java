package containers;

import net.mindview.util.CollectionData;
import net.mindview.util.RandomGenerator;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Using the Generators defined in the Arrays chapter.
 */
public class CollectionDataGeneration {
    public static void main(String[] args) {
        System.out.println(new ArrayList<String>(
                CollectionData.list( // Convenience method
                        new RandomGenerator.String(9), 10)));
        System.out.println(new HashSet<Integer>(
                new CollectionData<>(
                        new RandomGenerator.Integer(), 10)));
    }
}
/* Output:
[YNzbrnyGc, FOWZnTcQr, GseGZMmJM, RoEsuEcUO, neOEdLsmw, HLGEahKcx, rEqUCBbkI, naMesbtWH, kjUrUkZPg, wsqPzDyCy]
[2017, 8037, 871, 7882, 6090, 4779, 299, 573, 4367, 3455]
 */