package exercises.chapter2;

/**
 * Exercise 13
 * Run Documentation1.java, Documentation2.java and Documentation3.java
 * through Javadoc. Verify the resulting documentation with you Web browser.
 */
public class E13_DocsTest {
    /**
     * A class comment
     */
    class Documentation1 {

        /**
         * A field comment
         */
        public int i;

        /**
         * A method comment
         */
        public void f() {}
    }

    /**
     * <pre>
     *     System.out.println(new Date());
     * </pre>
     */
    class Documentation2 {
    }

    /**
     * You can <em>even</em> insert a list:
     * <ol>
     *     <li> Item one
     *     <li> Item two
     *     <li> Item three
     * </ol>
     */
    class Documentation3 {
    }
}