package exercises.chapter11;

import net.mindview.util.TextFile;

import java.util.*;

/**
 * Exercise 22
 * Modify the previous exercise so that it uses a class
 * containing a String and a count field to store each
 * different word, and a Set of these objects to maintain
 * the list of words.
 */
class WordCounter {
    public static final Comparator<WordCounter> CASE_INSENSITIVE_ORDER = new Comparator<WordCounter>() {
        @Override
        public int compare(WordCounter wc1, WordCounter wc2) {
            return wc1.word.compareToIgnoreCase(wc2.word);
        }
    };

    private static int totalWords = 0;
    private final String word;
    private int frequency;

    public WordCounter(String word) {
        this.word = word;
        increase();
    }

    public void increase() {
        frequency++;
        totalWords++;
    }

    public static int getTotalWords() {
        return totalWords;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof WordCounter && word.equals(((WordCounter) o).word);
    }

    @Override
    public int hashCode() {
        return word.hashCode();
    }

    @Override
    public String toString() {
        return word + ": " + frequency;
    }
}

public class E22_UniqueWordsInfo2 {
    public static void updateWord(Collection<WordCounter> col, WordCounter word) {
        for (WordCounter w : col)
            if (w.equals(word))
                w.increase();
    }

    public static void main(String[] args) {
        Set<WordCounter> set = new HashSet<>();
        for (String s : new TextFile("src/exercises/chapter11/E22_UniqueWordsInfo2.java", "\\W+")) {
            WordCounter word = new WordCounter(s);
            if (set.contains(word))
                updateWord(set, word);
            else
                set.add(word);
        }
        List<WordCounter> list = new ArrayList<>(set);
        list.sort(WordCounter.CASE_INSENSITIVE_ORDER);
        for (WordCounter word : list)
            System.out.println(word);
        System.out.println("*************************");
        System.out.println("Total words: " + WordCounter.getTotalWords());
    }
}
/* Output:
0: 1
22: 1
a: 4
add: 1
and: 2
args: 1
ArrayList: 1
boolean: 1
CASE_INSENSITIVE_ORDER: 2
chapter11: 2
class: 3
col: 2
Collection: 1
Comparator: 2
compare: 1
compareToIgnoreCase: 1
containing: 1
contains: 1
count: 1
different: 1
E22_UniqueWordsInfo2: 2
each: 1
else: 1
equals: 3
Exercise: 1
exercise: 1
exercises: 2
field: 1
final: 2
for: 3
frequency: 3
getTotalWords: 2
hashCode: 2
HashSet: 1
if: 2
import: 2
increase: 3
instanceof: 1
int: 5
it: 1
java: 2
List: 1
list: 4
main: 1
maintain: 1
mindview: 1
Modify: 1
net: 1
new: 5
o: 3
Object: 1
objects: 1
of: 2
out: 3
Override: 4
package: 1
previous: 1
println: 3
private: 3
public: 11
return: 5
s: 2
Set: 2
set: 5
so: 1
sort: 1
src: 1
static: 5
store: 1
String: 6
System: 3
TextFile: 2
that: 1
the: 2
these: 1
this: 1
to: 2
toString: 1
Total: 1
totalWords: 3
updateWord: 2
uses: 1
util: 2
void: 3
W: 1
w: 3
wc1: 2
wc2: 2
word: 19
WordCounter: 18
words: 2
*************************
Total words: 349
 */