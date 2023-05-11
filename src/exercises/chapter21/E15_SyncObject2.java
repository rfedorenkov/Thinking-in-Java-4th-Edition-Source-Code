package exercises.chapter21;

import static net.mindview.util.Print.print;

/**
 * Exercise 15
 * Create a class with three methods containing critical
 * sections that all synchronize on the same object. Create
 * multiple tasks to demonstrate that only one of these
 * methods can run at a time. Now modify the methods so
 * that each one synchronizes on a different object and
 * show that all three methods can be running at once.
 */
class SingleSync {
    public synchronized void f() {
        for (int i = 0; i < 5; i++) {
            print("f()");
            Thread.yield();
        }
    }

    public synchronized void g() {
        for (int i = 0; i < 5; i++) {
            print("g()");
            Thread.yield();
        }
    }

    public synchronized void h() {
        for (int i = 0; i < 5; i++) {
            print("h()");
            Thread.yield();
        }
    }
}

class TripleSync {
    private Object syncObjectOne = new Object();
    private Object syncObjectTwo = new Object();

    public synchronized void f() {
        for (int i = 0; i < 5; i++) {
            print("f()");
            Thread.yield();
        }
    }

    public void g() {
        synchronized (syncObjectOne) {
            for (int i = 0; i < 5; i++) {
                print("g()");
                Thread.yield();
            }
        }
    }

    public void h() {
        synchronized (syncObjectTwo) {
            for (int i = 0; i < 5; i++) {
                print("h()");
                Thread.yield();
            }
        }
    }
}

public class E15_SyncObject2 {
    public static void main(String[] args) throws Exception {
        System.out.println("Single test");
        SingleSync single = new SingleSync();
        Thread t1 = new Thread() {
            @Override
            public void run() {
                single.f();
            }
        };
        t1.start();
        Thread t2 = new Thread() {
            @Override
            public void run() {
                single.g();
            }
        };
        t2.start();
        single.h();
        t1.join();
        t2.join();
        System.out.println("Triple test");
        TripleSync tripleSync = new TripleSync();
        new Thread() {
            @Override
            public void run() {
                single.f();
            }
        }.start();
        new Thread() {
            @Override
            public void run() {
                single.g();
            }
        }.start();
        tripleSync.h();
    }
}
/* Output: (Sample)
Single test
f()
f()
f()
f()
f()
h()
h()
h()
h()
h()
g()
g()
g()
g()
g()
Triple test
f()
f()
f()
f()
f()
h()
h()
g()
g()
g()
h()
g()
h()
h()
g()
 */