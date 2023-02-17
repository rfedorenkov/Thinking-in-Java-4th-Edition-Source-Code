package exercises.chapter15;

import java.util.Date;

/**
 * Exercise 37
 * Add a new mixin class Colored to Mixins.java,
 * mix it in to Mixin, and show that it work.
 */
interface TimeStamped {
    long getStamp();
}

class TimeStampedImp implements TimeStamped {
    private final long timeStamp;

    public TimeStampedImp() {
        timeStamp = new Date().getTime();
    }

    @Override
    public long getStamp() {
        return timeStamp;
    }
}

interface SerialNumbered {
    long getSerialNumber();
}

class SerialNumberedImp implements SerialNumbered {
    private static long counter = 1;
    private final long serialNumber = counter++;

    @Override
    public long getSerialNumber() {
        return serialNumber;
    }
}

interface Basic {
    public void set(String val);
    public String get();
}

class BasicImp implements Basic {
    private String value;

    @Override
    public void set(String val) {
        value = val;
    }

    @Override
    public String get() {
        return value;
    }
}

interface Color {
    String getColor();
    void setColor(String color);
}

class Colored implements Color {
    private String color;

    Colored() {
        this.color = "Blue";
    }

    @Override
    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String getColor() {
        return color;
    }
}

class Mixin extends BasicImp implements TimeStamped, SerialNumbered, Color {
    private TimeStamped timeStamp = new TimeStampedImp();
    private SerialNumbered serialNumber = new SerialNumberedImp();
    private Color color = new Colored();

    @Override
    public long getStamp() {
        return timeStamp.getStamp();
    }

    @Override
    public long getSerialNumber() {
        return serialNumber.getSerialNumber();
    }

    @Override
    public String getColor() {
        return color.getColor();
    }

    @Override
    public void setColor(String color) {
        this.color.setColor(color);
    }
}

public class E37_Mixins2 {
    public static void main(String[] args) {
        Mixin mixin1 = new Mixin(), mixin2 = new Mixin();
        mixin1.set("test string 1");
        mixin2.set("test string 2");
        mixin2.setColor("Red");
        System.out.println(mixin1.get() + " " + mixin1.getStamp() + " " +
                mixin1.getSerialNumber() + " " + mixin1.getColor());
        System.out.println(mixin2.get() + " " + mixin2.getStamp() + " " +
                mixin2.getSerialNumber() + " " + mixin2.getColor());
    }
}
/* Output: (Sample)
test string 1 1676399711688 1 Blue
test string 2 1676399711688 2 Red
 */