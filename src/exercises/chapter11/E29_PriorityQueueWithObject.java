package exercises.chapter11;

import java.util.PriorityQueue;

/**
 * Exercise 29
 * Create a simple class that inherits from Object
 * and contains no members, and show that you cannot
 * successfully and multiple elements of that class
 * to a PriorityQueue. This issue will be fully
 * explained in the Containers in Depth chapter.
 * {ClassCastException}
 */
class Simple {
}

public class E29_PriorityQueueWithObject {
    public static void main(String[] args) {
        PriorityQueue<Simple> priorityQueue = new PriorityQueue<>();
        System.out.println("Add the first new Simple to the PriorityQueue");
        priorityQueue.add(new Simple());
        System.out.println("Add the second new Simple to the PriorityQueue");
        priorityQueue.add(new Simple());
    }
}
/* Output:
Add the first new Simple to the PriorityQueue
Exception in thread "main" java.lang.ClassCastException
 */