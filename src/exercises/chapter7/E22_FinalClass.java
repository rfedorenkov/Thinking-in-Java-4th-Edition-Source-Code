package exercises.chapter7;

/**
 * Exercise 22
 * Create a final class and attempt to inherit from it.
 */
final class Ultimate {

}

//! class EvenMoreUltimate extends Ultimate {}
// error: Cannot extend final class 'Ultimate'

public class E22_FinalClass {
    public static void main(String[] args) {
        Ultimate u = new Ultimate();
    }
}