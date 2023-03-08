package exercises.chapter19;

import enumerated.Input;
import net.mindview.util.Generator;
import net.mindview.util.TextFile;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

import static enumerated.Input.*;
import static exercises.chapter19.VendingMachine2.State.RESTING;
import static net.mindview.util.Print.print;

/**
 * Exercise 10
 * Modify class VendingMachine (only) using EnumMap so that
 * one program can have multiple instances of VendingMachine.
 * {Args: VendingMachineInput.txt}
 */
enum Category {
    MONEY(NICKEL, DIME, QUARTER, DOLLAR),
    ITEM_SELECTION(TOOTHPASTE, CHIPS, SODA, SOAP),
    QUIT_TRANSACTION(ABORT_TRANSACTION),
    SHUT_DOWN(STOP);

    private Input[] values;

    Category(Input... types) {
        values = types;
    }

    private static EnumMap<Input, Category> categories = new EnumMap<>(Input.class);

    static {
        for (Category c : Category.class.getEnumConstants())
            for (Input type : c.values)
                categories.put(type, c);
    }

    public static Category categorize(Input input) {
        return categories.get(input);
    }
}

class VendingMachine2 {
    private static class Context {
        private State state = RESTING;
        private int amount = 0;
        private Input selection = null;
    }

    private static Map<Machine, Context> em =
            Collections.synchronizedMap(new EnumMap<>(Machine.class));

    static {
        for (Machine m : Machine.values())
            em.put(m, new Context());
    }

    enum Machine {
        M1, M2, M3
    }

    private static final ReentrantLock lock = new ReentrantLock();

    private static State state;
    private static int amount;
    private static Input selection;

    enum StateDuration { // Tagging enum
        TRANSIENT
    }

    enum State {
        RESTING {
            @Override
            void next(Input input) {
                switch (Category.categorize(input)) {
                    case MONEY:
                        amount += input.amount();
                        state = ADDING_MONEY;
                        break;
                    case SHUT_DOWN:
                        state = TERMINAL;
                    default:
                }
            }
        },
        ADDING_MONEY {
            @Override
            void next(Input input) {
                switch (Category.categorize(input)) {
                    case MONEY:
                        amount += input.amount();
                        break;
                    case ITEM_SELECTION:
                        selection = input;
                        if (amount < selection.amount())
                            print("Insufficient money for " + selection);
                        else
                            state = DISPENSING;
                        break;
                    case QUIT_TRANSACTION:
                        state = GIVING_CHANGE;
                        break;
                    case SHUT_DOWN:
                        state = TERMINAL;
                    default:
                }
            }
        },
        DISPENSING(StateDuration.TRANSIENT) {
            @Override
            void next() {
                print("here is your " + selection);
                amount -= selection.amount();
                state = GIVING_CHANGE;
            }
        },
        GIVING_CHANGE(StateDuration.TRANSIENT) {
            @Override
            void next() {
                if (amount > 0) {
                    print("Your change: " + amount);
                    amount = 0;
                }
                state = RESTING;
            }
        },
        TERMINAL {
            @Override
            void output() {
                print("Halted");
            }
        };

        private boolean isTransient = false;

        State() {
        }

        State(StateDuration trans) {
            isTransient = true;
        }

        void next(Input input) {
            throw new RuntimeException("Only call " +
                    "next(Input input) for non-transient states");
        }

        void next() {
            throw new RuntimeException("Only call next() for " +
                    "StateDuration.TRANSIENT states");
        }

        void output() {
            print(amount);
        }
    }

    static void run(Generator<Input> gen, Machine m) {
        Context ctx = em.get(m);
        while (ctx.state != State.TERMINAL) {
            lock.lock();
            state = ctx.state;
            amount = ctx.amount;
            selection = ctx.selection;
            try {
                state.next(gen.next());
                while (state.isTransient)
                    state.next();
                state.output();
                ctx.state = state;
                ctx.amount = amount;
                ctx.selection = selection;
                em.put(m, ctx);
            } finally {
                lock.unlock();
            }
            Thread.yield();
        }
    }
}

public class E10_VendingMachine2 {
    public static void main(String[] args) {
        for (VendingMachine2.Machine m : VendingMachine2.Machine.values()) {
            Generator<Input> gen = new RandomInputGenerator();
            if (args.length == 1)
                gen = new FileInputGenerator(args[0]);
            final Generator<Input> generator = gen;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    VendingMachine2.run(generator, m);
                }
            }).start();
        }
    }
}

// For a basic sanity check:
class RandomInputGenerator implements Generator<Input> {
    @Override
    public Input next() {
        return Input.randomSelection();
    }
}

// Create Inputs from a file of ';' - separated strings:
class FileInputGenerator implements Generator<Input> {
    private Iterator<String> input;

    public FileInputGenerator(String fileName) {
        input = new TextFile(fileName, ";").iterator();
    }

    @Override
    public Input next() {
        if (!input.hasNext())
            return null;
        return Enum.valueOf(Input.class, input.next().trim());
    }
}
/* Output:
25
50
75
here is your CHIPS
0
100
25
25
200
50
50
75
here is your CHIPS
0
here is your TOOTHPASTE
0
75
here is your CHIPS
0
100
100
200
here is your TOOTHPASTE
0
25
35
Your change: 35
0
25
35
Insufficient money for SODA
35
25
200
here is your TOOTHPASTE
0
25
35
Your change: 35
0
25
35
Insufficient money for SODA
35
60
70
75
60
70
75
Insufficient money for SODA
75
Your change: 75
0
Halted
Insufficient money for SODA
75
Your change: 75
0
35
Halted
Your change: 35
0
25
35
Insufficient money for SODA
35
60
70
75
Insufficient money for SODA
75
Your change: 75
0
Halted
 */