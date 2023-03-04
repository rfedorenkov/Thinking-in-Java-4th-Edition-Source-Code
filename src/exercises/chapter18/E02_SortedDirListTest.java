package exercises.chapter18;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.regex.Pattern;

import static net.mindview.util.Print.print;

/**
 * Exercise 2
 * Create a class called SortedDirList with a constructor
 * that takes a File object and builds a sorted directory
 * list from the files at that File. Add to this class two
 * overloaded list() methods: the first produces the whole
 * list, and the second produces the subset of the list that
 * matches its argument (which is a regular expression).
 */
class SortedDirList {
    private File path;

    SortedDirList(File path) {
        this.path = path;
    }

    public String[] list() {
        String[] result = path.list();
        Arrays.sort(result, String.CASE_INSENSITIVE_ORDER);
        return result;
    }

    public String[] list(final String regex) {
        String[] result = path.list(new FilenameFilter() {
            private Pattern pattern = Pattern.compile(regex);

            @Override
            public boolean accept(File dir, String name) {
                return pattern.matcher(name).matches();
            }
        });
        Arrays.sort(result, String.CASE_INSENSITIVE_ORDER);
        return result;
    }
}

public class E02_SortedDirListTest {
    public static void main(String[] args) {
        SortedDirList sdl = new SortedDirList(new File("./src/exercises/chapter18"));
        print(Arrays.asList(sdl.list()));
        print(Arrays.asList(sdl.list("E01.*\\.java")));
    }
}

// TODO