package oth;

import java.util.List;

public enum Etat {
    O {
        @Override
        Etat exec(Oth o) {
            o.n++;
            return o.etats[o._case = o.caseO + o.n * o.dir] == -o.trait ? O : I.exec(o);
        }
    },
    I {
        @Override
        Etat exec(Oth o) {
            if (o.etats[o._case] == o.trait && o.n - 1 != 0) {
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
            return "(" + Constantes.SCASES[sq0] + ", " + lscore + ")";
        }
    }

    public record Score(int n, int dir) {

    }
}
