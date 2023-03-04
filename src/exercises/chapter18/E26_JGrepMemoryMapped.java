package exercises.chapter18;

import java.io.FileInputStream;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Exercise 26
 * Modify strings/JGrep.java to use Java nio memory-mapped files.
 * {Args: src/exercises/chapter18/E26_JGrepMemoryMapped.java "\b[Ssct]\w+"}
 */
public class E26_JGrepMemoryMapped {
    public static void main(String[] args) throws Exception {
        if (args.length < 2) {
            System.out.println("Usage: java JGrep file regex");
            System.exit(0);
        }
        Pattern p = Pattern.compile(args[1]);
        // Iterate through the lines of the input file:
        int index = 0;
        Matcher m = p.matcher("");
        FileChannel fc = new FileInputStream(args[0]).getChannel();
        MappedByteBuffer mbf = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());
        CharBuffer cb = Charset.defaultCharset().decode(mbf);
        String[] array = cb.toString().split("\n");
        for (String line : array) {
            m.reset(line);
            while (m.find())
                System.out.println(index++ + ": " + m.group() + ": " + m.start());
        }
    }
}
/* Output:
0: chapter18: 18
1: channels: 16
2: charset: 16
3: strings: 10
4: to: 29
5: src: 10
6: chapter18: 24
7: Ssct: 65
8: class: 7
9: static: 11
10: String: 28
11: throws: 43
12: System: 12
13: System: 12
14: compile: 28
15: through: 19
16: the: 27
17: the: 40
18: size: 75
19: cb: 19
20: System: 40
21: String: 8
22: cb: 25
23: toString: 28
24: split: 39
25: String: 13
26: System: 16
27: start: 73
 */