package strings;

import net.mindview.util.TextFile;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A very simple version of the "grep" program.
 * {Args: src/strings/JGrep.java "\b[Ssct]\w+"}
 */
public class JGrep {
    public static void main(String[] args) throws Exception {
        if (args.length < 2) {
            System.out.println("Usage: java JGrep file regex");
            System.exit(0);
        }
        Pattern p = Pattern.compile(args[1]);
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
0: strings: 8
1: simple: 10
2: the: 28
3: Ssct: 26
4: class: 7
5: static: 11
6: String: 28
7: throws: 43
8: System: 12
9: System: 12
10: compile: 28
11: through: 19
12: the: 27
13: the: 40
14: String: 13
15: System: 16
16: start: 73
 */