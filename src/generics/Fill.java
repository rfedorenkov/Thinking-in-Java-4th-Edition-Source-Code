package generics;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Generalizing the FilledList idea.
 * {main: FillTest}
 * Doesn't work with "anything that has an add()." There is
 * no "Addable" interface so we are narrowed to using a Collection.
 * We cannot generalize using generics in this case.
 */
public class Fill {
    public static <T> void fill(Collection<T> collection,
                                Class<? extends T> classToken, int size) {
        // Assumes default constructor:
        for (int i = 0; i < size; i++)
            try {
                collection.add(classToken.newInstance());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
    }
}

class Contract {
    private static long counter = 0;
    private final long id = counter++;

    @Override
    public String toString() {
        return getClass().getName() + " " + id;
    }
}

class TitleTransfer extends Contract {
}

class FillTest {
    public static void main(String[] args) {
        List<Contract> contracts = new ArrayList<>();
        Fill.fill(contracts, Contract.class, 3);
        Fill.fill(contracts, TitleTransfer.class, 2);
        for (Contract c : contracts)
            System.out.println(c);
        SimpleQueue<Contract> contractQueue = new SimpleQueue<>();
        // Won't work. fill() is not generic enough:
        // Fill.fill(contractQueue, Contract.class, 3);
    }
}
/* Output:
generics.Contract 0
generics.Contract 1
generics.Contract 2
generics.TitleTransfer 3
generics.TitleTransfer 4
 */