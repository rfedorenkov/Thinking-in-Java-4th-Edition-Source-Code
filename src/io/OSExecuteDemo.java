package io;

import net.mindview.util.OSExecute;

/**
 * Demonstrates standard I/O redirection.
 */
public class OSExecuteDemo {
    public static void main(String[] args) {
        OSExecute.command("javap out/classes/io/OSExecuteDemo.class");
    }
}
/* Output:
Compiled from "OSExecuteDemo.java"
public class io.OSExecuteDemo {
  public io.OSExecuteDemo();
  public static void main(java.lang.String[]);
}
 */