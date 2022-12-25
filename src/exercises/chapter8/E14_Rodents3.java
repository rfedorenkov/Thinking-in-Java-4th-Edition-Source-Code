package exercises.chapter8;

import static net.mindview.util.Print.print;

/**
 * Exercise 14
 * Modify Exercise 12 so one of the member objects
 * is a shared object with reference counting, and
 * demonstrate that it works properly.
 */
class Shared2 {
    private int refcount = 0;
    private static long counter = 0;
    private final long id = counter++;

    public Shared2() {
        print("Creating " + this);
    }

    public void addRef() {
        refcount++;
    }

    protected void dispose() {
        if (--refcount == 0)
            print("Disposing " + this);
    }

    @Override
    public String toString() {
        return "Shared " + id;
    }
}

class Description3 {
    Description3(String s) {
        print("Description() " + s);
    }
}

class Rodent3 {
    private Description3 d = new Description3("Simple rodent");
    private static long counter = 0;
    private final long id = counter++;
    private Shared2 shared;

    Rodent3(Shared2 shared) {
        print("Rodent constructor " + id);
        this.shared = shared;
        shared.addRef();
    }

    public void eat() {
        print("Rodent.eat()");
    }

    public void sleep() {
        print("Rodent.sleep()");
    }

    @Override
    public String toString() {
        return "Rodent " + id;
    }

    protected void dispose() {
        print("disposing " + this);
        shared.dispose();
    }
}

class Mouse3 extends Rodent3 {
    private Description3 d = new Description3("Gray mouse");

    Mouse3(Shared2 shared) {
        super(shared);
        print("Mouse constructor");
    }

    @Override
    public void eat() {
        print("Mouse.eat()");
    }

    @Override
    public void sleep() {
        print("Mouse.sleep()");
    }

    @Override
    public String toString() {
        return "Mouse - " + super.toString();
    }
}

class Gerbil3 extends Rodent3 {
    private Description3 d = new Description3("White gerbil");

    Gerbil3(Shared2 shared) {
        super(shared);
        print("Gerbil constructor");
    }

    @Override
    public void eat() {
        print("Gerbil.eat()");
    }

    @Override
    public void sleep() {
        print("Gerbil.sleep()");
    }

    @Override
    public String toString() {
        return "Gerbil - " + super.toString();
    }
}

class Hamster3 extends Rodent3 {
    private Description3 d = new Description3("Domestic hamster");

    Hamster3(Shared2 shared) {
        super(shared);
        print("Hamster constructor");
    }

    @Override
    public void eat() {
        print("Hamster.eat()");
    }

    @Override
    public void sleep() {
        print("Hamster.sleep()");
    }

    @Override
    public String toString() {
        return "Hamster - " + super.toString();
    }
}

public class E14_Rodents3 {
    public static void main(String[] args) {
        Shared2 shared = new Shared2();
        Rodent3[] rodents = {
                new Mouse3(shared),
                new Gerbil3(shared),
                new Hamster3(shared)
        };
        for (Rodent3 r : rodents)
            r.dispose();
    }
}
/* Output:
Creating Shared 0
Description() Simple rodent
Rodent constructor 0
Description() Gray mouse
Mouse constructor
Description() Simple rodent
Rodent constructor 1
Description() White gerbil
Gerbil constructor
Description() Simple rodent
Rodent constructor 2
Description() Domestic hamster
Hamster constructor
disposing Mouse - Rodent 0
disposing Gerbil - Rodent 1
disposing Hamster - Rodent 2
Disposing Shared 0
 */