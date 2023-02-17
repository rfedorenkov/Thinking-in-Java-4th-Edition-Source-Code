package exercises.chapter15;

import java.util.ArrayList;
import java.util.List;

/**
 * Exercise 27
 * Show that covariance doesn't work with Lists,
 * using Numbers and Integers, then introduce wildcards.
 */
public class E27_GenericsAndCovariance2 {
    public static void main(String[] args) {
        // Compile Error: incompatible types:
        // List<Number> numbers = new ArrayList<Integer>();

        // Wildcards allow covariance:
        List<? extends Number> numbers = new ArrayList<Integer>();
        // Compile Error: can't add any type of object:
        // numbers.add(Integer.valueOf(1));
        // numbers.add(Double.valueOf(1.1d));
        // numbers.add(new Object());
        numbers.add(null); // Legal but uninteresting
        // We know that it returns at least Number:
        Number number = numbers.get(0);
    }
}