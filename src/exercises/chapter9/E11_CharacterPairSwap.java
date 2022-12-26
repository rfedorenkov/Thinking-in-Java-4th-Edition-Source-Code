package exercises.chapter9;

import interfaces.interfaceprocessor.Apply;
import interfaces.interfaceprocessor.Processor;

/**
 * Exercise 11
 * Create a class with a method that takes a String argument and produces
 * a result that swaps each pair of characters in that argument.
 * Adapt the class to work with interfaceprocessor.Apply.process().
 */
class CharacterSwapProcessor {
    private CharacterSwapProcessor() {

    }
    public static String swap(String s) {
        char[] arr = s.toCharArray();
        for (int i = 0; i < arr.length - 1; i += 2)
            swap(arr, i, i + 1);
        return String.valueOf(arr);
    }

    private static void swap(char[] arr, int i, int j) {
        char tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}

class CharacterAdapter implements Processor {

    @Override
    public String name() {
        return CharacterSwapProcessor.class.getSimpleName();
    }

    @Override
    public Object process(Object input) {
        return CharacterSwapProcessor.swap(input.toString());
    }
}

public class E11_CharacterPairSwap {
    public static void main(String[] args) {
        Apply.process(new CharacterAdapter(), "abcde");
        Apply.process(new CharacterAdapter(), 123456);
    }
}
/* Output:
Using Processor CharacterSwapProcessor
badce
Using Processor CharacterSwapProcessor
214365
 */