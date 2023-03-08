package exercises.chapter19;

import net.mindview.util.Enums;

import java.util.Iterator;

import static net.mindview.util.Print.print;

/**
 * Exercise 8
 * Modify PostOffice.java so it has the ability to forward mail.
 */
class Mail {
    // The NO's lower the probability of random selection:
    enum GeneralDelivery {
        YES, NO1, NO2, NO3, NO4, NO5
    }

    enum Scannability {
        UNSCANNABLE, YES1, YES2, YES3, YES4
    }

    enum Readability {
        ILLEGIBLE, YES1, YES2, YES3, YES4
    }

    enum Address {
        INCORRECT, OK1, OK2, OK3, OK4, OK5, OK6
    }

    enum ReturnAddress {
        MISSING, OK1, OK2, OK3, OK4, OK5
    }

    enum ForwardAddress {
        MISSING, OK1, OK2, OK3, OK4, OK5
    }

    GeneralDelivery generalDelivery;
    Scannability scannability;
    Readability readability;
    Address address;
    ReturnAddress returnAddress;
    ForwardAddress forwardAddress;
    static long counter = 0;
    long id = counter++;

    @Override
    public String toString() {
        return "Mail " + id;
    }

    public String details() {
        return toString() +
                ", General Delivery: " + generalDelivery +
                ", Address Scanability: " + scannability +
                ", Address Readability: " + readability +
                ", Address Address: " + address +
                ", Return address: " + returnAddress +
                ", Forward address: " + forwardAddress;
    }

    // Generate test Mail:
    public static Mail randomMail() {
        Mail m = new Mail();
        m.generalDelivery = Enums.random(GeneralDelivery.class);
        m.scannability = Enums.random(Scannability.class);
        m.readability = Enums.random(Readability.class);
        m.address = Enums.random(Address.class);
        m.returnAddress = Enums.random(ReturnAddress.class);
        m.forwardAddress = Enums.random(ForwardAddress.class);
        return m;
    }

    public static Iterable<Mail> generator(final int count) {
        return new Iterable<Mail>() {
            int n = count;

            @Override
            public Iterator<Mail> iterator() {
                return new Iterator<Mail>() {
                    @Override
                    public boolean hasNext() {
                        return n-- > 0;
                    }

                    @Override
                    public Mail next() {
                        return randomMail();
                    }

                    @Override
                    public void remove() {
                        throw new UnsupportedOperationException();
                    }
                };
            }
        };
    }
}

public class E08_PostOfficeForwarding {
    enum MailHandler {
        GENERAL_DELIVERY {
            @Override
            boolean handle(Mail m) {
                switch (m.generalDelivery) {
                    case YES:
                        print("Using general delivery for " + m);
                        return true;
                    default:
                        return false;
                }
            }
        },
        MACHINE_SCAN {
            @Override
            boolean handle(Mail m) {
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
        },
        VISUAL_INSPECTION {
            @Override
            boolean handle(Mail m) {
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
        },
        FORWARD_MAIL {
            @Override
            boolean handle(Mail m) {
                switch (m.forwardAddress) {
                    case MISSING:
                        return false;
                    default:
                        print("Forwarding: " + m);
                        return true;
                }
            }
        },
        RETURN_TO_SENDER {
            @Override
            boolean handle(Mail m) {
                switch (m.returnAddress) {
                    case MISSING:
                        return false;
                    default:
                        print("Returning " + m + " to sender");
                        return true;
                }
            }
        };

        abstract boolean handle(Mail m);
    }

    static void handle(Mail m) {
        for (MailHandler handler : MailHandler.values())
            if (handler.handle(m))
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
Forwarding: Mail 8
*****
Mail 9, General Delivery: NO1, Address Scanability: YES4, Address Readability: YES1, Address Address: OK5, Return address: MISSING, Forward address: OK1
Delivering Mail 9 automatically
*****
 */