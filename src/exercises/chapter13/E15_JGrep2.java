package exercises.chapter13;

import net.mindview.util.TextFile;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Exercise 15
 * Modify JGrep.java to accept flags as arguments
 * (e.g., Pattern.CASE_INSENSITIVE, Pattern.MULTILINE).
 * {Args: src/exercises/chapter13/E15_JGrep2.java "\b[Ssct]\w+" CASE_INSENSITIVE}
 */
public class E15_JGrep2 {
    public static void main(String[] args) throws Exception {
        if (args.length < 3) {
            System.out.println("Usage: java E15_JGrep2 file regex pattern");
            System.out.println("The pattern can take one of the following values:");
            System.out.println("CANON_EQ, MULTILINE, CASE_INSENSITIVE, COMMENTS," +
                    " DOTALL, UNICODE_CASE, UNIX_LINES");
            System.exit(0);
            //The pattern can take one of the following values
        }
        int flag = 0;
        if ("CANON_EQ".equalsIgnoreCase(args[2])) {
            flag = Pattern.CANON_EQ;
        } else if ("MULTILINE".equalsIgnoreCase(args[2])) {
            flag = Pattern.MULTILINE;
        } else if ("CASE_INSENSITIVE".equalsIgnoreCase(args[2])) {
            flag = Pattern.CASE_INSENSITIVE;
        } else if ("COMMENTS".equalsIgnoreCase(args[2])) {
            flag = Pattern.COMMENTS;
        } else if ("DOTALL".equalsIgnoreCase(args[2])) {
            flag = Pattern.DOTALL;
        } else if ("UNICODE_CASE".equalsIgnoreCase(args[2])) {
            flag = Pattern.UNICODE_CASE;
        } else if ("UNIX_LINES".equalsIgnoreCase(args[2])) {
            flag = Pattern.UNIX_LINES;
        } else {
            throw new IllegalArgumentException("Incorrect pattern value: " + args[2]);
        }

        Pattern p = Pattern.compile(args[1], flag);
        // Iterate through the lines of the input file:
        int index = 0;
        Matcher m = p.matcher("");
        for (String line : new TextFile(args[0])) {
            m.reset(line);
            while (m.find())
                System.out.println(index++ + ": " + m.group() + ": " + m.start());
        }
    }
}
/* Output:
0: chapter13: 18
1: TextFile: 25
2: to: 21
3: CASE_INSENSITIVE: 18
4: src: 10
5: chapter13: 24
6: Ssct: 54
7: CASE_INSENSITIVE: 64
8: class: 7
9: static: 11
10: String: 28
11: throws: 43
12: System: 12
13: System: 12
14: The: 32
15: can: 44
16: take: 48
17: the: 60
18: System: 12
19: CANON_EQ: 32
20: CASE_INSENSITIVE: 53
21: COMMENTS: 71
22: System: 12
23: The: 14
24: can: 26
25: take: 30
26: the: 42
27: CANON_EQ: 13
28: CANON_EQ: 27
29: CASE_INSENSITIVE: 20
30: CASE_INSENSITIVE: 27
31: COMMENTS: 20
32: COMMENTS: 27
33: throw: 12
34: compile: 28
35: through: 19
36: the: 27
37: the: 40
38: String: 13
39: TextFile: 31
40: System: 16
41: start: 73
 */