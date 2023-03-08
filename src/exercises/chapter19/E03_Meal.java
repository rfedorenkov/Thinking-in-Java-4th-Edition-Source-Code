package exercises.chapter19;

import net.mindview.util.Enums;

/**
 * Exercise 3
 * Add a new Course to Course.java and demonstrate
 * that it works in Meal.java.
 */
interface Food {
    enum Appetizer implements Food {
        SALAD, SOUP, SPRING_ROLLS
    }

    enum MainCourse implements Food {
        LASAGNE, BURRITO, PAD_THAI,
        LENTILS, HUMMOUS, VINDALOO
    }

    enum Drink implements Food {
        COLA, JUICE, SPRING_WATER, SPARKLING_WATER,
        BEER, WINE, RUM, WHISKY
    }

    enum Dessert implements Food {
        TIRAMISU, GELATO, BLACK_FOREST_CALE,
        FRUIT, CREME_CARAMEL
    }

    enum Coffee implements Food {
        BLACK_COFFEE, DECAF_COFFEE, ESPRESSO,
        LATTE, CAPPUCCINO, TEA, HERB_TEA
    }
}
enum Course {
    APPETIZER(Food.Appetizer.class),
    MAINCOURSE(Food.MainCourse.class),
    DRINK(Food.Drink.class),
    DESSERT(Food.Dessert.class),
    COFFEE(Food.Coffee.class);

    private Food[] values;

    private Course(Class<? extends Food> kind) {
        values = kind.getEnumConstants();
    }

    public Food randomSelection() {
        return Enums.random(values);
    }
}

public class E03_Meal {
    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            for (Course course : Course.values()) {
                Food food = course.randomSelection();
                System.out.println(food);
            }
            System.out.println("---");
        }
    }
}
/* Output:
SPRING_ROLLS
VINDALOO
BEER
GELATO
TEA
---
SPRING_ROLLS
HUMMOUS
SPARKLING_WATER
BLACK_FOREST_CALE
BLACK_COFFEE
---
SALAD
LASAGNE
SPRING_WATER
CREME_CARAMEL
LATTE
---
SOUP
HUMMOUS
JUICE
TIRAMISU
ESPRESSO
---
SOUP
LASAGNE
BEER
BLACK_FOREST_CALE
BLACK_COFFEE
---
 */