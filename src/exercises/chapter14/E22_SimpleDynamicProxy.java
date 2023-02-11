package exercises.chapter14;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import static net.mindview.util.Print.print;

/**
 * Exercise 22
 * Modify SimpleDynamicProxy.java so that it
 * measures method-call times.
 */
class DynamicProxyHandler implements InvocationHandler {
    private Object proxied;

    public DynamicProxyHandler(Object proxied) {
        this.proxied = proxied;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("**** proxy: " + proxy.getClass() +
                ", method: " + method + ", args: " + args);
        if (args != null)
            for (Object arg : args)
                System.out.println("  " + arg);
        long start = System.nanoTime();
        Object result = method.invoke(proxied, args);
        long duration = System.nanoTime() - start;
        print("Method-call time: " + duration);
        return result;
    }
}

public class E22_SimpleDynamicProxy {
    public static void consumer(Interface iface) {
        iface.doSomething();
        iface.somethingElse("bonobo");
    }

    public static void main(String[] args) {
        RealObject real = new RealObject();
        consumer(real);
        // Insert a proxy and call again:
        Interface proxy = (Interface) Proxy.newProxyInstance(
                Interface.class.getClassLoader(),
                new Class[]{ Interface.class },
                new DynamicProxyHandler(real));
        consumer(proxy);
    }
}
/* Output:
doSomething
somethingElse bonobo
**** proxy: class exercises.chapter14.$Proxy0, method: public abstract void exercises.chapter14.Interface.doSomething(), args: null
doSomething
Method-call time: 22666
**** proxy: class exercises.chapter14.$Proxy0, method: public abstract void exercises.chapter14.Interface.somethingElse(java.lang.String), args: [Ljava.lang.Object;@90f6bfd
  bonobo
somethingElse bonobo
Method-call time: 15375
 */