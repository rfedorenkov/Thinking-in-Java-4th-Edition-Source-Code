package exercises.chapter4;

import static net.mindview.util.Print.print;

/**
 * Exercise 6
 * Modify the two test() methods in the previous two programs
 * so that they take two extra arguments begin and end, and so
 * that testval is tested to see if it is withing the range between
 * (and including) begin and end.
 */
public class E06_IfElse3 {
    static boolean test(int testval, int begin, int end) {
        if (begin > end) {
            print("begin cannot be > end");
            return false;
        }
        return testval >= begin && testval <= end;
    }

    public static void main(String[] args) {
        print(test(10, 5, 10));
        print(test(0, 5, 5));
        print(test(0, 10, 5));
    }
}
/* Output:
true
false
begin cannot be > end
false
 */