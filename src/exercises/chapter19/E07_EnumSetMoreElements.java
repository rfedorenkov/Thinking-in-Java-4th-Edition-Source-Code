package exercises.chapter19;

/**
 * Exercise 7
 * Find the source code for EnumSet and explain how it works.
 */
public class E07_EnumSetMoreElements {
    // EnumSet is an abstract class with two private implementation
    // classes: JumboEnumSet for types with more than 64 elements,
    // and RegularEnumSet for types with up to 64 elements. The main
    // factory method (entry point) of the EnumSet class is noneOf(),
    // which the other static methods also call. The design comes from
    // Patterns for Encapsulating Class Trees.
    // TIJ4 describes EnumSet's very efficient bit vector representation,
    // whereby JumboEnumSet uses an array of longs, while RegularEnumSet
    // uses a single long.
}