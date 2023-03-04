package io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static net.mindview.util.Print.print;

class House implements Serializable {
}

class Animal implements Serializable {
    private String name;
    private House preferredHouse;

    Animal(String nm, House h) {
        name = nm;
        preferredHouse = h;
    }

    @Override
    public String toString() {
        return name + "[" + super.toString() +
                "], " + preferredHouse + "\n";
    }
}

public class MyWorld {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        House house = new House();
        List<Animal> animals = new ArrayList<>();
        animals.add(new Animal("Bosco the dog", house));
        animals.add(new Animal("Ralph the hamster", house));
        animals.add(new Animal("Molly the cat", house));
        print("animal: " + animals);
        ByteArrayOutputStream buf1 = new ByteArrayOutputStream();
        ObjectOutputStream o1 = new ObjectOutputStream(buf1);
        o1.writeObject(animals);
        o1.writeObject(animals); // Write a 2nd set
        // Write to different stream:
        ByteArrayOutputStream buf2 = new ByteArrayOutputStream();
        ObjectOutputStream o2 = new ObjectOutputStream(buf2);
        o2.writeObject(animals);
        // Now get them back:
        ObjectInputStream in1 = new ObjectInputStream(
                new ByteArrayInputStream(buf1.toByteArray()));
        ObjectInputStream in2 = new ObjectInputStream(
                new ByteArrayInputStream(buf1.toByteArray()));
        List
                animals1 = (List) in1.readObject(),
                animals2 = (List) in1.readObject(),
                animals3 = (List) in2.readObject();
        print("animals1: " + animals1);
        print("animals2: " + animals2);
        print("animals3: " + animals3);
    }
}
/* Output: (Sample)
animal: [Bosco the dog[io.Animal@7c75222b], io.House@edf4efb
, Ralph the hamster[io.Animal@2f7a2457], io.House@edf4efb
, Molly the cat[io.Animal@566776ad], io.House@edf4efb
]
animals1: [Bosco the dog[io.Animal@1d057a39], io.House@26be92ad
, Ralph the hamster[io.Animal@4c70fda8], io.House@26be92ad
, Molly the cat[io.Animal@224edc67], io.House@26be92ad
]
animals2: [Bosco the dog[io.Animal@1d057a39], io.House@26be92ad
, Ralph the hamster[io.Animal@4c70fda8], io.House@26be92ad
, Molly the cat[io.Animal@224edc67], io.House@26be92ad
]
animals3: [Bosco the dog[io.Animal@14acaea5], io.House@46d56d67
, Ralph the hamster[io.Animal@d8355a8], io.House@46d56d67
, Molly the cat[io.Animal@59fa1d9b], io.House@46d56d67
]
 */