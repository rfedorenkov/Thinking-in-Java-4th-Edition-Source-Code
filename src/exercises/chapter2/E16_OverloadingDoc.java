package exercises.chapter2;

/**
 * Exercise 16
 * In the Initialization & Cleanup chapter, locate the Overloading.java
 * example and add Javadoc documentation. Extract this comment documentation
 * into an HTML file using Javadoc and view it with you Web browser.
 */
public class E16_OverloadingDoc {
}

/**
 * Tree class with two constructors and two info methods
 */
class Tree {
    /**
     * Current tree height
     */
    int height; // 0 by default

    /**
     * We create a tree. Constructor without parameters.
     * Height is 0.
     */
    public Tree() {
        System.out.println("Planting a seedling");
        height = 0;
    }

    /**
     * We create a tree. Constructor with one parameter.
     * @param initialHeight Initial tree height.
     */
    public Tree(int initialHeight) {
        height = initialHeight;
        System.out.println("Creating new Tree that is " + height + " feet tall");
    }

    /**
     * Print tree information including height.
     */
    public void info() {
        System.out.println("Tree is " + height + " feet tall");
    }

    /**
     * Overloading method.
     * Print tree information including height.
     * @param s Optional message.
     */
    public void info(String s) {
        System.out.println(s + ": Tree is " + height + " feet tall");
    }
}

/**
 * Simple code to test tree class.
 */
class Overloading {
    /**
     * Creates <b>Tree</b> objects and executes two different <code>info()</code> methods.
     */
    public static void main(String[] args) {
        for (int i = 0; i < 5; i ++) {
            Tree t = new Tree(i);
            t.info();
            t.info("overloading method");
        }
        // Overloaded constructor:
        new Tree();
    }
}