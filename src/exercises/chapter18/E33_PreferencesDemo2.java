package exercises.chapter18;

import io.PreferencesDemo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.prefs.Preferences;

import static net.mindview.util.Print.print;
import static net.mindview.util.Print.printnb;

/**
 * Exercise 33
 * Write a program that displays the current value of a directory
 * called "base directory" and prompts you for a new value.
 * Use the Preferences API to store the value.
 */
public class E33_PreferencesDemo2 {
    public static void main(String[] args) throws Exception {
        Preferences prefs = Preferences
                .userNodeForPackage(E33_PreferencesDemo2.class);
        String directory = prefs.get("base directory", "/");
        print("Current directory: " + directory);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        printnb("Enter a new directory: ");
        String newDirectory = br.readLine();
        prefs.put("base directory", newDirectory);
        print("New directory: " + newDirectory);
    }
}
/* Output:
Current directory: /
Enter a new directory: /root/
New directory: /root/
 */