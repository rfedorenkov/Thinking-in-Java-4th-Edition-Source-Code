package exercises.chapter11;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Exercise 27
 * Write a class called Command that contains a String
 * and has a method operation() that displays the String.
 * Write a second class with a method that fills a Queue
 * with Command objects and returns it. Pass the filled
 * Queue to a method in a third class that consumes
 * the objects in the Queue and calls their operation() methods.
 */
class Command {
    private final String cmd;

    public Command(String s) {
        this.cmd = s;
    }

    void operation() {
        System.out.println(cmd);
    }
}

class Producer {
    public static Queue<Command> produce(Command... commands) {
        Queue<Command> queue = new LinkedList<>();
        for (Command cmd : commands)
            queue.offer(cmd);
        return queue;
    }
}

class Consumer {
    public static void consume(Queue<Command> queue) {
        while (queue.peek() != null)
            queue.remove().operation();
    }
}

public class E27_CommandQueue {
    public static void main(String[] args) {
        Queue<Command> queue = Producer.produce(
                new Command("create"), new Command("read"),
                new Command("update"), new Command("delete"));
        Consumer.consume(queue);
    }
}
/* Output:
create
read
update
delete
 */