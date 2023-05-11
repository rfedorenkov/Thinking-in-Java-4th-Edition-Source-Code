package concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

class ExceptionThread2 implements Runnable {
    @Override
    public void run() {
        Thread t = Thread.currentThread();
        System.out.println("run() by " + t);
        System.out.println("eh = " + t.getUncaughtExceptionHandler());
        throw new RuntimeException();
    }
}

class MyUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println("caught " + e);
    }
}

class HandlerThreadFactory implements ThreadFactory {
    @Override
    public Thread newThread(Runnable r) {
        System.out.println(this + " creating new Thread");
        Thread t = new Thread(r);
        System.out.println("created " + t);
        t.setUncaughtExceptionHandler(new MyUncaughtExceptionHandler());
        System.out.println("eh = " + t.getUncaughtExceptionHandler());
        return t;
    }
}

public class CaptureUncaughtException {
    public static void main(String[] args) {
        ExecutorService exec = Executors.newCachedThreadPool(
                new HandlerThreadFactory());
        exec.execute(new ExceptionThread2());
    }
}
/* Output: (90% match)
concurrency.HandlerThreadFactory@13969fbe creating new Thread
created Thread[Thread-0,5,main]
eh = concurrency.MyUncaughtExceptionHandler@1a407d53
run() by Thread[Thread-0,5,main]
eh = concurrency.MyUncaughtExceptionHandler@1a407d53
concurrency.HandlerThreadFactory@13969fbe creating new Thread
created Thread[Thread-1,5,main]
eh = concurrency.MyUncaughtExceptionHandler@458c4345
caught java.lang.RuntimeException
 */