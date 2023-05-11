package exercises.chapter21;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import static net.mindview.util.Print.print;

/**
 * Exercise 16
 * Modify Exercise 15 to use explicit Lock objects.
 */
class LockSingleSync {
    private Lock lock = new ReentrantLock();

    public void f() {
        lock.lock();
        try {
            for (int i = 0; i < 5; i++) {
                print("f()");
                Thread.yield();
            }
        } finally {
            lock.unlock();
        }
    }

    public void g() {
        lock.lock();
        try {
            for (int i = 0; i < 5; i++) {
                print("g()");
                Thread.yield();
            }
        } finally {
            lock.unlock();
        }
    }

    public void h() {
        lock.lock();
        try {
            for (int i = 0; i < 5; i++) {
                print("h()");
                Thread.yield();
            }
        } finally {
            lock.unlock();
        }
    }
}

class LockTripleSync {
    private Lock lockOne = new ReentrantLock();
    private Lock lockTwo = new ReentrantLock();
    private Lock lockThree = new ReentrantLock();

    public void f() {
        lockOne.lock();
        try {
            for (int i = 0; i < 5; i++) {
                print("f()");
                Thread.yield();
            }
        } finally {
            lockOne.unlock();
        }
    }

    public void g() {
        lockTwo.lock();
        try {
            for (int i = 0; i < 5; i++) {
                print("g()");
                Thread.yield();
            }
        } finally {
            lockTwo.unlock();
        }
    }

    public void h() {
        lockThree.lock();
        try {
            for (int i = 0; i < 5; i++) {
                print("h()");
                Thread.yield();
            }
        } finally {
            lockThree.unlock();
        }
    }
}
public class E16_LockSyncObject {
    public static void main(String[] args) throws Exception {
        System.out.println("Single test");
        LockSingleSync single = new LockSingleSync();
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
        LockTripleSync tripleSync = new LockTripleSync();
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
/* Output: (Sample) {
Single test
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
f()
f()
f()
f()
f()
Triple test
f()
f()
f()
f()
h()
h()
f()
h()
h()
g()
g()
g()
g()
g()
h()
 */