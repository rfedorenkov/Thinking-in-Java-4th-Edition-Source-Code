package exercises.chapter16;

import net.mindview.util.Generated;
import net.mindview.util.Generator;

import java.math.BigDecimal;
import java.util.Arrays;

/**
 * Exercise 17
 * Create and test a Generator for BigDecimal, and
 * ensure that it works with the Generated methods.
 */
class BigDecimalGenerator implements Generator<BigDecimal> {
    private BigDecimal step;
    private BigDecimal value;

    public BigDecimalGenerator(BigDecimal step) {
        this.step = step;
        value = new BigDecimal("0.0");
    }

    @Override
    public BigDecimal next() {
        BigDecimal oldValue = value;
        value = value.add(step);
        return oldValue;
    }
}

public class E17_BigDecimalGeneratorTest {
    public static void main(String[] args) {
        BigDecimal[] array1 = new BigDecimal[10];
        array1 = Generated.array(array1, new BigDecimalGenerator(new BigDecimal("0.1")));
        System.out.println("array1 = " + Arrays.toString(array1));
        BigDecimal[] array2 = Generated.array(
                BigDecimal.class, new BigDecimalGenerator(new BigDecimal("0.2")), 10);
        System.out.println("array2 = " + Arrays.toString(array2));
    }
}
/* Output:
array1 = [0.0, 0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9]
array2 = [0.0, 0.2, 0.4, 0.6, 0.8, 1.0, 1.2, 1.4, 1.6, 1.8]
 */