package oth;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.IntStream.range;
import static oth.Oth.ETAT.*;

public class Oth implements Constantes {
    public List<Coups> lcoups;
    public Coups move;
    public int trait;
    int n;
    int caseO;
    int _case;
    int dir;
    int[] etats;
    List<Score> lscore;

    public Oth(Oth o) {
        etats = o.etats;
        trait = -o.trait;
        lcoups = new ArrayList<>();
    }

    public Oth() {
        etats = ETATS_INIT;
        trait = noir;
        lcoups = new ArrayList<>();
    }

    public void gen(int trait) {
        this.trait = trait;
        range(0, 100).filter(c -> etats[c] == vide).forEach(c -> {
            caseO = c;
            lscore = new ArrayList<>();
            DIRS.forEach(this::statemachine);
        });
    }

    private void statemachine(int d) {
        dir = d;
        ETAT state = O;
        while (true)
            if (state != null) {
                if ((state = state.exec(this)) == I) break;
            } else break;
    }

    public boolean domove() {
        for (Score score : move.lscore) {
            for (int k = 0; k <= score.n; k++) {
                etats[move.sq0 + k * score.dir] = trait;
            }
        }
        etats[move.sq0] = trait;
        return true;
    }

    public void undomove() {
        for (Score score : move.lscore) {
            for (int k = 0; k <= score.n; k++) {
                etats[move.sq0 + k * score.dir] = -trait;
            }
        }
        etats[move.sq0] = vide;

    }

    @Override
    public String toString() {
        StringBuilder spos = new StringBuilder();
        range(0, 100).forEach(_case -> {
            if (etats[_case] == vide) spos.append("- ");
            else spos.append(print(etats[_case])).append(" ");
            if (_case % 10 == 9) spos.append("\n");
        });
        return spos.toString();
    }

    private String print(int etat) {
        return switch (etat) {
            case vide -> "_";
            case blanc -> "b";
            case noir -> "n";
            case out -> " ";
            default -> "?";
        };
    }

    public enum ETAT {
        O {
            @Override
            ETAT exec(Oth o) {
                o.n++;
                return o.etats[o._case = o.caseO + o.n * o.dir] == -o.trait ? O : I.exec(o);
            }
        },
        I {
            @Override
            ETAT exec(Oth o) {
                if (o.etats[o._case] == o.trait && o.n - 1 != 0) {
                    o.lscore.add(new Score(o.n - 1, o.dir));
                    o.lcoups.add(new Coups(o.caseO, o.lscore));
                }
                o.n = 0;
                return null;
            }
        };

        abstract ETAT exec(Oth o);

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
}