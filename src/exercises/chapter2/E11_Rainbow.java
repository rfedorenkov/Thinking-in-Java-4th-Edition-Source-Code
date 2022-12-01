package exercises.chapter2;

public class E11_Rainbow {
    public static void main(String[] args) {
        AllTheColorsOfTheRainbow rainbow = new AllTheColorsOfTheRainbow();
        System.out.println("rainbow.anIntegerRepresentingColors = " + rainbow.anIntegerRepresentingColors);
        System.out.println("rainbow.hue = " + rainbow.hue);
        System.out.println("After color change:");
        rainbow.changeColor(7);
        rainbow.changeTheHueOfTheColor(50);
        System.out.println("rainbow.anIntegerRepresentingColors = " + rainbow.anIntegerRepresentingColors);
        System.out.println("rainbow.hue = " + rainbow.hue);
    }
}
/* Output:
rainbow.anIntegerRepresentingColors = 0
rainbow.hue = 0
After color change:
rainbow.anIntegerRepresentingColors = 7
rainbow.hue = 50
 */

class AllTheColorsOfTheRainbow {
    int anIntegerRepresentingColors;
    int hue;

    void changeTheHueOfTheColor(int newHue) {
        hue = newHue;
    }

    void changeColor(int newColor) {
        anIntegerRepresentingColors = newColor;
    }
}