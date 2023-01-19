package exercises.chapter11;

import java.util.PriorityQueue;
import java.util.Random;

/**
 * Exercise 28
 * Fill a PriorityQueue (using offer()) with Double values
 * created using java.util.Random, then remove the elements
 * using poll() and display them.
 */
public class E28_PriorityQueueWithDoubles {
    public static void main(String[] args) {
        Random rand = new Random(47);
        PriorityQueue<Double> priorityQueue = new PriorityQueue<>();
        for (int i = 0; i < 10; i++)
            priorityQueue.offer(rand.nextDouble());
        while (!priorityQueue.isEmpty())
            System.out.println(priorityQueue.poll());
    }
}
/* Output:
0.0508673570556899
0.16020656493302599
0.18847866977771732
0.2613610344283964
0.2678662084200585
0.5166020801268457
0.5309454508634242
0.7271157860730044
0.7620665811558285
0.8037155449603999
 */