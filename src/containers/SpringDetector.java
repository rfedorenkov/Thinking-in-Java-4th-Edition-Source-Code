package containers;

import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.Map;

import static net.mindview.util.Print.print;

/**
 * What will the weather be?
 */
public class SpringDetector {
    // Uses a Groundhog or class derived from Groundhog:
    public static <T extends GroundHog> void detectSpring(Class<T> type) throws Exception {
        Constructor<T> ghog = type.getConstructor(int.class);
        Map<GroundHog, Prediction> map = new HashMap<>();
        for (int i = 0; i < 10; i++)
            map.put(ghog.newInstance(i), new Prediction());
        print("map = " + map);
        GroundHog gh = ghog.newInstance(3);
        print("Looking up prediction for " + gh);
        if (map.containsKey(gh))
            print(map.get(gh));
        else
            print("Key not found: " + gh);
    }

    public static void main(String[] args) throws Exception {
        detectSpring(GroundHog.class);
    }
}
/* Output:
map = {Groundhog #2=Early Spring!, Groundhog #4=Six more weeks of Winter!, Groundhog #0=Six more weeks of Winter!, Groundhog #1=Six more weeks of Winter!, Groundhog #7=Early Spring!, Groundhog #9=Six more weeks of Winter!, Groundhog #6=Early Spring!, Groundhog #3=Early Spring!, Groundhog #5=Early Spring!, Groundhog #8=Six more weeks of Winter!}
Looking up prediction for Groundhog #3
Key not found: Groundhog #3
 */