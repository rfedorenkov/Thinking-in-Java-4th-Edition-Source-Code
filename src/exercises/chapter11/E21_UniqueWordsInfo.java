package exercises.chapter11;

import net.mindview.util.TextFile;

import java.util.*;

/**
 * Exercise 21
 * Using a Map<String, Integer>, follow the form of UniqueWords.java
 * to create a program that counts the occurrence of words in a file.
 * Sort the results using Collections.sort() with a second argument
 * of String.CASE_INSENSITIVE_ORDER (to produce an alphabetic sort),
 * and display the results.
 */
public class E21_UniqueWordsInfo {
    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        for (String word : new TextFile("src/exercises/chapter11/E21_UniqueWordsInfo.java", "\\W+")) {
            Integer i = map.get(word);
            map.put(word, i == null ? 1 : i + 1);
        }
        List<String> list = new ArrayList<>(map.keySet());
        Collections.sort(list, String.CASE_INSENSITIVE_ORDER);
        for (String key : list) {
            Integer value = map.get(key);
            System.out.println(key + ": " + value);
        }
    }
}
/* Output:
1: 2
21: 1
a: 4
alphabetic: 1
an: 1
and: 1
args: 1
argument: 1
ArrayList: 1
CASE_INSENSITIVE_ORDER: 2
chapter11: 2
class: 1
Collections: 2
counts: 1
create: 1
display: 1
E21_UniqueWordsInfo: 2
Exercise: 1
exercises: 2
file: 1
follow: 1
for: 2
form: 1
get: 2
HashMap: 1
i: 3
import: 2
in: 1
Integer: 4
java: 3
key: 3
keySet: 1
List: 1
list: 3
main: 1
Map: 2
map: 5
mindview: 1
net: 1
new: 3
null: 1
occurrence: 1
of: 3
out: 1
package: 1
println: 1
produce: 1
program: 1
public: 2
put: 1
results: 2
second: 1
Sort: 1
sort: 3
src: 1
static: 1
String: 8
System: 1
TextFile: 2
that: 1
the: 4
to: 2
UniqueWords: 1
Using: 1
using: 1
util: 2
value: 2
void: 1
W: 1
with: 1
word: 3
words: 1
 */