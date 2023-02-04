package exercises.chapter13;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static net.mindview.util.Print.print;

/**
 * Exercise 10
 * For the phrase "Java now has regular expression" evaluate
 * whether the following expressions will find a match:
 * ^Java
 * \Breq.*
 * n.w\s+h(a|i)s
 * s?
 * s*
 * s+
 * s{4}
 * s{1}.
 * s{0,3}
 */
public class E10_CheckForMatch {
    public static void main(String[] args) {
        String s = "Java now has regular expressions";
        String[] regularExpressions = {
                "^Java", "\\Breq.", "n.w\\s+h(a|i)s", "s?", "s*", "s+", "s{4}", "s{1}", "s{0,3}"
        };

        print("Input: \"" + s + "\"");
        for (String regex : regularExpressions) {
            print("Regular expression: \"" + regex + "\"");
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(s);
            while (m.find()) {
                print("Match \"" + m.group() + "\" at positions " +
                        m.start() + "-" + (m.end() - 1));
            }
        }
    }
}
/* Output:
Input: "Java now has regular expressions"
Regular expression: "^Java"
Match "Java" at positions 0-3
Regular expression: "\Breq."
Regular expression: "n.w\s+h(a|i)s"
Match "now has" at positions 5-11
Regular expression: "s?"
Match "" at positions 0--1
Match "" at positions 1-0
Match "" at positions 2-1
Match "" at positions 3-2
Match "" at positions 4-3
Match "" at positions 5-4
Match "" at positions 6-5
Match "" at positions 7-6
Match "" at positions 8-7
Match "" at positions 9-8
Match "" at positions 10-9
Match "s" at positions 11-11
Match "" at positions 12-11
Match "" at positions 13-12
Match "" at positions 14-13
Match "" at positions 15-14
Match "" at positions 16-15
Match "" at positions 17-16
Match "" at positions 18-17
Match "" at positions 19-18
Match "" at positions 20-19
Match "" at positions 21-20
Match "" at positions 22-21
Match "" at positions 23-22
Match "" at positions 24-23
Match "" at positions 25-24
Match "s" at positions 26-26
Match "s" at positions 27-27
Match "" at positions 28-27
Match "" at positions 29-28
Match "" at positions 30-29
Match "s" at positions 31-31
Match "" at positions 32-31
Regular expression: "s*"
Match "" at positions 0--1
Match "" at positions 1-0
Match "" at positions 2-1
Match "" at positions 3-2
Match "" at positions 4-3
Match "" at positions 5-4
Match "" at positions 6-5
Match "" at positions 7-6
Match "" at positions 8-7
Match "" at positions 9-8
Match "" at positions 10-9
Match "s" at positions 11-11
Match "" at positions 12-11
Match "" at positions 13-12
Match "" at positions 14-13
Match "" at positions 15-14
Match "" at positions 16-15
Match "" at positions 17-16
Match "" at positions 18-17
Match "" at positions 19-18
Match "" at positions 20-19
Match "" at positions 21-20
Match "" at positions 22-21
Match "" at positions 23-22
Match "" at positions 24-23
Match "" at positions 25-24
Match "ss" at positions 26-27
Match "" at positions 28-27
Match "" at positions 29-28
Match "" at positions 30-29
Match "s" at positions 31-31
Match "" at positions 32-31
Regular expression: "s+"
Match "s" at positions 11-11
Match "ss" at positions 26-27
Match "s" at positions 31-31
Regular expression: "s{4}"
Regular expression: "s{1}"
Match "s" at positions 11-11
Match "s" at positions 26-26
Match "s" at positions 27-27
Match "s" at positions 31-31
Regular expression: "s{0,3}"
Match "" at positions 0--1
Match "" at positions 1-0
Match "" at positions 2-1
Match "" at positions 3-2
Match "" at positions 4-3
Match "" at positions 5-4
Match "" at positions 6-5
Match "" at positions 7-6
Match "" at positions 8-7
Match "" at positions 9-8
Match "" at positions 10-9
Match "s" at positions 11-11
Match "" at positions 12-11
Match "" at positions 13-12
Match "" at positions 14-13
Match "" at positions 15-14
Match "" at positions 16-15
Match "" at positions 17-16
Match "" at positions 18-17
Match "" at positions 19-18
Match "" at positions 20-19
Match "" at positions 21-20
Match "" at positions 22-21
Match "" at positions 23-22
Match "" at positions 24-23
Match "" at positions 25-24
Match "ss" at positions 26-27
Match "" at positions 28-27
Match "" at positions 29-28
Match "" at positions 30-29
Match "s" at positions 31-31
Match "" at positions 32-31
 */