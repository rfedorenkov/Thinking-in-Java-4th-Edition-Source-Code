package exercises.chapter19;

import net.mindview.util.Enums;

/**
 * Exercise 4
 * Repeat the above exercise for Meal2.java
 */
public enum E04_Meal2 {
    APPETIZER(Food.Appetizer.class),
    MAINCOURSE(Food.MainCourse.class),
    DRINK(Food.Drink.class),
    DESERT(Food.Dessert.class),
    COFFEE(Food.Coffee.class);

    private Food[] values;

    private E04_Meal2(Class<? extends Food> kind) {
        values = kind.getEnumConstants();
    }

    public interface Food {
        enum Appetizer implements Food {
            SALAD, SOUP, SPRING_ROLLS;
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

    public Food randomSelection() {
        return Enums.random(values);
    }

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            for (E04_Meal2 meal : E04_Meal2.values()) {
                Food food = meal.randomSelection();
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