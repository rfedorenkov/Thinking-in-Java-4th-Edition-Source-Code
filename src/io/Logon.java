package io;

import java.io.*;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static net.mindview.util.Print.print;

/**
 * Demonstrates the "transient" keyword.
 */
public class Logon implements Serializable {
    private Date date = new Date();
    private String username;
    private transient String password;

    public Logon(String name, String pwd) {
        username = name;
        password = pwd;
    }

    @Override
    public String toString() {
        return "logon info: \n   username: " + username +
                "\n   date: " + date + "\n   password: " + password;
    }

    public static void main(String[] args) throws Exception {
        Logon a = new Logon("Hulk", "myLittlePony");
        print("logon a = " + a);
        ObjectOutputStream o = new ObjectOutputStream(
                new FileOutputStream("src/io/Logon.out"));
        o.writeObject(a);
        o.close();
        TimeUnit.SECONDS.sleep(1); // Delay
        // Now get them back;
        ObjectInputStream in = new ObjectInputStream(
                new FileInputStream("src/io/Logon.out"));
        print("Recovering object at " + new Date());
        a = (Logon) in.readObject();
        print("logon a = " + a);

    }
}
/* Output:
logon a = logon info:
   username: Hulk
   date: Fri Mar 03 08:34:55 MSK 2023
   password: myLittlePony
Recovering object at Fri Mar 03 08:34:56 MSK 2023
logon a = logon info:
   username: Hulk
   date: Fri Mar 03 08:34:55 MSK 2023
   password: null
 */