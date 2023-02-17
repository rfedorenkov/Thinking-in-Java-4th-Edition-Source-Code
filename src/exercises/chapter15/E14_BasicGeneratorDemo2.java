package exercises.chapter15;

import generics.CountedObject;
import net.mindview.util.BasicGenerator;
import net.mindview.util.Generator;

/**
 * Exercise 14
 * Modify BasicGeneratorDemo.java to use the explicit
 * form of creation for the Generator (that is, use
 * the explicit constructor instead of the generic
 * create() method).
 */
public class E14_BasicGeneratorDemo2 {
    public static void main(String[] args) {
        Generator<CountedObject> gen = new BasicGenerator<>(CountedObject.class);
        for (int i = 0; i < 5; i++)
            System.out.println(gen.next());
    }
}
/* Output:
CountedObject 0
CountedObject 1
CountedObject 2
CountedObject 3
CountedObject 4
 */