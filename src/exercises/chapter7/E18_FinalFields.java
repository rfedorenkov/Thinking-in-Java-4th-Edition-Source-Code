package exercises.chapter7;

import java.util.Random;

import static net.mindview.util.Print.print;

/**
 * Exercise 18
 * Create a class with a static final field and a final field
 * and demonstrate the difference between the two.
 */
class Boat {
    public static final int MAX_SPEED = 90;
    private static Random random = new Random(47);
    public final int speed = random.nextInt(MAX_SPEED);
    private final int id;

    public Boat(int id) {
        this.id = id;
    }

    public static void checkSpeed(Boat boat) {
        String msg = "The maximum speed of %s is: %d km/h. The fastest boat has speed: %d km/h";
        String format = String.format(msg, boat, boat.speed, MAX_SPEED);
        print(format);
    }

    @Override
    public String toString() {
        return "boat #" + id;
    }
}

public class E18_FinalFields {
    public static void main(String[] args) {
        Boat boat1 = new Boat(1);
        Boat boat2 = new Boat(2);
        Boat boat3 = new Boat(3);
        //! boat1.speed++; // Error: can't change value
        Boat.checkSpeed(boat1);
        Boat.checkSpeed(boat2);
        Boat.checkSpeed(boat3);
        //! Boat.MAX_SPEED++; // Error: can't change value
    }
}
/* Output:
The maximum speed of boat #1 is: 38 km/h. The fastest boat has speed: 90 km/h
The maximum speed of boat #2 is: 35 km/h. The fastest boat has speed: 90 km/h
The maximum speed of boat #3 is: 13 km/h. The fastest boat has speed: 90 km/h
 */