package generics;

import java.util.List;

/**
 * {CompileTimeError} (Won't compile)
 */
public class UseList<W, T> {
    // void f(List<T> v) {}

    // void f(List<W> v) {}
}