package concurrency;

/**
 * Inheriting directly from the Thread class.
 */
public class SimpleThread extends Thread {
    private int countDown = 5;
    private static int threadCount = 0;

    public SimpleThread() {
        // Store the thread name:
        super(Integer.toString(++threadCount));
        start();
    }

    @Override
    public String toString() {
        return "#" + getName() + "(" + countDown + "), ";
    }

    @Override
    public void run() {
        while (true) {
            System.out.print(this);
            if (--countDown == 0)
                return;
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++)
            new SimpleThread();
    }
}
/* Output: (Sample)
#4(5), #4(4), #4(3), #4(2), #4(1), #1(5), #5(5), #5(4), #1(4), #2(5), #2(4), #3(5), #3(4), #3(3), #2(3), #2(2), #2(1), #1(3), #1(2), #1(1), #5(3), #3(2), #3(1), #5(2), #5(1),
 */