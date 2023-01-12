package exercises.chapter10;

/**
 * Exercise 7
 * Create a class with a private field and a private method.
 * Create an inner class with a method that modifies the outer-class
 * field and calls the outer-class method. In a second outer-class
 * method, create an object of the inner class and call its method,
 * then show the effect on the outer-class object.
 */
class SecretClass {
    private String secret = "Password";

    private String getSecret() {
        return secret;
    }

    class Hacker {
        String hackSecret() {
            secret = "12345";
            return getSecret();
        }
    }

    public void showHackerWork() {
        System.out.println("Secret = " + secret);
        System.out.println("Create Hacker()");
        Hacker hacker = new Hacker();
        String hack = hacker.hackSecret();
        System.out.println("hacker.hackSecret() = " + hack);
        System.out.println("Secret = " + secret);
    }
}
public class E07_InnerClassAccess {
    public static void main(String[] args) {
        SecretClass secret = new SecretClass();
        secret.showHackerWork();
    }
}
/* Output:
Secret = Password
Create Hacker()
hacker.hackSecret() = 12345
Secret = 12345
 */