package exercises.chapter12;

/**
 * Exercise 30
 * Modify main() in Human.java so that the technique
 * in TurnOffChecking.java is used to handle the different
 * types of exceptions.
 */
class Annoyance extends RuntimeException {
}

class Sneeze extends Annoyance {
}

public class E30_Human {
    static void throwRuntimeException(int type) {
        switch (type) {
            case 0:
                throw new Annoyance();
            case 1:
                throw new Sneeze();
            case 2:
                throw new RuntimeException("Where am I?");
            default:
                return;
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            try {
                throwRuntimeException(i);
            } catch (Sneeze s) {
                System.out.println("Sneeze: " + s);
            } catch (Annoyance a) {
                System.out.println("Annoyance: " + a);
            } catch (RuntimeException re) {
                System.out.println("RuntimeException: " + re);
            }
        }
    }
}
/* Output:
Annoyance: exercises.chapter12.Annoyance
Sneeze: exercises.chapter12.Sneeze
RuntimeException: java.lang.RuntimeException: Where am I?
 */