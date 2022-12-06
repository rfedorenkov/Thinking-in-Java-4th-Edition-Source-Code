package exercises.chapter4;

/**
 * Exercise 7
 * Modify exercise 1 so that the program exits by using
 * the break keyword at value 99. Try using return instead.
 */
public class E07_OneToNinetyNine {
    static void testRBreak() {
        for (int i = 1; i <= 100; i++) {
            if (i == 99) return;
            System.out.printf("%2d ", i);
            if (i % 10 == 0)
                System.out.println();
        }
    }

    static void testReturn() {
        for (int i = 1; i <= 100; i++) {
            if (i == 99) return;
            System.out.printf("%2d ", i);
            if (i % 10 == 0)
                System.out.println();
        }
    }

    public static void main(String[] args) {
        System.out.println("testBreak():");
        testRBreak();
        System.out.println();
        System.out.println("testReturn():");
        testReturn();

    }
}
/* Output:
testBreak():
 1  2  3  4  5  6  7  8  9 10
11 12 13 14 15 16 17 18 19 20
21 22 23 24 25 26 27 28 29 30
31 32 33 34 35 36 37 38 39 40
41 42 43 44 45 46 47 48 49 50
51 52 53 54 55 56 57 58 59 60
61 62 63 64 65 66 67 68 69 70
71 72 73 74 75 76 77 78 79 80
81 82 83 84 85 86 87 88 89 90
91 92 93 94 95 96 97 98
testReturn():
 1  2  3  4  5  6  7  8  9 10
11 12 13 14 15 16 17 18 19 20
21 22 23 24 25 26 27 28 29 30
31 32 33 34 35 36 37 38 39 40
41 42 43 44 45 46 47 48 49 50
51 52 53 54 55 56 57 58 59 60
61 62 63 64 65 66 67 68 69 70
71 72 73 74 75 76 77 78 79 80
81 82 83 84 85 86 87 88 89 90
91 92 93 94 95 96 97 98
 */