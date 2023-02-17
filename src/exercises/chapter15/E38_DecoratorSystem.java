package exercises.chapter15;

/**
 * Exercise 38
 * Create a simple Decorator system by starting with basic
 * coffee, then providing decorators of steamed milk, foam,
 * chocolate, caramel and whipped cream.
 */
class BasicCoffee {
    private String type;

    BasicCoffee() {
        type = "coffee";
    }

    BasicCoffee(String type) {
        this.type = type;
    }

    public void set(String type) {
        this.type = type;
    }

    public String get() {
        return type;
    }
}

class Decorator extends BasicCoffee {
    protected BasicCoffee coffee;

    public Decorator(BasicCoffee coffee) {
        this.coffee = coffee;
    }

    @Override
    public void set(String type) {
        coffee.set(type);
    }

    @Override
    public String get() {
        return coffee.get();
    }
}

class SteamedMilk extends Decorator {
    public SteamedMilk(BasicCoffee coffee) {
        super(coffee);
        set(get() + " & steamed milk");
    }
}

class Foam extends Decorator {
    public Foam(BasicCoffee coffee) {
        super(coffee);
        set(get() + " & foam");
    }
}

class Chocolate extends Decorator {
    public Chocolate(BasicCoffee coffee) {
        super(coffee);
        set(get() + " & chocolate");
    }
}

class Caramel extends Decorator {
    public Caramel(BasicCoffee coffee) {
        super(coffee);
        set(get() + " & caramel");
    }
}

class WhippedCream extends Decorator {
    public WhippedCream(BasicCoffee coffee) {
        super(coffee);
        set(get() + " & whipped cream");
    }
}

public class E38_DecoratorSystem {
    public static void main(String[] args) {
        System.out.println(new BasicCoffee("espresso").get());
        System.out.println(new Chocolate(new Caramel(new Foam(new BasicCoffee()))).get());
        System.out.println(new WhippedCream(new SteamedMilk(new Foam(new BasicCoffee()))).get());
    }
}
/* Output:
espresso
coffee & foam & caramel & chocolate
coffee & foam & steamed milk & whipped cream
 */