package exercises.chapter11;

import net.mindview.util.TextFile;

import java.util.*;

/**
 * Exercise 25
 * Create a Map<String, ArrayList<Integer>>.
 * Use net.mindview.TextFile to open a text file and read it in a word
 * at a time (use "\\W+" as the second argument to the TextFile constructor).
 * Count the words as you read them in, and for each word in the file,
 * record in the ArrayList<Integer> the word count associated with
 * that word - this is, in effect, the location in the file where that
 * word was found.
 */
public class E25_UniqueWordsInfo3 {
    public static void main(String[] args) {
        Map<String, ArrayList<Integer>> m = new HashMap<>();
        int wordCount = 0;
        List<String> originalWords = new TextFile("src/exercises/chapter11/E25_UniqueWordsInfo3.java", "\\W");
        originalWords.removeIf(s -> s.equals(""));
        for (String s : originalWords) {
            ArrayList<Integer> list = m.get(s);
            if (list == null) {
                list = new ArrayList<>();
                m.put(s, list);
            }
            list.add(++wordCount);
        }
        for (Map.Entry<String, ArrayList<Integer>> entry : m.entrySet()) {
            System.out.println("\"" + entry.getKey() + "\"" + ": " + entry.getValue());
        }
        System.out.println("*************************");
        System.out.println("Total words: " + wordCount);
    }
}
/* Output:
"argument": [43]
"HashMap": [104]
"use": [38]
"ArrayList": [18, 66, 100, 128, 139, 151]
"main": [95]
"String": [17, 96, 99, 109, 125, 150]
"Count": [48]
"put": [141]
"second": [42]
"that": [73, 85]
"java": [10, 117]
"Exercise": [12]
"exercises": [2, 114]
"record": [63]
"List": [108]
"text": [27]
"net": [5, 21]
"if": [134]
"you": [52]
"add": [145]
"new": [103, 111, 138]
"getKey": [160]
"package": [1]
"read": [30, 53]
"static": [93]
"void": [94]
"wordCount": [106, 146, 171]
"in": [32, 55, 60, 64, 77, 81]
"entrySet": [155]
"constructor": [47]
"count": [70]
"this": [75]
"them": [54]
"is": [76]
"it": [31]
"list": [130, 135, 137, 143, 144]
"each": [58]
"removeIf": [120]
"0": [107]
"entry": [153, 159, 161]
"as": [40, 51]
"at": [35]
"util": [7, 11]
"null": [136]
"effect": [78]
"Map": [16, 98, 148]
"word": [34, 59, 69, 74, 86]
"25": [13]
"import": [4, 9]
"originalWords": [110, 119, 127]
"for": [57, 124, 147]
"mindview": [6, 22]
"System": [156, 163, 166]
"out": [157, 164, 167]
"Entry": [149]
"println": [158, 165, 168]
"file": [28, 62, 83]
"found": [88]
"public": [89, 92]
"Create": [14]
"and": [29, 56]
"W": [39, 118]
"get": [132]
"where": [84]
"E25_UniqueWordsInfo3": [91, 116]
"class": [90]
"a": [15, 26, 33, 36]
"src": [113]
"Use": [20]
"TextFile": [8, 23, 46, 112]
"words": [50, 170]
"was": [87]
"m": [102, 131, 140, 154]
"int": [105]
"Integer": [19, 67, 101, 129, 152]
"the": [41, 45, 49, 61, 65, 68, 79, 82]
"args": [97]
"with": [72]
"getValue": [162]
"chapter11": [3, 115]
"s": [121, 122, 126, 133, 142]
"associated": [71]
"equals": [123]
"Total": [169]
"location": [80]
"to": [24, 44]
"time": [37]
"open": [25]
*************************
Total words: 171
 */