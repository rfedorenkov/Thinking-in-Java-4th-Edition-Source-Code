package exercises.chapter18;

import net.mindview.util.TextFile;
import nu.xom.Document;
import nu.xom.Element;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Exercise 32
 * Using a Map<String, Integer> and the net.mindview.util.TextFile
 * utility, write a program that counts the occurrence of words in
 * a file (use "\\W+" as the second argument to the TextFile constructor).
 * Store the results as an XML file.
 * {Requires: nu.xom.Node; You must install
 * the XOM library from http://www.xom.nu }
 */
public class E32_WordsCountXML {

    public static Element getXML(Map.Entry<String, Integer> entry) {
        Element word = new Element("word");
        Element text = new Element("text");
        text.appendChild(entry.getKey());
        Element frequency = new Element("frequency");
        frequency.appendChild(Integer.toString(entry.getValue()));
        word.appendChild(text);
        word.appendChild(frequency);
        return word;
    }

    public static void main(String[] args) throws Exception {
        String fileName = "src/exercises/chapter18/E32_WordsCountXML.java";
        Map<String, Integer> map = new HashMap<>();
        for (String word : new TextFile(fileName, "\\W+")) {
            Integer value = map.get(word);
            map.put(word, value == null ? 1 : value + 1);
        }
        Element root = new Element("words");
        for (Map.Entry<String, Integer> entry : map.entrySet())
            root.appendChild(getXML(entry));
        Document doc = new Document(root);
        Person.format(System.out, doc);
        Person.format(new BufferedOutputStream(
                (new FileOutputStream("src/exercises/chapter18/E32_Words.xml"))), doc);
    }
}
/* (Execute to see output) */