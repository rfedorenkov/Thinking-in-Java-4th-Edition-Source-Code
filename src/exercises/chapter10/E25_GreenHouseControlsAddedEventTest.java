package exercises.chapter10;

import innerclasses.GreenhouseControls;
import innerclasses.controller.Event;

/**
 * Exercise 25
 * Inherit from GreenhouseControls in GreenhouseControls.java
 * add Event inner classes that turn water mist generators on
 * and off. Write a new version of GreenhouseController.java
 * to use these new Event objects.
 * {Args: 5000}
 */
class GreenHouseWithWaterMistGenerators extends GreenhouseControls {
    private boolean generator = false;

    class WaterMistGeneratorOn extends Event {

        public WaterMistGeneratorOn(long delayTime) {
            super(delayTime);
        }

        @Override
        public void action() {
            // Put hardware control code here
            generator = true;
        }

        @Override
        public String toString() {
            return "Water mist generator is on";
        }
    }

    class WaterMistGeneratorOff extends Event {

        public WaterMistGeneratorOff(long delayTime) {
            super(delayTime);
        }

        @Override
        public void action() {
            // Put hardware control code here
            generator = false;
        }

        @Override
        public String toString() {
            return "Water mist generator is off";
        }
    }
}

public class E25_GreenHouseControlsAddedEventTest {
    public static void main(String[] args) {
        GreenHouseWithWaterMistGenerators gc = new GreenHouseWithWaterMistGenerators();
        // Instead of hard-wiring, you could parse
        // configuration information from a text file here:
        gc.addEvent(gc.new Bell(900));
        Event[] eventList = {
                gc.new ThermostatNight(0),
                gc.new LightOn(200),
                gc.new LightOff(400),
                gc.new WaterOn(600),
                gc.new WaterOff(800),
                gc.new ThermostatDay(1400),
                gc.new WaterMistGeneratorOn(1600),
                gc.new WaterMistGeneratorOff(1800)
        };
        gc.addEvent(gc.new Restart(2000, eventList));
        if (args.length == 1)
            gc.addEvent(
                    new GreenhouseControls.Terminate(
                            new Integer(args[0])));
        gc.run();
    }
}
/* Output:
Bing!
Thermostat on night setting
Light is on
Light is off
Greenhouse water is on
Greenhouse water is off
Thermostat on day setting
Water mist generator is on
Water mist generator is off
Restarting system
Terminating
 */