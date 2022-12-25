package exercises.chapter8;

/**
 * Exercise 16
 * Following the example in Transmogrify.java create
 * a Starship class containing an AlertStatus reference
 * that can indicate three different states. Include
 * methods to change the states.
 */
class AlertStatus {
    String getStatus() {
        return "";
    }
}

class RedStatus extends AlertStatus {
    @Override
    String getStatus() {
        return "Status: Danger!!!";
    }
}

class YellowStatus extends AlertStatus {
    @Override
    String getStatus() {
        return "Status: Warning!";
    }
}

class GreenStatus extends AlertStatus {
    @Override
    String getStatus() {
        return "Status: OK.";
    }
}

class Starship {
    AlertStatus status = new GreenStatus();
    public void checkStatus() {
        System.out.println(status.getStatus());
    }

    public void changeStatus(AlertStatus status) {
        this.status = status;
    }

    public void repair() {
        this.status = new GreenStatus();
    }
}

public class E16_Transmogrify2 {
    public static void main(String[] args) {
        Starship starship = new Starship();
        starship.checkStatus();
        starship.changeStatus(new YellowStatus());
        starship.checkStatus();
        starship.changeStatus(new RedStatus());
        starship.checkStatus();
        starship.repair();
        starship.checkStatus();
    }
}
/* Output:
Status: OK.
Status: Warning!
Status: Danger!!!
Status: OK.
 */