package exercises.chapter11;

import net.mindview.util.Stack;

/**
 * Exercise 15
 * Stack are often used to evaluate expressions
 * in programming languages. Using net.mindview.util.Stack,
 * evaluate the following expression, where '+' means
 * "push the following letter onto the stack," and '-' means
 * "pop the top of the stack and print it":
 * "+U+n+c---+e+r+t---+a-+i-+n+t+y---+ -+r+u--+l+e+s---"
 */
public class E15_EvaluateExpression {
    public static void main(String[] args) {
        Stack<Character> stack = new Stack<>();
        String message = "+U+n+c---+e+r+t---+a-+i-+n+t+y---+ -+r+u--+l+e+s---";
        for (int i = 0; i < message.length(); i++) {
            if (message.charAt(i) == '+') {
                stack.push(message.charAt(++i));
            } else if (message.charAt(i) == '-') {
                System.out.print(stack.pop());
            }
        }
    }
}
/* Output:
cnUtreaiytn ursel
 */