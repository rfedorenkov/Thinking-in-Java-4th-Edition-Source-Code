package exercises.chapter10;

import innerclasses.GreenhouseControls;
import innerclasses.controller.Event;

/**
 * Exercise 24
 * Add Event inner classes that turn fans on and off
 * in GreenhouseControls.java. Configure GreenhouseControl.java
 * to use these new Event objects.
 * {Args: 5000}
 */
class GreenhouseControlsWithFans extends GreenhouseControls {
    private boolean fan = false;

    class FanOn extends Event {

        public FanOn(long delayTime) {
            super(delayTime);
        }

        @Override
        public void action() {
            // Put hardware control code here
            fan = true;
        }

        @Override
        public String toString() {
            return "Fan is on";
        }
    }

    class FanOff extends Event {

        public FanOff(long delayTime) {
            super(delayTime);
        }

        @Override
        public void action() {
            // Put hardware control code here
            fan = false;
        }

        @Override
        public String toString() {
            return "Fan is off";
        }
    }
}

public class E24_GreenhouseControlsAddedEventTest {
    public static void main(String[] args) {
        GreenhouseControlsWithFans gc = new GreenhouseControlsWithFans();
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
                gc.new FanOn(1600),
                gc.new FanOff(1800)
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
Fan is on
Fan is off
Restarting system
Terminating
 */