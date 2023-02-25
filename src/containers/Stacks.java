package containers;

import java.util.LinkedList;
import java.util.Stack;

import static net.mindview.util.Print.print;
import static net.mindview.util.Print.printnb;

/**
 * Demonstration of Stack Class.
 */
enum Month {
    JANUARY, FEBRUARY, MARCH, APRIL, MAY, JUNE,
    JULY, AUGUST, SEPTEMBER, OCTOBER, NOVEMBER, DECEMBER
}

public class Stacks {
    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();
        for (Month m : Month.values())
            stack.push(m.toString());
        print("stack = " + stack);
        // Treating a stack as a Vector:
        stack.addElement("The last line");
        print("element 5 = " + stack.elementAt(5));
        print("popping elements:");
        while (!stack.empty())
            printnb(stack.pop() + " ");
        print();

        // Using a LinkedList as a Stack:
        LinkedList<String> lstack = new LinkedList<>();
        for (Month m : Month.values())
            lstack.add(m.toString());
        print("lstack = " + lstack);
        while (!lstack.isEmpty())
            printnb(lstack.removeFirst() + " ");
        print();

        // Using the Stack class from
        // the Holding Your Objects Chapter:
        net.mindview.util.Stack<String> stack2 = new net.mindview.util.Stack<>();
        for (Month m : Month.values())
            stack2.push(m.toString());
        print("stack2 = " + stack2);
        while (!stack2.empty())
            printnb(stack2.pop() + " ");
    }
}
/* Output:
stack = [JANUARY, FEBRUARY, MARCH, APRIL, MAY, JUNE, JULY, AUGUST, SEPTEMBER, OCTOBER, NOVEMBER, DECEMBER]
element 5 = JUNE
popping elements:
The last line DECEMBER NOVEMBER OCTOBER SEPTEMBER AUGUST JULY JUNE MAY APRIL MARCH FEBRUARY JANUARY
lstack = [JANUARY, FEBRUARY, MARCH, APRIL, MAY, JUNE, JULY, AUGUST, SEPTEMBER, OCTOBER, NOVEMBER, DECEMBER]
JANUARY FEBRUARY MARCH APRIL MAY JUNE JULY AUGUST SEPTEMBER OCTOBER NOVEMBER DECEMBER
stack2 = [DECEMBER, NOVEMBER, OCTOBER, SEPTEMBER, AUGUST, JULY, JUNE, MAY, APRIL, MARCH, FEBRUARY, JANUARY]
DECEMBER NOVEMBER OCTOBER SEPTEMBER AUGUST JULY JUNE MAY APRIL MARCH FEBRUARY JANUARY
 */