package exercises.chapter10;

/**
 * Exercise 12
 * Repeat Exercise 7 using an anonymous inner class.
 */
class SecretClass2 {
    private String secret = "Password";

    private String getSecret() {
        return secret;
    }

    public void showHackerWork() {
        System.out.println("Secret = " + secret);
        String hack = new Object() {
            {
                System.out.println("Create anonymous class");
            }
            String hackSecret() {
                secret = "12345";
                return getSecret();
            }
        }.hackSecret();
        System.out.println("hacker.hackSecret() = " + hack);
        System.out.println("Secret = " + secret);
    }
}

public class E12_AnonymousInnerClassAccess {
    public static void main(String[] args) {
        SecretClass2 secret = new SecretClass2();
        secret.showHackerWork();
    }
}
/* Output:
Secret = Password
Create anonymous class
hacker.hackSecret() = 12345
Secret = 12345
 */