package enumerated;

import static net.mindview.util.Print.print;

/**
 * {Exec: javap -c LikeClasses}
 */
enum LikeClasses {
    WINKEN {
        @Override
        void behavior() {
            print("Behavior1");
        }
    },
    BLINKEN {
        @Override
        void behavior() {
            print("Behavior2");
        }
    },
    NOD {
        @Override
        void behavior() {
            print("Behavior3");
        }
    };

    abstract void behavior();
}

public class NotClasses {
    // void f1(LikeClasses.WINKEN instance) {} // Nope
}
/* Output:
Compiled from "NotClasses.java"
abstract class enumerated.LikeClasses extends java.lang.Enum<enumerated.LikeClasses> {
public static final enumerated.LikeClasses WINKEN;
public static final enumerated.LikeClasses BLINKEN;
public static final enumerated.LikeClasses NOD;
...
 */