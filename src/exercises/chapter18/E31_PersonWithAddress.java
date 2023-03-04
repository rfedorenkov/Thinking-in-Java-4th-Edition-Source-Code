package exercises.chapter18;

import nu.xom.*;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Exercise 31
 * Add appropriate address information to Person.java and People.java
 * {Requires: nu.xom.Node; You must install
 * the XOM library from http://www.xom.nu }
 */
class People extends ArrayList<Person> {
    public People(String fileName) throws Exception {
        Document doc = new Builder().build(fileName);
        Elements elements = doc.getRootElement().getChildElements();
        for (int i = 0; i < elements.size(); i++)
            add(new Person(elements.get(i)));
    }
}

class Person {
    private String first, last, address;

    public Person(String first, String last, String address) {
        this.first = first;
        this.last = last;
        this.address = address;
    }

    // Produce an XML Element from this Person object:
    public Element getXML() {
        Element person = new Element("person");
        Element firstName = new Element("first");
        firstName.appendChild(first);
        Element lastName = new Element("last");
        lastName.appendChild(last);
        Element homeAddress = new Element("address");
        homeAddress.appendChild(address);
        person.appendChild(firstName);
        person.appendChild(lastName);
        person.appendChild(homeAddress);
        return person;
    }

    // Constructor to restore a Person from an XML Element:
    public Person(Element person) {
        first = person.getFirstChildElement("first").getValue();
        last = person.getFirstChildElement("last").getValue();
        address = person.getFirstChildElement("address").getValue();
    }

    @Override
    public String toString() {
        return first + " " + last + " " + address;
    }

    // Make it human-readable:
    public static void format(OutputStream os, Document doc) throws Exception {
        Serializer serializer = new Serializer(os, "ISO-8859-1");
        serializer.setIndent(4);
        serializer.setMaxLength(60);
        serializer.write(doc);
        serializer.flush();
    }
}

public class E31_PersonWithAddress {
    public static void main(String[] args) throws Exception {
        List<Person> people = Arrays.asList(
                new Person("Dr. Bunsen", "Honeydew", "The Muppet Show"),
                new Person("Gonzo", "The Great", "planet Oznog"),
                new Person("Phillip J.", "Fry", "Brooklyn NY"));
        System.out.println(people);
        Element root = new Element("people");
        for (Person p : people)
            root.appendChild(p.getXML());
        Document doc = new Document(root);
        Person.format(System.out, doc);
        Person.format(new BufferedOutputStream(
                new FileOutputStream("src/exercises/chapter18/E31_People.xml")), doc);

        People p = new People("src/exercises/chapter18/E31_People.xml");
        System.out.println(p);
    }
}
/* Output:
[Dr. Bunsen Honeydew The Muppet Show, Gonzo The Great planet Oznog, Phillip J. Fry Brooklyn NY]
<?xml version="1.0" encoding="ISO-8859-1"?>
<people>
    <person>
        <first>Dr. Bunsen</first>
        <last>Honeydew</last>
        <address>The Muppet Show</address>
    </person>
    <person>
        <first>Gonzo</first>
        <last>The Great</last>
        <address>planet Oznog</address>
    </person>
    <person>
        <first>Phillip J.</first>
        <last>Fry</last>
        <address>Brooklyn NY</address>
    </person>
</people>
[Dr. Bunsen Honeydew The Muppet Show, Gonzo The Great planet Oznog, Phillip J. Fry Brooklyn NY]
 */