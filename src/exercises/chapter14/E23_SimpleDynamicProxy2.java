package exercises.chapter14;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Exercise 23
 * Inside invoke() in SimpleDynamicProxy.java, try to print
 * the proxy argument and explain what happens.
 * {Throws StackOverflowError}
 */
class DynamicProxyHandler2 implements InvocationHandler {
    private Object proxied;

    public DynamicProxyHandler2(Object proxied) {
        this.proxied = proxied;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println(proxy); // StackOverflowError
        return method.invoke(proxied, args);
    }
}

public class E23_SimpleDynamicProxy2 {
    public static void consumer(Interface iface) {
        iface.doSomething();
        iface.somethingElse("bonobo");
    }

    public static void main(String[] args) {
        Interface proxy = (Interface) Proxy.newProxyInstance(
                Interface.class.getClassLoader(),
                new Class[]{ Interface.class },
                new DynamicProxyHandler2(new RealObject()));
        consumer(proxy);
    }
}