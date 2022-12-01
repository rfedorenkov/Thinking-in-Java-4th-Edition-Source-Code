package exercises.chapter2;

/**
 * Exercise 7
 * Turn the Incrementable code fragments into a working program.
 */
public class E07_IncrementableTest {
    public static void main(String[] args) {
        System.out.println("StaticTest.i = " + StaticTest.i);
        StaticTest st1 = new StaticTest();
        StaticTest st2 = new StaticTest();
        System.out.println("st1.i = " + st1.i);
        System.out.println("st2.i = " + st2.i);
        Incrementable sf = new Incrementable();
        sf.increment();
        System.out.println("After calling sf.increment()");
        System.out.println("StaticTest.i = " + StaticTest.i);
        System.out.println("st1.i = " + st1.i);
        System.out.println("st2.i = " + st2.i);
        Incrementable.increment();
        System.out.println("After calling Incrementable.increment()");
        System.out.println("After calling sf.increment()");
        System.out.println("StaticTest.i = " + StaticTest.i);
        System.out.println("st1.i = " + st1.i);
        System.out.println("st2.i = " + st2.i);
    }
}
/* Output:
StaticTest.i = 47
st1.i = 47
st2.i = 47
After calling sf.increment()
StaticTest.i = 48
st1.i = 48
st2.i = 48
After calling Incrementable.increment()
After calling sf.increment()
StaticTest.i = 49
st1.i = 49
st2.i = 49
 */

class StaticTest {
    static int i = 47;
}

class Incrementable {
    static void increment() {
        StaticTest.i++;
    }
}