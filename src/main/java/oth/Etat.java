package oth;

import java.util.List;

import static oth.Constantes.*;

public enum Etat {
    OPPTRAIT {
        @Override
        Etat exec(Oth o) {
            o.n++;
            return o.etats[o.nextcase()] == -o.trait ? OPPTRAIT : ENDLINE.exec(o);
        }
    },
    ENDLINE {
        @Override
        Etat exec(Oth o) {
            if (o.memetrait() && o.n - 1 != 0) {
                o.lscore.add(new Score(o.n - 1, o.dir));
                o.lcoups.add(new Coups(o.caseO, o.lscore));
            }
            o.n = 0;
            return null;
        }
    };

    abstract Etat exec(Oth o);

    public record Coups(int sq0, List<Score> lscore) {
        public static Coups NOMOVE;

        @Override
        public String toString() {
            return "(" + SCASES[sq0] + ", " + lscore + ")";
        }
    }

    public record Score(int n, int dir) {
    }
}
