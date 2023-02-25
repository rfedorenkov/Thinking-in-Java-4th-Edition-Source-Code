package exercises.chapter17;

import containers.SimpleHashMap;
import net.mindview.util.TextFile;

import java.util.List;
import java.util.Map;

import static net.mindview.util.Print.print;

/**
 * Exercise 19
 * Repeat Exercise 13 using a SimpleHashMap.
 */
public class E19_WordsCounter3 {
    public static void main(String[] args) {
        List<String> words = new TextFile("src/exercises/chapter17/E19_WordsCounter3.java", "\\W+");
        SimpleHashMap<String, Integer> map = new SimpleHashMap<>();
        for (String word : words) {
            Integer value = map.get(word);
            map.put(word, value == null ? 1 : value + 1);
        }
        for (Map.Entry<String, Integer> entry : map.entrySet())
            print(entry);
    }
}
/* Output:
import=5
for=2
Repeat=1
main=1
mindview=2
String=5
put=1
Print=1
java=3
Exercise=2
E19_WordsCounter3=2
Entry=1
exercises=2
W=1
get=1
public=2
List=2
map=4
class=1
net=2
value=3
a=1
static=2
void=1
SimpleHashMap=4
package=1
using=1
new=2
13=1
src=1
entrySet=1
19=1
TextFile=2
words=2
chapter17=2
util=4
print=2
args=1
1=2
entry=2
Integer=3
null=1
containers=1
word=3
Map=2
 */