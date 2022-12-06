package exercises.chapter4;

/**
 * Exercise 8
 * Create a switch statement inside a for loop that tries each case
 * and prints a message. Put a break after each case and test it,
 * then see what happens when you remove the breaks.
 */
public class E08_SwitchTest {
    static void switchWithBreaks(int i) {
        switch (i) {
            case 0:
                System.out.println(i + " - Zero");
                break;
            case 1:
                System.out.println(i + " - One");
                break;
            case 2:
                System.out.println(i + " - Two");
                break;
            case 3:
                System.out.println(i + " - Three");
                break;
            case 4:
                System.out.println(i + " - Four");
                break;
            case 5:
                System.out.println(i + " - Five");
                break;
            default:
                System.out.println(i + " - Default");
        }
    }

    static void switchWithoutBreaks(int i) {
        switch (i) {
            case 0:
                System.out.println(i + " - Zero");
            case 1:
                System.out.println(i + " - One");
            case 2:
                System.out.println(i + " - Two");
            case 3:
                System.out.println(i + " - Three");
            case 4:
                System.out.println(i + " - Four");
            case 5:
                System.out.println(i + " - Five");
            default:
                System.out.println(i + " - Default");
        }
    }
    public static void main(String[] args) {
        System.out.println("switchWithBreaks():");
        for (int i = 0; i <= 6; i++) {
            switchWithBreaks(i);
        }
        System.out.println("switchWithoutBreaks():");
        for (int i = 0; i <= 6; i++) {
            switchWithoutBreaks(i);
        }
    }
}
/* Output:
switchWithBreaks():
0 - Zero
1 - One
2 - Two
3 - Three
4 - Four
5 - Five
6 - Default
switchWithoutBreaks():
0 - Zero
0 - One
0 - Two
0 - Three
0 - Four
0 - Five
0 - Default
1 - One
1 - Two
1 - Three
1 - Four
1 - Five
1 - Default
2 - Two
2 - Three
2 - Four
2 - Five
2 - Default
3 - Three
3 - Four
3 - Five
3 - Default
4 - Four
4 - Five
4 - Default
5 - Five
5 - Default
6 - Default
 */