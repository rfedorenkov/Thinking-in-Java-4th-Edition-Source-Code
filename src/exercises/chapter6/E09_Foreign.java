package exercises.chapter6;

/**
 * Exercise 9
 * Create in access/local directory in your CLASSPATH:
 * access/local/PackagedClass.java
 * Then in another directory create the file below and explain
 * why compiler generates error.Would making Foreign class part
 * of access.local change anything?
 */
public class E09_Foreign {
    public static void main(String[] args) {
        //! PackagedClass pc = new PackagedClass(); // cannot be accessed from outside package
    }
}