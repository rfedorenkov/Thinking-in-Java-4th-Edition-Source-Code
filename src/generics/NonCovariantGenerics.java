package generics;

import java.util.ArrayList;
import java.util.List;

/**
 * {CompileTimeError} (Won't compile)
 */
public class NonCovariantGenerics {
    // Compile Error: incompatible types:
    // List<Fruit> flist = new ArrayList<Apple>();
}