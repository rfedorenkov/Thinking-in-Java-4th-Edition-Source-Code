package exercises.chapter16;

import net.mindview.util.*;

import java.util.Arrays;

import static net.mindview.util.Print.print;

/**
 * Exercise 16
 * Starting with CountingGenerator.java, create a SkipGenerator class
 * that produces new values by incrementing according to a constructor
 * argument. Modify TestArrayGeneration.java to show that your new
 * class works correctly.
 */
class SkipGenerator {
    public static class Boolean implements Generator<java.lang.Boolean> {
        private boolean value;
        private boolean step;

        public Boolean(boolean step) {
            this.step = step;
            value = step;
        }

        @Override
        public java.lang.Boolean next() {
            boolean oldValue = value;
            value = !value;
            return oldValue;
        }
    }

    public static class Byte implements Generator<java.lang.Byte> {
        private byte value = 0;
        private byte step;

        public Byte(byte step) {
            this.step = step;
        }

        @Override
        public java.lang.Byte next() {
            byte oldValue = value;
            value += step;
            return oldValue;
        }
    }

    static char[] chars = ("abcdefghijklmnopqrstuvwxyz" +
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ").toCharArray();

    public static class Character implements Generator<java.lang.Character> {
        int index;
        int step;

        public Character(int step) {
            this.step = step;
        }

        @Override
        public java.lang.Character next() {
            char oldValue = chars[index];
            index = (index + step) % chars.length;
            return oldValue;
        }
    }

    public static class String implements Generator<java.lang.String> {
        private int length;
        Generator<java.lang.Character> cg;

        public String(int step) {
            this(step, 7);
        }

        public String(int step, int length) {
            cg = new Character(step);
            this.length = length;
        }

        @Override
        public java.lang.String next() {
            char[] buf = new char[length];
            for (int i = 0; i < length; i++)
                buf[i] = cg.next();
            return new java.lang.String(buf);
        }
    }

    public static class Short implements Generator<java.lang.Short> {
        private short value = 0;
        private short step;

        public Short(short step) {
            this.step = step;
        }

        @Override
        public java.lang.Short next() {
            short oldValue = value;
            value += step;
            return oldValue;
        }
    }

    public static class Integer implements Generator<java.lang.Integer> {
        private int value = 0;
        private int step;

        public Integer(int step) {
            this.step = step;
        }

        @Override
        public java.lang.Integer next() {
            int oldValue = value;
            value += step;
            return oldValue;
        }
    }

    public static class Long implements Generator<java.lang.Long> {
        private long value = 0;
        private long step;

        public Long(long step) {
            this.step = step;
        }

        @Override
        public java.lang.Long next() {
            long oldValue = value;
            value += step;
            return oldValue;
        }
    }

    public static class Float implements Generator<java.lang.Float> {
        private float value = 0;
        private float step;

        public Float(float step) {
            this.step = step;
        }

        @Override
        public java.lang.Float next() {
            float result = value;
            value += step;
            return result;
        }
    }

    public static class Double implements Generator<java.lang.Double> {
        private double value = 0.0;
        private double step;

        public Double(double step) {
            this.step = step;
        }

        @Override
        public java.lang.Double next() {
            double result = value;
            value += step;
            return result;
        }
    }
}

public class E16_TestSkipArrayGeneration {
    public static void main(String[] args) {
        int size = 6;
        boolean[] a1 = ConvertTo.primitive(Generated.array(
                Boolean.class, new SkipGenerator.Boolean(true), size));
        print("a1 = " + Arrays.toString(a1));
        byte[] a2 = ConvertTo.primitive(
                Generated.array(Byte.class, new SkipGenerator.Byte((byte) 2), size));
        print("a2 = " + Arrays.toString(a2));
        char[] a3 = ConvertTo.primitive(
                Generated.array(Character.class, new SkipGenerator.Character(2), size));
        print("a3 = " + Arrays.toString(a3));
        short[] a4 = ConvertTo.primitive(
                Generated.array(Short.class, new SkipGenerator.Short((short) 2), size));
        print("a4 = " + Arrays.toString(a4));
        int[] a5 = ConvertTo.primitive(
                Generated.array(Integer.class, new SkipGenerator.Integer(2), size));
        print("a5 = " + Arrays.toString(a5));
        long[] a6 = ConvertTo.primitive(
                Generated.array(Long.class, new SkipGenerator.Long(2), size));
        print("a6 = " + Arrays.toString(a6));
        float[] a7 = ConvertTo.primitive(
                Generated.array(Float.class, new SkipGenerator.Float(1.0f), size));
        print("a7 = " + Arrays.toString(a7));
        double[] a8 = ConvertTo.primitive(
                Generated.array(Double.class, new SkipGenerator.Double(2.0), size));
        print("a8 = " + Arrays.toString(a8));
    }
}
/* Output:
a1 = [true, false, true, false, true, false]
a2 = [0, 2, 4, 6, 8, 10]
a3 = [a, c, e, g, i, k]
a4 = [0, 2, 4, 6, 8, 10]
a5 = [0, 2, 4, 6, 8, 10]
a6 = [0, 2, 4, 6, 8, 10]
a7 = [0.0, 1.0, 2.0, 3.0, 4.0, 5.0]
a8 = [0.0, 2.0, 4.0, 6.0, 8.0, 10.0]
 */