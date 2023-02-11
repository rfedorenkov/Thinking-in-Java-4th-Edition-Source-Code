package exercises.chapter14;

import static net.mindview.util.Print.print;

/**
 * Exercise 10
 * Write a program to determine whether an array
 * of char is a primitive type or a true object.
 */
public class E10_PrimitiveOrTrueObjectTest {
    public static void printInfo(Class<?> clazz) {
        print("Class: " + clazz);
        print("Superclass: " + clazz.getSuperclass());
    }

    public static void main(String[] args) {
        char[] chars1 = new char[1];
        print("char[] is a primitive type or a true object?");
        printInfo(chars1.getClass());
        System.out.println("**********");
        Character[] chars2 = new Character[1];
        print("Character[] is a primitive type or a true object?");
        printInfo(chars2.getClass());
        //! char c = 'c';
        //! printInfo(c.getClass()); // Can't do it
        System.out.println("**********");
        char[][][][] chars3 = new char[1][][][];
        print("char[][][][] is a primitive type or a true object?");
        printInfo(chars3.getClass());
    }
}
/* Output:
char[] is a primitive type or a true object?
Class: class [C
Superclass: class java.lang.Object
**********
Character[] is a primitive type or a true object?
Class: class [Ljava.lang.Character;
Superclass: class java.lang.Object
**********
char[][][][] is a primitive type or a true object?
Class: class [[[[C
Superclass: class java.lang.Object
 */