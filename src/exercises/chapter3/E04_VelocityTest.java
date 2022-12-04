package exercises.chapter3;

/**
 * Exercise 4
 * Write a program that calculates velocity
 * using a constant distance and a constant time.
 */
public class E04_VelocityTest {
    public static void main(String[] args) {
        float distance = 198;
        float time = 2.5f;
        float velocity = Velocity.kilometerPerHour(distance, time);
        System.out.println("Distance: " + distance + " km");
        System.out.println("time: " + time + " h");
        System.out.println("Velocity: " + velocity + " km/h");
    }
}
/* Output:
Distance: 198.0 km
time: 2.5 h
Velocity: 79.2 km/h
 */

class Velocity {
    public static float kilometerPerHour(float distance, float time) {
        if (time == 0) return 0;
        else return distance / time;
    }
}