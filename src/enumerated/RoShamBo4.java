package enumerated;

public enum RoShamBo4 implements Competitor<RoShamBo4> {
    PAPER {
        @Override
        public Outcome compete(RoShamBo4 opponent) {
            return compete(ROCK, opponent);
        }
    },
    SCISSORS {
        @Override
        public Outcome compete(RoShamBo4 opponent) {
            return compete(PAPER, opponent);
        }
    },
    ROCK {
        @Override
        public Outcome compete(RoShamBo4 opponent) {
            return compete(SCISSORS, opponent);
        }
    };

    Outcome compete(RoShamBo4 loser, RoShamBo4 opponent) {
        return ((opponent == this) ? Outcome.DRAW
                : ((opponent == loser) ? Outcome.WIN : Outcome.LOSE));
    }

    public static void main(String[] args) {
        RoShamBo.play(RoShamBo4.class, 20);
    }
}
/* Same output as RoShamBo2.java */