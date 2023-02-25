package containers;

import java.lang.ref.*;
import java.util.LinkedList;

/**
 * Demonstrates Reference objects.
 */
class VeryBig {
    private static final int SIZE = 10000;
    private long[] la = new long[SIZE];
    private String ident;

    public VeryBig(String id) {
        ident = id;
    }

    @Override
    public String toString() {
        return ident;
    }

    @Override
    protected void finalize() {
        System.out.println("Finalizing " + ident);
    }
}

public class References {
    private static ReferenceQueue<VeryBig> rq = new ReferenceQueue<>();

    public static void checkQueue() {
        Reference<? extends VeryBig> inq = rq.poll();
        if (inq != null)
            System.out.println("In queue: " + inq.get());
    }

    public static void main(String[] args) {
        int size = 10;
        // Or, choose size via the command line:
        if (args.length > 0)
            size = new Integer(args[0]);
        LinkedList<SoftReference<VeryBig>> sa = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            sa.add(new SoftReference<>(new VeryBig("Soft " + i), rq));
            System.out.println("Just created: " + sa.getLast());
            checkQueue();
        }

        LinkedList<WeakReference<VeryBig>> wa = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            wa.add(new WeakReference<>(new VeryBig("Weak " + i), rq));
            System.out.println("Just created: " + wa.getLast());
            checkQueue();
        }
        SoftReference<VeryBig> s = new SoftReference<>(new VeryBig("Soft"));
        WeakReference<VeryBig> w = new WeakReference<>(new VeryBig("Weak"));
        System.gc();
        LinkedList<PhantomReference<VeryBig>> pa = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            pa.add(new PhantomReference<>(new VeryBig("Phantom " + i), rq));
            System.out.println("Just created: " + wa.getLast());
            checkQueue();
        }
    }
}
/* Output:
Just created: java.lang.ref.SoftReference@17f6480
Just created: java.lang.ref.SoftReference@2d6e8792
Just created: java.lang.ref.SoftReference@2812cbfa
Just created: java.lang.ref.SoftReference@2acf57e3
Just created: java.lang.ref.SoftReference@506e6d5e
Just created: java.lang.ref.SoftReference@96532d6
Just created: java.lang.ref.SoftReference@3796751b
Just created: java.lang.ref.SoftReference@67b64c45
Just created: java.lang.ref.SoftReference@4411d970
Just created: java.lang.ref.SoftReference@6442b0a6
Just created: java.lang.ref.WeakReference@60f82f98
Just created: java.lang.ref.WeakReference@35f983a6
Just created: java.lang.ref.WeakReference@7f690630
Just created: java.lang.ref.WeakReference@edf4efb
Just created: java.lang.ref.WeakReference@2f7a2457
Just created: java.lang.ref.WeakReference@566776ad
Just created: java.lang.ref.WeakReference@6108b2d7
Just created: java.lang.ref.WeakReference@1554909b
Just created: java.lang.ref.WeakReference@6bf256fa
Just created: java.lang.ref.WeakReference@6cd8737
Finalizing Weak 1
Finalizing Weak 0
Finalizing Weak 3
Finalizing Weak 2
Finalizing Weak
Finalizing Weak 9
Finalizing Weak 7
Finalizing Weak 6
Finalizing Weak 5
Finalizing Weak 4
Finalizing Weak 8
Just created: java.lang.ref.WeakReference@6cd8737
In queue: null
Just created: java.lang.ref.WeakReference@6cd8737
In queue: null
Just created: java.lang.ref.WeakReference@6cd8737
In queue: null
Just created: java.lang.ref.WeakReference@6cd8737
In queue: null
Just created: java.lang.ref.WeakReference@6cd8737
In queue: null
Just created: java.lang.ref.WeakReference@6cd8737
In queue: null
Just created: java.lang.ref.WeakReference@6cd8737
In queue: null
Just created: java.lang.ref.WeakReference@6cd8737
In queue: null
Just created: java.lang.ref.WeakReference@6cd8737
In queue: null
Just created: java.lang.ref.WeakReference@6cd8737
In queue: null
 */