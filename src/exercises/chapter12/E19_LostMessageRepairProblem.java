package exercises.chapter12;

/**
 * Exercise 19
 * Repair the problem in LostMessage.java by
 * guarding the call in the finally clause.
 */
public class E19_LostMessageRepairProblem {
    public static void main(String[] args) {
        try {
            LostMessage2 lm = new LostMessage2();
            try {
                lm.f();
            } finally {
                try {
                    lm.dispose();
                } catch (HoHumException e) {
                    System.out.println(e);
                }
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
/* Output:
A trivial exception
A very important exception!
 */