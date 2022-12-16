package exercises.chapter6;

/**
 * Exercise 8
 * Following the form of the example Lunch.java, create a class
 * called ConnectionManager that manages a fixed array of Connection
 * objects. The client programmer must not be able to create
 * Connection objects, but only get them via a static method
 * in ConnectionManager. ConnectionManager returns a null
 * reference when it runs out of objects. Test the classes in main().
 */
class Connection {
    private static int nextId;
    private int id = nextId++;

    private Connection() {
    }

    static Connection getConnection() {
        return new Connection();
    }

    @Override
    public String toString() {
        return "Connection #" + id;
    }
}

class ConnectionManager {
    private static int SIZE = 5;
    private Connection[] connections;
    private int counter = 0;
    private ConnectionManager() {
        connections = new Connection[SIZE];
        fillConnections();
    }

    public static ConnectionManager getInstance() {
        return new ConnectionManager();
    }

    public Connection getConnection() {
        return counter >= SIZE ? null : connections[counter++];
    }

    private void fillConnections() {
        for (int i = 0; i < connections.length; i++) {
            connections[i] = Connection.getConnection();
        }
    }
}

public class E08_ConnectionManagerTest {
    public static void main(String[] args) {
        ConnectionManager manager = ConnectionManager.getInstance();
        for (int i = 0; i < 6; i++) {
            System.out.println(manager.getConnection());
        }
    }
}
/* Output:
Connection #0
Connection #1
Connection #2
Connection #3
Connection #4
null
 */