package exercises.chapter20;

import net.mindview.atunit.Test;
import net.mindview.util.OSExecute;
import strings.Turtle;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Formatter;

/**
 * Exercise 10
 * Select an example from elsewhere in the book
 * and add @Unit tests.
 */
public class E10_UnitTestTurtle {
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream out = new PrintStream(new BufferedOutputStream(baos), true);
    Turtle turtle = new Turtle("Turtle", new Formatter(out));

    @Test
    void test1() {
        turtle.move(0, 0);
        assert baos.toString()
                .equals("Turtle The Turtle is at (0,0)\n");
    }

    @Test
    void test2() {
        turtle.move(3, 4);
        assert baos.toString()
                .equals("Turtle The Turtle is at (3,4)\n");
    }

    @Test
    void test3() {
        turtle.move(3, 3);
        assert baos.toString()
                .equals("Turtle The Turtle is at (3,3)\n");
    }

    public static void main(String[] args) throws Exception {
        OSExecute.command("java net.mindview.atunit.AtUnit E10_UnitTestTurtle");
    }
}
/* Output:
exercises.chapter20.E10_UnitTestTurtle
  . test1
  . test2
  . test3
OK (3 tests)
 */