package exercises.chapter17;

import net.mindview.util.TextFile;

import java.util.List;

import static net.mindview.util.Print.print;

/**
 * Exercise 13
 * Use AssociativeArray.java to create a word-occurrence counter,
 * mapping String to Integer. Using the net.mindview.util.TextFile
 * utility in this book, open a text file and break up the words
 * in that file using whitespace and punctuation, and count the
 * occurrence of the words in that file.
 */
class AssociativeArray<K, V> {
    private Object[][] pairs;
    private int index;

    public AssociativeArray(int length) {
        pairs = new Object[length][2];
    }

    public void put(K key, V value) {
        for (int i = 0; i < index; i++)
            if (pairs[i][0].equals(key)) {
                pairs[i][1] = value;
                return;
            }
        if (index >= pairs.length)
            throw new ArrayIndexOutOfBoundsException();
        pairs[index++] = new Object[]{ key, value };
    }

    @SuppressWarnings("unchecked")
    public V get(K key) {
        for (int i = 0; i < index; i++)
            if (key.equals(pairs[i][0]))
                return (V) pairs[i][1];
        return null; // Did not find key
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < index; i++) {
            result.append(pairs[i][0].toString());
            result.append(" : ");
            result.append(pairs[i][1].toString());
            if (i < index - 1)
                result.append("\n");
        }
        return result.toString();
    }
}

public class E13_WordsCounter {
    public static void main(String[] args) {
        List<String> words = new TextFile("src/exercises/chapter17/E13_WordsCounter.java", "\\W+");
        AssociativeArray<String, Integer> map = new AssociativeArray<>(words.size());
        for (String word : words) {
            Integer value = map.get(word);
            map.put(word, value == null ? 1 : value + 1);
        }
        print(map);
    }
}
/* Output:
package : 1
exercises : 2
chapter17 : 2
import : 3
net : 3
mindview : 3
util : 4
TextFile : 3
java : 3
List : 2
static : 2
Print : 1
print : 2
Exercise : 1
13 : 1
Use : 1
AssociativeArray : 5
to : 2
create : 1
a : 2
word : 4
occurrence : 2
counter : 1
mapping : 1
String : 6
Integer : 3
Using : 1
the : 4
utility : 1
in : 3
this : 1
book : 1
open : 1
text : 1
file : 3
and : 3
break : 1
up : 1
words : 5
that : 2
using : 1
whitespace : 1
punctuation : 1
count : 1
of : 1
class : 2
K : 3
V : 4
private : 2
Object : 3
pairs : 10
int : 5
index : 7
public : 6
length : 3
new : 6
2 : 1
void : 2
put : 2
key : 6
value : 6
for : 4
i : 16
0 : 6
if : 4
equals : 2
1 : 6
return : 4
throw : 1
ArrayIndexOutOfBoundsException : 1
SuppressWarnings : 1
unchecked : 1
get : 2
null : 2
Did : 1
not : 1
find : 1
Override : 1
toString : 4
StringBuilder : 2
result : 6
append : 4
n : 1
E13_WordsCounter : 2
main : 1
args : 1
src : 1
W : 1
map : 4
size : 1
 */