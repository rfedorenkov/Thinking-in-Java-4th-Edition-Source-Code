package exercises.chapter13;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static net.mindview.util.Print.print;

/**
 * Exercise 11
 * Apply the regular expression
 * (?i)((^[aeiou])|(\s+[aeiou]))\w+?[aeiou]\b
 * to
 * "Arline ate eight apples and one orange while Anita hadn't any"
 */
public class E11_CheckForMatch2 {
    public static void main(String[] args) {
        String s = "Arline ate eight apples and one orange while Anita hadn't any";
        Pattern p = Pattern.compile("(?i)((^[aeiou])|(\\s+[aeiou]))\\w+?[aeiou]\\b");
        Matcher m = p.matcher(s);
        while (m.find()) {
            print("Match \"" + m.group() + "\" at positions " +
                    m.start() + "-" + (m.end() - 1));
        }
    }
}
/* Output:
Match "Arline" at positions 0-5
Match " ate" at positions 6-9
Match " one" at positions 27-30
Match " orange" at positions 31-37
Match " Anita" at positions 44-49
 */