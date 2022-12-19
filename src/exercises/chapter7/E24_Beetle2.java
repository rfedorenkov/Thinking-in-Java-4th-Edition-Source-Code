package exercises.chapter7;

import static net.mindview.util.Print.print;

/**
 * Exercise 24
 * In Beetle.java, inherit a specific type of beetle
 * from class Beetle, following the same format as
 * the existing classes. Trace and explain the output.
 */
class Insect {
    private int i = 9;
    protected int j;

    Insect() {
        print("i = " + i + ", j = " + j);
        j = 39;
    }

    private static int x1 =
            printInit("static Insect.x1 initialized");

    static int printInit(String s) {
        print(s);
        return 47;
    }
}
class Beetle extends Insect {
    private int k = printInit("Beetle.k initialized");

    public Beetle() {
        print("k = " + k);
        print("j = " + j);
    }

    private static int x2 =
            printInit("static Beetle.x2 initialized");
}

class StagBeetle extends Beetle {
    private int x = printInit("StagBeetle.x initialized");

    public StagBeetle() {
        print("x = " + x);
        print("z = " + z);
    }

    private static int z =
            printInit("static StagBeetle.z initialized");
}

public class E24_Beetle2 {
    public static void main(String[] args) {
        print("StagBeetle constructor");
        StagBeetle b = new StagBeetle();
    }
}
/* Output:
StagBeetle constructor
static Insect.x1 initialized
static Beetle.x2 initialized
static StagBeetle.z initialized
i = 9, j = 0
Beetle.k initialized
k = 47
j = 39
StagBeetle.x initialized
x = 47
z = 47
 */