package exercises.chapter19;

import java.util.EnumMap;

import static exercises.chapter19.E09_PostOffice2.MailHandler.*;
import static net.mindview.util.Print.print;

/**
 * Exercise 9
 * Modify class PostOffice so that it uses an EnumMap.
 */
interface Command {
    boolean handle(Mail m);
}

public class E09_PostOffice2 {

    static EnumMap<MailHandler, Command> em = new EnumMap<>(MailHandler.class);

    static {
        em.put(GENERAL_DELIVERY, new Command() {
            @Override
            public boolean handle(Mail m) {
                switch (m.generalDelivery) {
                    case YES:
                        print("Using general delivery for " + m);
                        return true;
                    default:
                        return false;
                }
            }
        });
        em.put(MACHINE_SCAN, new Command() {
            @Override
            public boolean handle(Mail m) {
                switch (m.scannability) {
                    case UNSCANNABLE: return false;
                    default:
                        switch (m.address) {
                            case INCORRECT: return false;
                            default:
                                print("Delivering " + m + " automatically");
                                return true;
                        }
                }
            }
        });
        em.put(VISUAL_INSPECTION, new Command() {
            @Override
            public boolean handle(Mail m) {
                switch (m.readability) {
                    case ILLEGIBLE: return false;
                    default:
                        switch (m.address) {
                            case INCORRECT: return false;
                            default:
                                print("Delivering " + m + " normally");
                                return true;
                        }
                }
            }
        });
        em.put(RETURN_TO_SENDER, new Command() {
            @Override
            public boolean handle(Mail m) {
                switch (m.returnAddress) {
                    case MISSING:
                        return false;
                    default:
                        print("Returning " + m + " to sender");
                        return true;
                }
            }
        });
    }
    enum MailHandler {
        GENERAL_DELIVERY, MACHINE_SCAN, VISUAL_INSPECTION, RETURN_TO_SENDER;
    }

    static void handle(Mail m) {
        for (Command cmd : em.values())
            if (cmd.handle(m))
                return;
        print(m + " is a dead letter");
    }

    public static void main(String[] args) {
        for (Mail mail : Mail.generator(10)) {
            print(mail.details());
            handle(mail);
            print("*****");
        }
    }
}
/* Output:
Mail 0, General Delivery: NO2, Address Scanability: UNSCANNABLE, Address Readability: YES3, Address Address: OK1, Return address: OK1, Forward address: OK5
Delivering Mail 0 normally
*****
Mail 1, General Delivery: NO4, Address Scanability: UNSCANNABLE, Address Readability: YES2, Address Address: INCORRECT, Return address: MISSING, Forward address: MISSING
Mail 1 is a dead letter
*****
Mail 2, General Delivery: NO3, Address Scanability: YES4, Address Readability: YES4, Address Address: OK3, Return address: OK4, Forward address: OK1
Delivering Mail 2 automatically
*****
Mail 3, General Delivery: NO2, Address Scanability: YES3, Address Readability: YES1, Address Address: OK4, Return address: OK1, Forward address: MISSING
Delivering Mail 3 automatically
*****
Mail 4, General Delivery: NO2, Address Scanability: YES3, Address Readability: YES1, Address Address: OK5, Return address: OK4, Forward address: OK2
Delivering Mail 4 automatically
*****
Mail 5, General Delivery: YES, Address Scanability: YES4, Address Readability: ILLEGIBLE, Address Address: OK4, Return address: OK4, Forward address: MISSING
Using general delivery for Mail 5
*****
Mail 6, General Delivery: NO1, Address Scanability: YES4, Address Readability: YES4, Address Address: OK6, Return address: OK3, Forward address: OK5
Delivering Mail 6 automatically
*****
Mail 7, General Delivery: YES, Address Scanability: YES2, Address Readability: YES1, Address Address: INCORRECT, Return address: OK1, Forward address: OK2
Using general delivery for Mail 7
*****
Mail 8, General Delivery: NO4, Address Scanability: YES1, Address Readability: YES2, Address Address: INCORRECT, Return address: OK4, Forward address: OK5
Returning Mail 8 to sender
*****
Mail 9, General Delivery: NO1, Address Scanability: YES4, Address Readability: YES1, Address Address: OK5, Return address: MISSING, Forward address: OK1
Delivering Mail 9 automatically
*****
 */