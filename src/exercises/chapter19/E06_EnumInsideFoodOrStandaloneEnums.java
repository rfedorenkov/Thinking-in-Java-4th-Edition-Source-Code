package exercises.chapter19;

/**
 * Exercise 6
 * Is there any special benefit in nexting Appetizer, MainCourse,
 * Desert, and Coffee inside Food rather than making then standalone
 * enums that just happen to implement Food?
 */
public class E06_EnumInsideFoodOrStandaloneEnums {
    // We nest Appetizer, MainCourse, Dessert, and Coffee inside Food
    // Rather than making them independent enums that coincidentally
    // use Food; this produces a clearer structure. When you see
    // Food.Appetizer in the code, you know the terms are related.
    // The clarity and comprehensibility of code are crucial, especially
    // during maintenance. Remember, when you reduce software maintenance
    // you increase profit.
    // Nesting has another benefit when you use reflection to build up
    // the grouping enum (like Course from TIJ4). Try this as an additional
    // exercise with TIJ4's enumerated/menu/Meal.java program. Pay special
    // attention to overcoming the JDK's constraints on the java.lang.Enum class.
}