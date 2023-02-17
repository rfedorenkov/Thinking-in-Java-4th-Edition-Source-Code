package exercises.chapter15;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static net.mindview.util.Print.print;

/**
 * Exercise 42
 * Create two separate classes, with nothing in common.
 * Each class should hold a value, and at least have
 * methods that produce that value and perform a modification
 * upon that value. Modify Functional.java so that is performs
 * functional operations on collections of your classes
 * (these operations do not have to be arithmetic as they
 * are in Functional.java)
 */
interface Combiner<T> {
    T combine(T x, T y);
}

interface UnaryFunction<R, T> {
    R function(T x);
}

interface Collector<T> extends UnaryFunction<T, T> {
    T result();
}

interface UnaryPredicate<T> {
    boolean test(T x);
}

class Fruit {

    private String name;

    public Fruit(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Fruit: [" + name + "]";
    }
}

class DataHolder {
    private String data;

    public DataHolder(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "DataHolder: [" + data + "]";
    }
}

public class E42_Functional2 {
    public static <T> T reduce(Iterable<T> seq, Combiner<T> combiner) {
        Iterator<T> it = seq.iterator();
        if (it.hasNext()) {
            T result = it.next();
            while (it.hasNext())
                result = combiner.combine(result, it.next());
            return result;
        }
        return null;
    }

    public static <T> Collector<T> forEach(Iterable<T> seq, Collector<T> func) {
        for (T t : seq)
            func.function(t);
        return func;
    }

    public static <R, T> List<R> transform(Iterable<T> seq, UnaryFunction<R, T> func) {
        List<R> result = new ArrayList<>();
        for (T t : seq)
            result.add(func.function(t));
        return result;
    }

    public static <T> List<T> filter(Iterable<T> seq, UnaryPredicate<T> pred) {
        List<T> result = new ArrayList<>();
        for (T t : seq)
            if (pred.test(t))
                result.add(t);
        return result;
    }

    static class FruitShaker implements Combiner<Fruit> {
        @Override
        public Fruit combine(Fruit f1, Fruit f2) {
            return new Fruit(f1.getName() + ", " + f2.getName());
        }
    }

    static class FruitCollector implements Collector<Fruit> {
        private Fruit val = new Fruit("");

        @Override
        public Fruit function(Fruit x) {
            val.setName(val.getName() + " " + x.getName());
            return val;
        }

        @Override
        public Fruit result() {
            return val;
        }
    }

    static class DataUppercase implements UnaryFunction<DataHolder, DataHolder> {
        @Override
        public DataHolder function(DataHolder data) {
            return new DataHolder(data.getData().toUpperCase());
        }
    }

    static class DataConverter implements Combiner<DataHolder> {
        @Override
        public DataHolder combine(DataHolder d1, DataHolder d2) {
            return new DataHolder(d1.getData() + ", " + d2.getData());
        }
    }

    public static void main(String[] args) {
        List<Fruit> fl = Arrays.asList(
                new Fruit("Apple"), new Fruit("Orange"),
                new Fruit("Grape"), new Fruit("Mango"));
        Fruit result = reduce(fl, new FruitShaker());
        print(result);
        print(forEach(fl, new FruitCollector()).result());

        List<DataHolder> dhl = Arrays.asList(
                new DataHolder("Login"), new DataHolder("Password"), new DataHolder("Secret"));
        DataHolder result2 = reduce(dhl, new DataConverter());
        print(result2);
        print(transform(dhl, new DataUppercase()));
    }
}
/* Output:
Fruit: [Apple, Orange, Grape, Mango]
Fruit: [ Apple Orange Grape Mango]
DataHolder: [Login, Password, Secret]
[DataHolder: [LOGIN], DataHolder: [PASSWORD], DataHolder: [SECRET]]
 */