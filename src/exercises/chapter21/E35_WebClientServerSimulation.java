package exercises.chapter21;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static net.mindview.util.Print.print;
import static net.mindview.util.Print.printnb;

/**
 * Exercise 35
 * Modify BankTellerSimulation.java so that it represents Web clients
 * making requests of a fixed number of servers. The goal is to determine
 * the load that the group of servers can handle.
 * {Args: 5}
 */
class WebClient {
    private final int serviceTime;

    public WebClient(int tm) {
        serviceTime = tm;
    }

    public int getServiceTime() {
        return serviceTime;
    }

    @Override
    public String toString() {
        return "[" + serviceTime + "]";
    }
}

class WebClientLine extends ArrayBlockingQueue<WebClient> {
    public WebClientLine(int maxLineSize) {
        super(maxLineSize);
    }

    @Override
    public String toString() {
        if (this.size() == 0)
            return "[Empty]";
        StringBuilder result = new StringBuilder();
        for (WebClient customer : this)
            result.append(customer);
        return result.toString();
    }
}

class WebClientGenerator implements Runnable {
    private WebClientLine clients;
    volatile int loadFactor = 1;
    private static Random rand = new Random(47);

    public WebClientGenerator(WebClientLine cq) {
        clients = cq;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                clients.put(new WebClient(rand.nextInt(1000)));
                TimeUnit.MILLISECONDS.sleep(1000 / loadFactor);
            }
        } catch (InterruptedException e) {
            print("WebClientGenerator interrupted");
        }
        print("WebClientGenerator terminating");
    }
}

class Server implements Runnable {
    private static int counter = 0;
    private final int id = counter++;
    private WebClientLine clients;

    public Server(WebClientLine cq) {
        clients = cq;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                WebClient client = clients.take();
                TimeUnit.MILLISECONDS.sleep(client.getServiceTime());
            }
        } catch (InterruptedException e) {
            print(this + " interrupted");
        }
        print(this + " terminating");
    }

    @Override
    public String toString() {
        return "Server " + id;
    }

    public String shortString() {
        return "T" + id;
    }
}

class ServerManager implements Runnable {
    private ExecutorService exec;
    private WebClientGenerator generator;
    private WebClientLine clients;
    private Queue<Server> servers = new LinkedList<>();
    private int adjustmentPeriod;
    private boolean stable = true;
    private int prevSize;

    public ServerManager(ExecutorService e, WebClientGenerator gen,
                         WebClientLine customers, int adjustmentPeriod, int n) {
        exec = e;
        generator = gen;
        this.clients = customers;
        this.adjustmentPeriod = adjustmentPeriod;
        for (int i = 0; i < n; i++) {
            Server server = new Server(clients);
            exec.execute(server);
            servers.add(server);
        }
    }

    public void adjustLoadFactor() {
        if (clients.size() > prevSize) {
            if (stable)
                stable = false;
            else if (!stable) {
                print("Load factor: " + generator.loadFactor);
                exec.shutdownNow();
            }
        } else {
            print("New load factor: " + ++generator.loadFactor);
            stable = true;
        }
        prevSize = clients.size();
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                TimeUnit.MILLISECONDS.sleep(adjustmentPeriod);
                printnb(clients + " { ");
                for (Server server : servers)
                    printnb(server.shortString() + " ");
                print("}");
                adjustLoadFactor();
            }
        } catch (InterruptedException e) {
            print(this + "interrupted");
        }
        print(this + "terminating");
    }

    @Override
    public String toString() {
        return "ServerManager ";
    }
}

public class E35_WebClientServerSimulation {
    static final int MAX_LINE_SIZE = 50;
    static final int NUM_OF_SERVERS = 3;
    static final int ADJUSTMENT_PERIOD = 1000;

    public static void main(String[] args) throws Exception {
        ExecutorService exec = Executors.newCachedThreadPool();
        WebClientLine clients = new WebClientLine(MAX_LINE_SIZE);
        WebClientGenerator g = new WebClientGenerator(clients);
        exec.execute(g);
        exec.execute(new ServerManager(exec, g, clients, ADJUSTMENT_PERIOD, NUM_OF_SERVERS));
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
Press 'Enter' to quit
[Empty] { T0 T1 T2 }
New load factor: 2
[Empty] { T0 T1 T2 }
New load factor: 3
[Empty] { T0 T1 T2 }
New load factor: 4
[Empty] { T0 T1 T2 }
New load factor: 5
[Empty] { T0 T1 T2 }
New load factor: 6
[Empty] { T0 T1 T2 }
New load factor: 7
[704][383] { T0 T1 T2 }
[342][674][804][950] { T0 T1 T2 }
Load factor: 7
WebClientGenerator interrupted
WebClientGenerator terminating
ServerManager terminating
Server 0 interrupted
Server 2 interrupted
Server 0 terminating
Server 2 terminating
Server 1 interrupted
Server 1 terminating
 */