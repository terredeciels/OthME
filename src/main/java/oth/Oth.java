package oth;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.IntStream.range;
import static oth.Etat.*;

public class Oth implements Constantes {
    public List<Coups> lcoups;
    public Coups move;
    public int trait;
    int[] etats;
    int n;
    int caseO;
    int _case;
    int dir;
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

    public void gen(int t) {
        trait = t;
        range(0, 100).filter(c -> etats[c] == vide).forEach(c -> {
            caseO = c;
            lscore = new ArrayList<>();
            DIRS.forEach(this::statemachine);
        });
    }

    private void statemachine(int d) {
        dir = d;
        Etat state = O;

        while (true)
            if (state != null) {
                if ((state = state.exec(this)) == I) break;
            } else break;
    }

    public void fmove(boolean undo) {
        for (Score score : move.lscore()) {
            for (int k = 0; k <= score.n(); k++) {
                etats[move.sq0() + k * score.dir()] = undo ? -trait : trait;
            }
        }
        etats[move.sq0()] = undo ? vide : trait;
    }


}