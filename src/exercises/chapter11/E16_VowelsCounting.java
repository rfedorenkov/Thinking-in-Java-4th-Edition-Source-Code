package exercises.chapter11;

import net.mindview.util.TextFile;

import java.util.*;

/**
 * Exercise 16
 * Create a Set of the vowels. Working from UniqueWords.java,
 * count and display the number of vowels in each input word,
 * and also display the total number of vowels in the input file.
 */
public class E16_VowelsCounting {
    public static void main(String[] args) {
        Set<Character> vowels = new HashSet<>();
        Collections.addAll(vowels, 'a', 'e', 'o', 'u', 'i');
        Set<String> words = new TreeSet<>(
                new TextFile("src/exercises/chapter11/E16_VowelsCounting.java",  "\\W++"));
        int totalVowels = 0;
        for (String word : words) {
            int vowelsInWord = 0;
            for (char c : word.toLowerCase().toCharArray())
                if (vowels.contains(c))
                    vowelsInWord++;
            totalVowels += vowelsInWord;
            System.out.println("Vowels in word '" + word + "': " + vowelsInWord);
        }
        System.out.println("Total vowels: " + totalVowels);
    }
}
/* Output:
Vowels in word '0': 0
Vowels in word '16': 0
Vowels in word 'Character': 3
Vowels in word 'Collections': 4
Vowels in word 'Create': 3
Vowels in word 'E16_VowelsCounting': 6
Vowels in word 'Exercise': 4
Vowels in word 'HashSet': 2
Vowels in word 'Set': 1
Vowels in word 'String': 1
Vowels in word 'System': 1
Vowels in word 'TextFile': 3
Vowels in word 'Total': 2
Vowels in word 'TreeSet': 3
Vowels in word 'UniqueWords': 5
Vowels in word 'Vowels': 2
Vowels in word 'W': 0
Vowels in word 'Working': 2
Vowels in word 'a': 1
Vowels in word 'addAll': 2
Vowels in word 'also': 2
Vowels in word 'and': 1
Vowels in word 'args': 1
Vowels in word 'c': 0
Vowels in word 'chapter11': 2
Vowels in word 'char': 1
Vowels in word 'class': 1
Vowels in word 'contains': 3
Vowels in word 'count': 2
Vowels in word 'display': 2
Vowels in word 'e': 1
Vowels in word 'each': 2
Vowels in word 'exercises': 4
Vowels in word 'file': 2
Vowels in word 'for': 1
Vowels in word 'from': 1
Vowels in word 'i': 1
Vowels in word 'if': 1
Vowels in word 'import': 2
Vowels in word 'in': 1
Vowels in word 'input': 2
Vowels in word 'int': 1
Vowels in word 'java': 2
Vowels in word 'main': 2
Vowels in word 'mindview': 3
Vowels in word 'net': 1
Vowels in word 'new': 1
Vowels in word 'number': 2
Vowels in word 'o': 1
Vowels in word 'of': 1
Vowels in word 'out': 2
Vowels in word 'package': 3
Vowels in word 'println': 1
Vowels in word 'public': 2
Vowels in word 'src': 0
Vowels in word 'static': 2
Vowels in word 'the': 1
Vowels in word 'toCharArray': 4
Vowels in word 'toLowerCase': 5
Vowels in word 'total': 2
Vowels in word 'totalVowels': 4
Vowels in word 'u': 1
Vowels in word 'util': 2
Vowels in word 'void': 2
Vowels in word 'vowels': 2
Vowels in word 'vowelsInWord': 4
Vowels in word 'word': 1
Vowels in word 'words': 1
Total vowels: 131
 */