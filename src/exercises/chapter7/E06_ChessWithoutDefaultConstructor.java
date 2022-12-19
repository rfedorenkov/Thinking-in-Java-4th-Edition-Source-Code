package exercises.chapter7;

/**
 * Exercise 6
 * If you don't call the base-class constructor in BoardGame(),
 * the compiler will respond that is can't find a constructor
 * of the form Game(). The call to the base-class constructor
 * must be the first thing you do in the derived-class constructor.
 * (The compiler will remind you if you get it wrong.)
 * Use Chess.java to prove those assertions.
 */
//! class Chess2 extends Chess { // constructor in class cannot be applied to given types
    //! Chess2() {
        //! System.out.println("Chess2()"); // constructor in class cannot be applied to given types
        //! super(11); // call to super must be first statement in constructor
    //! }
//! }

public class E06_ChessWithoutDefaultConstructor {
    public static void main(String[] args) {
        //! Chess2 x = new Chess2();
    }
}