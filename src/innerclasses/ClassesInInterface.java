package innerclasses;

/**
 * {main: ClassInInterface$Test}
 */
public interface ClassesInInterface {
    void howdy();

    class Test implements ClassesInInterface {
        @Override
        public void howdy() {
            System.out.println("Howdy!");
        }

        public static void main(String[] args) {
            new Test().howdy();
        }
    }
}
/* Output:
Howdy!
 */