package exercises.chapter15;

import generics.Holder;

/**
 * Exercise 30
 * Create a Holder for each of the primitive wrapper types,
 * and show that autoboxing and autounboxing works for
 * the set() and get() methods of each instance.
 */
public class E30_AutoboxingUnboxingTest {
    public static void main(String[] args) {
        Holder<Byte> holderByte = new Holder<>();
        byte b1 = 1;
        holderByte.set(b1);
        byte b2 = holderByte.get();
        System.out.println(b1 == b2);
        Holder<Short> holderShort = new Holder<>();
        short s1 = 1;
        holderShort.set(s1);
        short s2 = holderShort.get();
        System.out.println(s1 == s2);
        Holder<Integer> holderInteger = new Holder<>();
        int i1 = 1;
        holderInteger.set(i1);
        int i2 = holderInteger.get();
        System.out.println(i1 == i2);
        Holder<Long> holderLong = new Holder<>();
        long l1 = 1L;
        holderLong.set(l1);
        long l2 = holderLong.get();
        System.out.println(l1 == l2);
        Holder<Float> holderFloat = new Holder<>();
        float f1 = 1.0f;
        holderFloat.set(f1);
        float f2 = holderFloat.get();
        System.out.println(f1 == f2);
        Holder<Double> holderDouble = new Holder<>();
        double d1 = 1.0;
        holderDouble.set(d1);
        double d2 = holderDouble.get();
        System.out.println(d1 == d2);
        Holder<Character> holderCharacter = new Holder<>();
        char c1 = 'c';
        holderCharacter.set(c1);
        long c2 = holderCharacter.get();
        System.out.println(c1 == c2);
        Holder<Boolean> holderBoolean = new Holder<>();
        boolean bool1 = false;
        holderBoolean.set(bool1);
        boolean bool2 = holderBoolean.get();
        System.out.println(bool1 == bool2);
    }
}
/* Output:
true
true
true
true
true
true
true
true
 */