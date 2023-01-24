package exercises.chapter12;

import polymorphism.Frog;

/**
 * Exercise 17
 * Modify polymorphism/Frog.java so that it uses try-finally
 * to guarantee proper cleanup, and show that this works even
 * if you return from the middle of the try-finally.
 */
class Frog2 extends Frog {
    @Override
    protected void dispose() {
        super.dispose();
    }
}

public class E17_FrogWithFinallyTest {
    public static void main(String[] args) {
        Frog2 frog = new Frog2();
        try {
            // No return
        } finally {
            frog.dispose();
        }
        frog = new Frog2();
        try {
            // With return
            return;
        } finally {
            frog.dispose();
        }
    }
}
/* Output:
Creating Characteristic is alive
Creating Description Basic Living Creature
LivingCreature()
Creating Characteristic has heart
Creating Description Animal nor Vegetable
Animal
Creating Characteristic can live in water
Creating Description Both water and land
Amphibian()
Creating Characteristic Croaks
Creating Description Eats Bugs
Frog()
Frog dispose
disposing Description Eats Bugs
disposing Characteristic Croaks
Amphibian dispose
disposing Description Both water and land
disposing Characteristic can live in water
Animal dispose
disposing Description Animal nor Vegetable
disposing Characteristic has heart
LivingCreature dispose
disposing Description Basic Living Creature
disposing Characteristic is alive
Creating Characteristic is alive
Creating Description Basic Living Creature
LivingCreature()
Creating Characteristic has heart
Creating Description Animal nor Vegetable
Animal
Creating Characteristic can live in water
Creating Description Both water and land
Amphibian()
Creating Characteristic Croaks
Creating Description Eats Bugs
Frog()
Frog dispose
disposing Description Eats Bugs
disposing Characteristic Croaks
Amphibian dispose
disposing Description Both water and land
disposing Characteristic can live in water
Animal dispose
disposing Description Animal nor Vegetable
disposing Characteristic has heart
LivingCreature dispose
disposing Description Basic Living Creature
disposing Characteristic is alive
 */