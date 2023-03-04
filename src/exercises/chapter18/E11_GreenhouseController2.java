package exercises.chapter18;

import innerclasses.GreenhouseControls;
import innerclasses.controller.Event;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Constructor;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Exercise 11
 * In the innerclasses/GreenhouseController.java example,
 * GreenhouseController contains a hard-coded set of events.
 * Change the program so that it reads the events and their
 * relative times from a text file. (Challenging: Use a Factory
 * Method design pattern to build the events-see
 * Thinking in Patterns (with Java) at www.MindView.net).
 * {Args: 5000}
 */
class GreenhouseControls2 extends GreenhouseControls {
    public class Restart extends Event {
        private Event[] eventList;

        public Restart(long delayTime) {
            super(delayTime);
        }

        public void setEventList(Event[] eventList) {
            this.eventList = eventList;
        }

        @Override
        public void action() {
            for (Event e : eventList) {
                e.start(); // Rerun each event
                addEvent(e);
            }
            start(); // Rerun this event
            addEvent(this);
        }

        @Override
        public String toString() {
            return "Restarting system";
        }
    }

    class EventFactory {
        LinkedList<EventCreator> events = new LinkedList<>();

        class EventCreator {
            Constructor<Event> constructor;
            long time;

            public EventCreator(Constructor<Event> constructor, long time) {
                this.constructor = constructor;
                this.time = time;
            }
        }

        @SuppressWarnings("unchecked")
        public EventFactory(String eventFile) {
            try {
                BufferedReader in = new BufferedReader(new FileReader(eventFile));
                String s;
                while ((s = in.readLine()) != null) {
                    String[] split = s.split(": ");
                    String className = split[0];
                    long offset = Long.parseLong(split[1]);
                    Class<?> outer = className.equals("Restart") ?
                            GreenhouseControls2.class : GreenhouseControls.class;
                    String type = outer.getName() + "$" + className;
                    Class<Event> eventClass = (Class<Event>) Class.forName(type);
                    Constructor<Event> constructor = eventClass.getConstructor(outer, long.class);
                    events.add(new EventCreator(constructor, offset));
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        Iterator<Event> iterator() {
            return new Iterator<Event>() {
                Iterator<EventCreator> it = events.iterator();

                @Override
                public boolean hasNext() {
                    return it.hasNext();
                }

                @Override
                public Event next() {
                    EventCreator ec = it.next();
                    try {
                        return ec.constructor
                                .newInstance(GreenhouseControls2.this, ec.time);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }

                @Override
                public void remove() {
                    throw new UnsupportedOperationException();
                }
            };
        }
    }

    EventFactory factory;

    public GreenhouseControls2(String initFile) {
        factory = new EventFactory(initFile);
        LinkedList<Event> list = new LinkedList<>();
        Iterator<Event> it = factory.iterator();
        while (it.hasNext()) {
            Event e = it.next();
            if (e instanceof Bell || e instanceof Restart)
                continue;
            list.add(e);
        }
        it = factory.iterator();
        while (it.hasNext()) {
            Event e = it.next();
            addEvent(e);
            if (e instanceof Restart)
                ((Restart) e).setEventList(list.toArray(new Event[0]));
        }
    }
}

public class E11_GreenhouseController2 {
    public static void main(String[] args) {
        String config = "src/exercises/chapter18/E11_GreenhouseConfig.txt";
        GreenhouseControls2 gc = new GreenhouseControls2(config);
        if (args.length == 1)
            gc.addEvent(new GreenhouseControls.Terminate(Long.parseLong(args[0])));
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
Restarting system
Terminating
 */