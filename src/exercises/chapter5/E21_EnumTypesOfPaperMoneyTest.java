package exercises.chapter5;

/**
 * Exercise 21
 * Create an enum of the six lowest denominations of paper currency.
 * Loop through the values() and print each value and its ordinal().
 */
enum TypesOfEuroPaperMoney {
    FIVE, TEN, TWENTY, FIFTY, ONE_HUNDRED, TWO_HUNDRED
}
public class E21_EnumTypesOfPaperMoneyTest {
    public static void main(String[] args) {
        for (TypesOfEuroPaperMoney value : TypesOfEuroPaperMoney.values())
            System.out.println(value + ", ordinal " + value.ordinal());
    }
}
/* Output:
FIVE, ordinal 0
TEN, ordinal 1
TWENTY, ordinal 2
FIFTY, ordinal 3
ONE_HUNDRED, ordinal 4
TWO_HUNDRED, ordinal 5
 */