package enumerated;

import static enumerated.Outcome.*;

/**
 * Enums using "tables" instead of multiple dispatch.
 */
enum RoShamBo6 implements Competitor<RoShamBo6> {
    PAPER, SCISSORS, ROCK;

    private static Outcome[][] table = {
            { DRAW, LOSE, WIN }, // PAPER
            { WIN, DRAW, LOSE }, // SCISSORS
            { LOSE, WIN, DRAW }, // ROCK
    };


    @Override
    public Outcome compete(RoShamBo6 other) {
        return table[this.ordinal()][other.ordinal()];
    }

    public static void main(String[] args) {
        RoShamBo.play(RoShamBo6.class, 20);
    }
}
/* Same output as RoShamBo2.java */