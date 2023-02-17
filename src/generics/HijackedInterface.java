package generics;

/**
 * {CompileTimeError} (Won't compile)
 */
//class Cat extends ComparablePet implements Comparable<Cat> {
    // Error: Comparable cannot be inherited with
    // different arguments: <Cat> and <Pet>
//    @Override
//    public int compareTo(Cat arg) {
//        return 0;
//    }
//}