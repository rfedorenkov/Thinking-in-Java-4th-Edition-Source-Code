package control;

public class CommaOperator {
    public static void main(String[] args) {
        for (int i = 1, j = i + 10; i < 5; i++, j = j * 2) {
            System.out.println("i = " + i + " j = " + j);
        }
    }
}
/* Output:
i = 1 j = 11
i = 2 j = 22
i = 3 j = 44
i = 4 j = 88
 */