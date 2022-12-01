package exercises.chapter2;

/**
 * Exercise 9
 * Write a program that demonstrates that
 * autoboxing works for all the primitive types
 * and their wrappers.
 */
public class E09_Autoboxing {
    public static void main(String[] args) {
        byte b = 1;
        short sh = 200;
        int i = 4000;
        long l = 80000;
        float f = 3.14f;
        double d = 9.11;
        char c = '@';
        boolean bool = true;

        Byte b2 = b;
        Short sh2 = sh;
        Integer i2 = i;
        Long l2 = l;
        Float f2 = f;
        Double d2 = d;
        Character c2 = c;
        Boolean bool2 = bool;
        System.out.println("Autoboxing...");
        System.out.println("Byte b2 = " + b2);
        System.out.println("Short sh2 = " + sh2);
        System.out.println("Integer i2 = " + i2);
        System.out.println("Long l2 = " + l2);
        System.out.println("Float f2 = " + f2);
        System.out.println("Double d2 = " + d2);
        System.out.println("Character c2 = " + c2);
        System.out.println("Boolean bool2 = " + bool2);
    }
}
/* Output:
Autoboxing...
Byte b2 = 1
Short sh2 = 200
Integer i2 = 4000
Long l2 = 80000
Float f2 = 3.14
Double d2 = 9.11
Character c2 = @
Boolean bool2 = true
 */