package exercises.chapter18;

import net.mindview.util.OSExecuteException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Exercise 22
 * Modify OSExecute.java so that, instead of printing
 * the standard output stream, it returns the results
 * of executing the program as a List of Strings.
 * Demonstrate the use of this new version of the utility.
 */
class OSExecute2 {
    public static List<String> command(String command) {
        boolean err = false;
        List<String> list = new ArrayList<>();
        try {
            Process process = new ProcessBuilder(command.split(" ")).start();
            BufferedReader result = new BufferedReader(
                    new InputStreamReader(process.getInputStream()));
            String s;
            while ((s = result.readLine()) != null)
                list.add(s);
            BufferedReader errors = new BufferedReader(
                    new InputStreamReader(process.getErrorStream()));
            // Report errors and return nonzero value
            // to calling process if there are problems:
            while ((s = errors.readLine()) != null) {
                System.err.println(s);
                err = true;
            }
        } catch (Exception e) {
            // Compensate for Windows 2000, which throws an
            // exception for the default command line:
            if (!command.startsWith("CMD /C"))
                command("CMD /C " + command);
            else
                throw new RuntimeException(e);
        }
        if (err)
            throw new OSExecuteException("Errors executing " + command);
        return list;
    }
}

public class E22_OSExecuteDemo2 {
    public static void main(String[] args) {
        List<String> result = OSExecute2.command("javap out/classes/exercises/chapter18/E22_OSExecuteDemo2.class");
        for (String s : result)
            System.out.println(s);
    }
}
/* Output:
Compiled from "E22_OSExecuteDemo2.java"
public class exercises.chapter18.E22_OSExecuteDemo2 {
  public exercises.chapter18.E22_OSExecuteDemo2();
  public static void main(java.lang.String[]);
}
 */