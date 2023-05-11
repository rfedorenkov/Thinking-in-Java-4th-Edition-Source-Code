package exercises.chapter21;

import enumerated.menu.Course;
import enumerated.menu.Food;

import java.util.*;
import java.util.concurrent.*;

import static net.mindview.util.Print.print;

/**
 * Exercise 36
 * Modify RestaurantWithQueues.java so there's one
 * OrderTicket object per table. Change order to orderTicket,
 * and add a Table class, wit multiple Customers per table.
 * {Args: 5}
 */
class OrderTicket {
    private static int counter = 0;
    private final int id = counter++;
    private final Table table;
    private final List<Order4> orders =
            Collections.synchronizedList(new LinkedList<>());

    public OrderTicket(Table t) {
        table = t;
    }

    public WaitPerson4 getWaitPerson() {
        return table.getWaitPerson();
    }

    public void placeOrder(Customer4 cust, Food f) {
        Order4 order = new Order4(cust, f);
        orders.add(order);
        order.setOrderTicket(this);
    }

    public List<Order4> getOrders() {
        return orders;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(
                "Order Ticket: " + id + " for: " + table + "\n");
        synchronized (orders) {
            for (Order4 order : orders)
                sb.append(order).append("\n");
        }
        return sb.substring(0, sb.length() - 1);
    }
}

class Table implements Runnable {
    private static int counter;
    private final int id = counter++;
    private final WaitPerson4 waitPerson;
    private final List<Customer4> customers;
    private final OrderTicket orderTicket = new OrderTicket(this);
    private final CyclicBarrier barrier;
    private final int nCustomers;
    private final ExecutorService exec;

    public Table(WaitPerson4 waitPerson, int nCustomers, ExecutorService e) {
        this.waitPerson = waitPerson;
        customers = Collections.synchronizedList(new LinkedList<>());
        this.nCustomers = nCustomers;
        exec = e;
        barrier = new CyclicBarrier(nCustomers + 1, new Runnable() {
            @Override
            public void run() {
                print(orderTicket);
            }
        });
    }

    public WaitPerson4 getWaitPerson() {
        return waitPerson;
    }

    public void placeOrder(Customer4 cust, Food food) {
        orderTicket.placeOrder(cust, food);
    }

    @Override
    public void run() {
        Customer4 customer;
        for (int i = 0; i < nCustomers; i++) {
            customers.add(customer = new Customer4(this, barrier));
            exec.execute(customer);
        }
        try {
            barrier.await();
        } catch (InterruptedException e) {
            print(this + " interrupted");
            return;
        } catch (BrokenBarrierException e) {
            throw new RuntimeException(e);
        }
        waitPerson.placeOrder(orderTicket);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("" +
                "Table: " + id + " served by: " + waitPerson + "\n");
        synchronized (customers) {
            for (Customer4 customer : customers)
                sb.append(customer + "\n");
        }
        return sb.substring(0, sb.length() - 1);
    }
}

class Order4 { // (A data-transfer object)
    private static int counter = 0;
    private final int id;
    private volatile OrderTicket orderTicket;
    private final Customer4 customer;
    private final Food food;

    public Order4(Customer4 cust, Food f) {
        customer = cust;
        food = f;
        synchronized (Order4.class) {
            id = counter++;
        }
    }

    public void setOrderTicket(OrderTicket orderTicket) {
        this.orderTicket = orderTicket;
    }

    public OrderTicket getOrderTicket() {
        return orderTicket;
    }

    public Food item() {
        return food;
    }

    public Customer4 getCustomer() {
        return customer;
    }

    @Override
    public String toString() {
        return "Order: " + id + " item: " + food +
                " for: " + customer;
    }
}

// This is what comes back from the chef:
class Plate4 {
    private final Order4 order;
    private final Food food;

    public Plate4(Order4 ord, Food f) {
        order = ord;
        food = f;
    }

    public Order4 getOrder() {
        return order;
    }

    public Food getFood() {
        return food;
    }

    @Override
    public String toString() {
        return food.toString();
    }
}

class Customer4 implements Runnable {
    private static int counter = 0;
    private final int id;
    private final CyclicBarrier barrier;
    private final Table table;
    private int nPlates;
    private final SynchronousQueue<Plate4> placeSetting = new SynchronousQueue<>();

    public Customer4(Table table, CyclicBarrier barrier) {
        this.table = table;
        this.barrier = barrier;
        synchronized (Customer4.class) {
            id = counter++;
        }
    }

    public void deliver(Plate4 p) throws InterruptedException {
        // Only blocks if customer is still
        // eating the previous course:
        placeSetting.put(p);
    }

    @Override
    public void run() {
        for (Course course : Course.values()) {
            Food food = course.randomSelection();
            table.placeOrder(this, food);
            ++nPlates;
        }
        try {
            barrier.await();
            // Blocks until course has been delivered:
        } catch (InterruptedException e) {
            print(this + " interrupted while ordering meal");
            return;
        } catch (BrokenBarrierException e) {
            throw new RuntimeException(e);
        }
        for (int i = 0; i < nPlates; i++)
            try {
                print(this + " eating " + placeSetting.take());
            } catch (InterruptedException e) {
                print(this + " waiting for meal interrupted");
                return;
            }
        print(this + " finished meal, leaving");
    }

    @Override
    public String toString() {
        return "Customer " + id;
    }
}

class WaitPerson4 implements Runnable {
    private static int counter = 0;
    private final int id = counter++;
    private final Restaurant4 restaurant;
    BlockingQueue<Plate4> filledOrders = new LinkedBlockingQueue<>();

    public WaitPerson4(Restaurant4 rest) {
        restaurant = rest;
    }

    public void placeOrder(OrderTicket orderTicket) {
        try {
            // Shouldn't actually block because this is
            // a LinkedBlockingQueue with no size limit:
            restaurant.orderTickets.put(orderTicket);
        } catch (InterruptedException e) {
            print(this + " placeOrderTicket interrupted");
        }
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                // Blocks until a course is ready
                Plate4 plate = filledOrders.take();
                print(this + " received " + plate +
                        " delivering to " + plate.getOrder().getCustomer());
                plate.getOrder().getCustomer().deliver(plate);
            }
        } catch (InterruptedException e) {
            print(this + " interrupted");
        }
        print(this + " off duty");
    }

    @Override
    public String toString() {
        return "WaitPerson " + id;
    }
}

class Chef4 implements Runnable {
    private static int counter = 0;
    private final int id = counter++;
    private final Restaurant4 restaurant;
    private static Random rand = new Random(47);

    public Chef4(Restaurant4 rest) {
        restaurant = rest;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                // Blocks until an order appears:
                OrderTicket orderTicket = restaurant.orderTickets.take();
                List<Order4> orders = orderTicket.getOrders();
                synchronized (orders) {
                    for (Order4 order : orders) {
                        Food requestedItem = order.item();
                        // Time to prepare order:
                        TimeUnit.MILLISECONDS.sleep(rand.nextInt(500));
                        Plate4 plate = new Plate4(order, requestedItem);
                        order.getOrderTicket().getWaitPerson().filledOrders.put(plate);
                    }
                }
            }
        } catch (InterruptedException e) {
            print(this + " interrupted");
        }
        print(this + " off duty");
    }

    @Override
    public String toString() {
        return "Chef " + id;
    }
}

class Restaurant4 implements Runnable {
    private List<WaitPerson4> waitPersons = new ArrayList<>();
    private List<Chef4> chefs = new ArrayList<>();
    private ExecutorService exec;
    private static Random rand = new Random(47);
    BlockingQueue<OrderTicket> orderTickets = new LinkedBlockingQueue<>();

    public Restaurant4(ExecutorService e, int nWaitPersons, int nChefs) {
        exec = e;
        for (int i = 0; i < nWaitPersons; i++) {
            WaitPerson4 waitPerson = new WaitPerson4(this);
            waitPersons.add(waitPerson);
            exec.execute(waitPerson);
        }
        for (int i = 0; i < nChefs; i++) {
            Chef4 chef = new Chef4(this);
            chefs.add(chef);
            exec.execute(chef);
        }
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                // A new customer arrives; assign a WaitPerson:
                WaitPerson4 wp = waitPersons.get(rand.nextInt(waitPersons.size()));
                int nCustomers = rand.nextInt(4) + 1;
                Table t = new Table(wp, nCustomers, exec);
                exec.execute(t);
                TimeUnit.MILLISECONDS.sleep(400 * nCustomers);
            }
        } catch (InterruptedException e) {
            print("Restaurant interrupted");
        }
        print("Restaurant closing");
    }
}

public class E36_RestaurantWithQueues2 {
    public static void main(String[] args) throws Exception {
        ExecutorService exec = Executors.newCachedThreadPool();
        Restaurant4 restaurant = new Restaurant4(exec, 5, 2);
        exec.execute(restaurant);
        if (args.length > 0) // Optional argument
            TimeUnit.SECONDS.sleep(new Integer(args[0]));
        else {
            print("Press 'Enter' to quit");
            System.in.read();
        }
        exec.shutdownNow();
    }
}
/* Output: (Sample)
Order Ticket: 0 for: Table: 0 served by: WaitPerson 3
Customer 0
Customer 1
Order: 0 item: SPRING_ROLLS for: Customer 1
Order: 1 item: BURRITO for: Customer 1
Order: 3 item: GELATO for: Customer 1
Order: 4 item: TEA for: Customer 1
Order: 2 item: SPRING_ROLLS for: Customer 0
Order: 5 item: VINDALOO for: Customer 0
Order: 6 item: FRUIT for: Customer 0
Order: 7 item: TEA for: Customer 0
WaitPerson 3 received SPRING_ROLLS delivering to Customer 1
Customer 1 eating SPRING_ROLLS
WaitPerson 3 received BURRITO delivering to Customer 1
Customer 1 eating BURRITO
WaitPerson 3 received GELATO delivering to Customer 1
Customer 1 eating GELATO
Order Ticket: 1 for: Table: 1 served by: WaitPerson 3
Customer 2
Order: 8 item: SALAD for: Customer 2
Order: 9 item: BURRITO for: Customer 2
Order: 10 item: FRUIT for: Customer 2
Order: 11 item: TEA for: Customer 2
WaitPerson 3 received TEA delivering to Customer 1
Customer 1 eating TEA
Customer 1 finished meal, leaving
Restaurant interrupted
Restaurant closing
Customer 0 waiting for meal interrupted
WaitPerson 1 interrupted
WaitPerson 4 interrupted
WaitPerson 3 interrupted
WaitPerson 3 off duty
WaitPerson 2 interrupted
Chef 1 interrupted
WaitPerson 4 off duty
WaitPerson 1 off duty
Chef 1 off duty
Customer 2 waiting for meal interrupted
WaitPerson 0 interrupted
Chef 0 interrupted
Chef 0 off duty
WaitPerson 2 off duty
WaitPerson 0 off duty
 */