package exercises.chapter14;

import generics.coffee.Coffee;
import generics.coffee.CoffeeGenerator;
import net.mindview.util.TypeCounter;

import static net.mindview.util.Print.print;
import static net.mindview.util.Print.printnb;

/**
 * Exercise 12
 * Use TypeCounter with the CoffeeGenerator.java
 * class in the Generics chapter.
 */
public class E12_CoffeeCount {
    public static void main(String[] args) {
        TypeCounter counter = new TypeCounter(Coffee.class);
        for (Coffee coffee : new CoffeeGenerator(20)) {
            printnb(coffee + " ");
            counter.count(coffee);
        }
        print();
        print(counter);
    }
}
/* Output:
Americano 0 Latte 1 Americano 2 Mocha 3 Mocha 4 Breve 5 Americano 6 Latte 7 Cappuccino 8 Cappuccino 9 Americano 10 Americano 11 Mocha 12 Breve 13 Breve 14 Americano 15 Americano 16 Mocha 17 Latte 18 Americano 19
{Cappuccino=2, Americano=8, Mocha=4, Breve=3, Coffee=20, Latte=3}
 */