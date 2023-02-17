package exercises.chapter15;

/**
 * Exercise 31
 * Remove all the generics from MultipleInterfaceVariants.java
 * and modify the code so that the example compiles.
 */
interface Payable {
}

class Employee2 implements Payable {
}

class Hourly extends Employee2 implements Payable {
}

public class E31_MultipleInterfaceVariants2 {
    public static void main(String[] args) {
        new Employee2();
        new Hourly();
    }
}