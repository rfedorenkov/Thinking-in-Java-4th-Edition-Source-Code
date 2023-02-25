package exercises.chapter17;

import containers.SlowMap;
import net.mindview.util.TextFile;

import java.util.List;
import java.util.Map;

import static net.mindview.util.Print.print;

/**
 * Exercise 15
 * Repeat Exercise 13 using a SlowMap.
 */
public class E15_WordsCounter2 {
    public static void main(String[] args) {
        List<String> words = new TextFile("src/exercises/chapter17/E15_WordsCounter2.java", "\\W+");
        Map<String, Integer> map = new SlowMap<>();
        for (String word : words) {
            Integer value = map.get(word);
            map.put(word, value == null ? 1 : value + 1);
        }
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            print(entry);
        }
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
java=3
Print=1
Exercise=2
Entry=1
exercises=2
public=2
W=1
get=1
List=2
map=4
class=1
net=2
value=3
a=1
static=2
package=1
void=1
13=1
using=1
new=2
src=1
entrySet=1
15=1
TextFile=2
words=2
chapter17=2
util=4
print=2
args=1
Integer=3
1=2
entry=2
null=1
containers=1
E15_WordsCounter2=2
SlowMap=3
Map=3
word=3
 */