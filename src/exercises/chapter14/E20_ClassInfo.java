package exercises.chapter14;

import static net.mindview.util.Print.print;

/**
 * Exercise 20
 * Look up the interface for java.lang.Class in the JDK
 * documentation from java.sun.com. Write a program that
 * takes the name of a class as a command-line argument,
 * then uses the Class methods to dump all the information
 * available for that class. Test your program with a standard
 * library class and a class you create.
 * {Args: java.util.ArrayList exercises.chapter14.FancyToy2}
 */
public class E20_ClassInfo {
    private static String usage = "usage:\n" +
            "exercises.chapter14.E20_ClassInfo qualified.class.name\n" +
            "To show all methods and fields in class.\n";

    private static void printInfo(String msg, Object[] values) {
        print(msg);
        for (Object value : values)
            print(value);
    }

    public static void aboutClass(Class<?> c) {
        print("View class: " + c);
        print("clazz.getSuperclass(): " + c.getSuperclass());
        printInfo("clazz.getDeclaredConstructors():", c.getDeclaredConstructors());
        printInfo("clazz.getClasses():", c.getClasses());
        printInfo("clazz.getDeclaredAnnotations():", c.getDeclaredAnnotations());
        printInfo("clazz.getDeclaredMethods():", c.getDeclaredMethods());
        printInfo("clazz.getDeclaredFields():", c.getDeclaredFields());
        printInfo("clazz.getInterfaces()", c.getInterfaces());
        if (c.getSuperclass() != null) {
            print();
            aboutClass(c.getSuperclass());
        }
    }

    public static void main(String[] args) {
        if (args.length < 1) {
            print(usage);
            System.exit(0);
        }

        try {
            for (String arg : args) {
                aboutClass(Class.forName(arg));
                print();
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
/* Output:
View class: class java.util.ArrayList
clazz.getSuperclass(): class java.util.AbstractList
clazz.getDeclaredConstructors():
public java.util.ArrayList(java.util.Collection)
public java.util.ArrayList()
public java.util.ArrayList(int)
clazz.getClasses():
clazz.getDeclaredAnnotations():
clazz.getDeclaredMethods():
public boolean java.util.ArrayList.add(java.lang.Object)
private void java.util.ArrayList.add(java.lang.Object,java.lang.Object[],int)
public void java.util.ArrayList.add(int,java.lang.Object)
public boolean java.util.ArrayList.remove(java.lang.Object)
public java.lang.Object java.util.ArrayList.remove(int)
public java.lang.Object java.util.ArrayList.get(int)
public boolean java.util.ArrayList.equals(java.lang.Object)
public int java.util.ArrayList.hashCode()
public java.lang.Object java.util.ArrayList.clone()
public int java.util.ArrayList.indexOf(java.lang.Object)
public void java.util.ArrayList.clear()
public int java.util.ArrayList.lastIndexOf(java.lang.Object)
public boolean java.util.ArrayList.isEmpty()
public void java.util.ArrayList.replaceAll(java.util.function.UnaryOperator)
public int java.util.ArrayList.size()
public java.util.List java.util.ArrayList.subList(int,int)
public java.lang.Object[] java.util.ArrayList.toArray(java.lang.Object[])
public java.lang.Object[] java.util.ArrayList.toArray()
public java.util.Iterator java.util.ArrayList.iterator()
public boolean java.util.ArrayList.contains(java.lang.Object)
public java.util.Spliterator java.util.ArrayList.spliterator()
public boolean java.util.ArrayList.addAll(java.util.Collection)
public boolean java.util.ArrayList.addAll(int,java.util.Collection)
public java.lang.Object java.util.ArrayList.set(int,java.lang.Object)
private void java.util.ArrayList.readObject(java.io.ObjectInputStream) throws java.io.IOException,java.lang.ClassNotFoundException
private void java.util.ArrayList.writeObject(java.io.ObjectOutputStream) throws java.io.IOException
public void java.util.ArrayList.forEach(java.util.function.Consumer)
public void java.util.ArrayList.ensureCapacity(int)
public void java.util.ArrayList.trimToSize()
public boolean java.util.ArrayList.retainAll(java.util.Collection)
public boolean java.util.ArrayList.removeAll(java.util.Collection)
boolean java.util.ArrayList.removeIf(java.util.function.Predicate,int,int)
public boolean java.util.ArrayList.removeIf(java.util.function.Predicate)
void java.util.ArrayList.checkInvariants()
public void java.util.ArrayList.sort(java.util.Comparator)
java.lang.Object java.util.ArrayList.elementData(int)
private java.lang.Object[] java.util.ArrayList.grow()
private java.lang.Object[] java.util.ArrayList.grow(int)
int java.util.ArrayList.indexOfRange(java.lang.Object,int,int)
int java.util.ArrayList.lastIndexOfRange(java.lang.Object,int,int)
private void java.util.ArrayList.fastRemove(java.lang.Object[],int)
private boolean java.util.ArrayList.equalsArrayList(java.util.ArrayList)
boolean java.util.ArrayList.equalsRange(java.util.List,int,int)
private void java.util.ArrayList.checkForComodification(int)
int java.util.ArrayList.hashCodeRange(int,int)
private void java.util.ArrayList.shiftTailOverGap(java.lang.Object[],int,int)
boolean java.util.ArrayList.batchRemove(java.util.Collection,boolean,int,int)
static java.lang.Object java.util.ArrayList.elementAt(java.lang.Object[],int)
private static long[] java.util.ArrayList.nBits(int)
private static void java.util.ArrayList.setBit(long[],int)
private static boolean java.util.ArrayList.isClear(long[],int)
private void java.util.ArrayList.replaceAllRange(java.util.function.UnaryOperator,int,int)
public java.util.ListIterator java.util.ArrayList.listIterator()
public java.util.ListIterator java.util.ArrayList.listIterator(int)
protected void java.util.ArrayList.removeRange(int,int)
private void java.util.ArrayList.rangeCheckForAdd(int)
private static java.lang.String java.util.ArrayList.outOfBoundsMsg(int,int)
private java.lang.String java.util.ArrayList.outOfBoundsMsg(int)
clazz.getDeclaredFields():
private static final long java.util.ArrayList.serialVersionUID
private static final int java.util.ArrayList.DEFAULT_CAPACITY
private static final java.lang.Object[] java.util.ArrayList.EMPTY_ELEMENTDATA
private static final java.lang.Object[] java.util.ArrayList.DEFAULTCAPACITY_EMPTY_ELEMENTDATA
transient java.lang.Object[] java.util.ArrayList.elementData
private int java.util.ArrayList.size
clazz.getInterfaces()
interface java.util.List
interface java.util.RandomAccess
interface java.lang.Cloneable
interface java.io.Serializable

View class: class java.util.AbstractList
clazz.getSuperclass(): class java.util.AbstractCollection
clazz.getDeclaredConstructors():
protected java.util.AbstractList()
clazz.getClasses():
clazz.getDeclaredAnnotations():
clazz.getDeclaredMethods():
public void java.util.AbstractList.add(int,java.lang.Object)
public boolean java.util.AbstractList.add(java.lang.Object)
public java.lang.Object java.util.AbstractList.remove(int)
public abstract java.lang.Object java.util.AbstractList.get(int)
public boolean java.util.AbstractList.equals(java.lang.Object)
public int java.util.AbstractList.hashCode()
public int java.util.AbstractList.indexOf(java.lang.Object)
public void java.util.AbstractList.clear()
public int java.util.AbstractList.lastIndexOf(java.lang.Object)
public java.util.List java.util.AbstractList.subList(int,int)
public java.util.Iterator java.util.AbstractList.iterator()
public boolean java.util.AbstractList.addAll(int,java.util.Collection)
public java.lang.Object java.util.AbstractList.set(int,java.lang.Object)
public java.util.ListIterator java.util.AbstractList.listIterator()
public java.util.ListIterator java.util.AbstractList.listIterator(int)
protected void java.util.AbstractList.removeRange(int,int)
private void java.util.AbstractList.rangeCheckForAdd(int)
static void java.util.AbstractList.subListRangeCheck(int,int,int)
private java.lang.String java.util.AbstractList.outOfBoundsMsg(int)
clazz.getDeclaredFields():
protected transient int java.util.AbstractList.modCount
clazz.getInterfaces()
interface java.util.List

View class: class java.util.AbstractCollection
clazz.getSuperclass(): class java.lang.Object
clazz.getDeclaredConstructors():
protected java.util.AbstractCollection()
clazz.getClasses():
clazz.getDeclaredAnnotations():
clazz.getDeclaredMethods():
public boolean java.util.AbstractCollection.add(java.lang.Object)
public boolean java.util.AbstractCollection.remove(java.lang.Object)
public java.lang.String java.util.AbstractCollection.toString()
public void java.util.AbstractCollection.clear()
public boolean java.util.AbstractCollection.isEmpty()
public abstract int java.util.AbstractCollection.size()
public java.lang.Object[] java.util.AbstractCollection.toArray()
public java.lang.Object[] java.util.AbstractCollection.toArray(java.lang.Object[])
public abstract java.util.Iterator java.util.AbstractCollection.iterator()
public boolean java.util.AbstractCollection.contains(java.lang.Object)
public boolean java.util.AbstractCollection.addAll(java.util.Collection)
public boolean java.util.AbstractCollection.containsAll(java.util.Collection)
public boolean java.util.AbstractCollection.retainAll(java.util.Collection)
public boolean java.util.AbstractCollection.removeAll(java.util.Collection)
private static java.lang.Object[] java.util.AbstractCollection.finishToArray(java.lang.Object[],java.util.Iterator)
clazz.getDeclaredFields():
clazz.getInterfaces()
interface java.util.Collection

View class: class java.lang.Object
clazz.getSuperclass(): null
clazz.getDeclaredConstructors():
public java.lang.Object()
clazz.getClasses():
clazz.getDeclaredAnnotations():
clazz.getDeclaredMethods():
protected void java.lang.Object.finalize() throws java.lang.Throwable
public final native void java.lang.Object.wait(long) throws java.lang.InterruptedException
public final void java.lang.Object.wait(long,int) throws java.lang.InterruptedException
public final void java.lang.Object.wait() throws java.lang.InterruptedException
public boolean java.lang.Object.equals(java.lang.Object)
public java.lang.String java.lang.Object.toString()
public native int java.lang.Object.hashCode()
public final native java.lang.Class java.lang.Object.getClass()
protected native java.lang.Object java.lang.Object.clone() throws java.lang.CloneNotSupportedException
public final native void java.lang.Object.notify()
public final native void java.lang.Object.notifyAll()
private static native void java.lang.Object.registerNatives()
clazz.getDeclaredFields():
clazz.getInterfaces()

View class: class exercises.chapter14.FancyToy2
clazz.getSuperclass(): class exercises.chapter14.Toy2
clazz.getDeclaredConstructors():
exercises.chapter14.FancyToy2(int)
clazz.getClasses():
clazz.getDeclaredAnnotations():
clazz.getDeclaredMethods():
clazz.getDeclaredFields():
clazz.getInterfaces()
interface exercises.chapter14.HasBatteries2
interface exercises.chapter14.Waterproof2
interface exercises.chapter14.Shoots2

View class: class exercises.chapter14.Toy2
clazz.getSuperclass(): class java.lang.Object
clazz.getDeclaredConstructors():
exercises.chapter14.Toy2(int)
clazz.getClasses():
clazz.getDeclaredAnnotations():
clazz.getDeclaredMethods():
public java.lang.String exercises.chapter14.Toy2.toString()
clazz.getDeclaredFields():
private int exercises.chapter14.Toy2.i
clazz.getInterfaces()

View class: class java.lang.Object
clazz.getSuperclass(): null
clazz.getDeclaredConstructors():
public java.lang.Object()
clazz.getClasses():
clazz.getDeclaredAnnotations():
clazz.getDeclaredMethods():
protected void java.lang.Object.finalize() throws java.lang.Throwable
public final native void java.lang.Object.wait(long) throws java.lang.InterruptedException
public final void java.lang.Object.wait(long,int) throws java.lang.InterruptedException
public final void java.lang.Object.wait() throws java.lang.InterruptedException
public boolean java.lang.Object.equals(java.lang.Object)
public java.lang.String java.lang.Object.toString()
public native int java.lang.Object.hashCode()
public final native java.lang.Class java.lang.Object.getClass()
protected native java.lang.Object java.lang.Object.clone() throws java.lang.CloneNotSupportedException
public final native void java.lang.Object.notify()
public final native void java.lang.Object.notifyAll()
private static native void java.lang.Object.registerNatives()
clazz.getDeclaredFields():
clazz.getInterfaces()
 */