package exercises.chapter5;

/**
 * Exercise 22
 * Write a switch statement for the enum in Exercise 21.
 * For each case, output a description of that particular currency.
 */
public class E22_EnumTypesOfPaperMoneyTest2 {
    static void describe(TypesOfEuroPaperMoney value) {
        System.out.print("Costs: ");
        switch (value) {
            case FIVE:
                System.out.println("€5");
                break;
            case TEN:
                System.out.println("€10");
                break;
            case TWENTY:
                System.out.println("€20");
                break;
            case FIFTY:
                System.out.println("€50");
                break;
            case ONE_HUNDRED:
                System.out.println("€100");
                break;
            case TWO_HUNDRED:
                System.out.println("€200");
                break;
            default:
        }
    }

    public static void main(String[] args) {
        for (TypesOfEuroPaperMoney value : TypesOfEuroPaperMoney.values())
            describe(value);
    }
}
/* Output:
Costs: €5
Costs: €10
Costs: €20
Costs: €50
Costs: €100
Costs: €200
 */