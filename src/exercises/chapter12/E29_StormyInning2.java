package exercises.chapter12;

/**
 * Exercise 29
 * Modify all the exception types in StormyInning.java
 * so that they extend RuntimeException, and show that
 * no exception specifications or try blocks are necessary.
 * Remove the '//!' comments and show how the methods can be
 * compiled without specifications.
 * {ThrowsException}
 */
class BaseballException2 extends RuntimeException {
}

class Foul2 extends BaseballException2 {
}

class Strike2 extends BaseballException2 {
}

abstract class Inning2 {
    public Inning2() throws BaseballException2 {
    }

    public void event() throws BaseballException2 {
    }

    public abstract void atBat();

    public void walk() {
    }
}
class StormException2 extends RuntimeException {
}

class RainedOut2 extends StormException2 {
}

class PopFoul2 extends Foul2 {
}

interface Storm2 {
    public void event();
    public void rainHard();
}

class StormyInning2 extends Inning2 implements Storm2 {
    public StormyInning2() {
    }

    public StormyInning2(String s) {
    }

    @Override public void event() {
    }

    @Override
    public void rainHard() {
    }

    @Override
    public void atBat() {
        throw new PopFoul2();
    }
}

public class E29_StormyInning2 {
    public static void main(String[] args) {
        StormyInning2 si = new StormyInning2();
        si.atBat();
    }
}
/* Output:
Exception in thread "main" exercises.chapter12.PopFoul2
	at exercises.chapter12.StormyInning2.atBat(E29_StormyInning2.java:63)
	at exercises.chapter12.E29_StormyInning2.main(E29_StormyInning2.java:70)
 */