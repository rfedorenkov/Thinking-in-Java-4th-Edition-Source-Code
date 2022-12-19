package exercises.chapter7;

/**
 * Prove that base-class constructors are (a) always called
 * and (b) called before derived-class constructors.
 */
class One {
    One() {
        System.out.println("One()");
    }
}

class Two extends One {
    Two() {
        System.out.println("Two()");
    }
}

class Three extends Two {
    Three() {
        System.out.println("Three()");
    }
}

public class E04_ConstructorsTest {
    public static void main(String[] args) {
        System.out.println("Test new One()");
        One one = new One();
        System.out.println("Test new Two()");
        Two two = new Two();
        System.out.println("Test new Three()");
        Three three = new Three();
    }
}
/* Output:
Test new One()
One()
Test new Two()
One()
Two()
Test new Three()
One()
Two()
Three()
 */