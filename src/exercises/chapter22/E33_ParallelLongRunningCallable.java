package exercises.chapter22;

import net.mindview.util.TwoTuple;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.*;

import static net.mindview.util.SwingConsole.run;

/**
 * Exercise 33
 * Modify InterruptableLongRunningCallable.java so that
 * it runs all the tasks in parallel rather than sequentially.
 */
class Task implements Runnable {
    private static int counter = 0;
    private final int id = counter++;

    @Override
    public void run() {
        System.out.println(this + " started");
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            System.out.println(this + " interrupted");
            return;
        }
        System.out.println(this + " completed");
    }

    @Override
    public String toString() {
        return "Task " + id;
    }

    public long id() {
        return id;
    }
}

class CallableTask extends Task implements Callable<String> {
    @Override
    public String call() throws Exception {
        run();
        return "Return value of " + this;
    }
}

class TaskManager<R, C extends Callable<R>> extends ArrayList<TwoTuple<Future<R>, C>> {
    private ExecutorService exec = Executors.newCachedThreadPool();

    public void add(C task) {
        add(new TwoTuple<>(exec.submit(task), task));
    }

    public List<R> getResults() {
        Iterator<TwoTuple<Future<R>, C>> items = iterator();
        List<R> results = new ArrayList<>();
        while (items.hasNext()) {
            TwoTuple<Future<R>, C> item = items.next();
            if (item.first.isDone()) {
                try {
                    results.add(item.first.get());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                items.remove();
            }
        }
        return results;
    }

    public List<String> purge() {
        Iterator<TwoTuple<Future<R>, C>> items = iterator();
        List<String> results = new ArrayList<>();
        while (items.hasNext()) {
            TwoTuple<Future<R>, C> item = items.next();
            // Leave completed tasks for results reporting:
            if (!item.first.isDone()) {
                results.add("Cancelling " + item.second);
                item.first.cancel(true); // May interrupt
                items.remove();
            }
        }
        return results;
    }
}

public class E33_ParallelLongRunningCallable extends JFrame {
    private JButton
            b1 = new JButton("Start Long Running Task"),
            b2 = new JButton("End Long Running Task"),
            b3 = new JButton("Get results");
    private TaskManager<String, CallableTask> manager = new TaskManager<>();

    public E33_ParallelLongRunningCallable() {
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CallableTask task = new CallableTask();
                manager.add(task);
                System.out.println(task + " added to the queue");
            }
        });
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (String result : manager.purge())
                    System.out.println(result);
            }
        });
        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Sample call to a Task method:
                for (TwoTuple<Future<String>, CallableTask> tt : manager)
                    tt.second.id(); // No cast required;
                for (String result : manager.getResults())
                    System.out.println(result);
            }
        });
        setLayout(new FlowLayout());
        add(b1);
        add(b2);
        add(b3);
    }

    public static void main(String[] args) {
        run(new E33_ParallelLongRunningCallable(), 200, 150);
    }
}