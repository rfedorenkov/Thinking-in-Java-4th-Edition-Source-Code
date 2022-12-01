package exercises.chapter2;

/**
 * Exercise 8
 * Write a program that demonstrates that,
 * no matter how many objects you create
 * of a particular class, there is only one instance
 * of a particular static field in that class.
 */
public class E08_StaticTest {
    public static void main(String[] args) {
        StaticClass st1 = new StaticClass();
        StaticClass st2 = new StaticClass();
        StaticClass st3 = new StaticClass();
        StaticClass st4 = new StaticClass();
        StaticClass st5 = new StaticClass();
        System.out.println("StaticClass.i = " + StaticClass.i);
        System.out.println("st1.i = " + st1.i);
        System.out.println("st2.i = " + st2.i);
        System.out.println("st3.i = " + st3.i);
        System.out.println("st4.i = " + st4.i);
        System.out.println("st5.i = " + st5.i);
        System.out.println("After calling StaticClass.i++");
        StaticClass.i++;
        System.out.println("StaticClass.i = " + StaticClass.i);
        System.out.println("st1.i = " + st1.i);
        System.out.println("st2.i = " + st2.i);
        System.out.println("st3.i = " + st3.i);
        System.out.println("st4.i = " + st4.i);
        System.out.println("st5.i = " + st5.i);
    }
}
/* Output:
StaticClass.i = 47
st1.i = 47
st2.i = 47
st3.i = 47
st4.i = 47
st5.i = 47
After calling StaticClass.i++
StaticClass.i = 48
st1.i = 48
st2.i = 48
st3.i = 48
st4.i = 48
st5.i = 48
 */

class StaticClass {
    static int i = 47;
}