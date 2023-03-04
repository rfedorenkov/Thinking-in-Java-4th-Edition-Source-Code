package exercises.chapter18;

import net.mindview.util.Directory;

import java.io.File;
import java.io.IOException;

/**
 * Exercise 5
 * Modify ProcessFiles.java so that it matches
 * a regular expression rather than a fixed extension.
 */
public class E05_ProcessFiles2 {
    public interface Strategy {
        void process(File file);
    }

    private Strategy strategy;
    private String regex;

    public E05_ProcessFiles2(Strategy strategy, String regex) {
        this.strategy = strategy;
        this.regex = regex;
    }

    public void start(String[] args) {
        try {
            if (args.length == 0)
                processDirectoryTree(new File("."));
            else
                for (String arg : args) {
                    File fileArg = new File(arg);
                    if (fileArg.isDirectory())
                        processDirectoryTree(fileArg);
                    else {
                        // Allow user to leave off extension:
                        if (!arg.matches(regex))
                            strategy.process(fileArg.getCanonicalFile());
                    }
                }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void processDirectoryTree(File root) throws IOException {
        for (File file : Directory.walk(
                root.getAbsolutePath(), regex))
            strategy.process(file.getCanonicalFile());
    }

    // Demonstration of how to use it:
    public static void main(String[] args) {
        new E05_ProcessFiles2(new Strategy() {
            @Override
            public void process(File file) {
                System.out.println(file);
            }
        }, ".*\\.java").start(args);
    }
}
/* (Execute to see output) */