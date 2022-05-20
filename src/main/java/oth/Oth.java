package oth;

import java.util.ArrayList;
import java.util.List;

import static java.util.stream.IntStream.range;
import static java.util.stream.IntStream.rangeClosed;
import static oth.Etat.*;

public class Oth implements Constantes {
    public List<Coups> lcoups;
    List<Score> lscore;
    public Coups move;
    public int trait;
    public int[] etats;
    int n;
    int caseO;
    int _case;
    int dir;


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
        range(0, 100)
                .filter(c -> etats[c] == vide)
                .forEach(c -> {
                    caseO = c;
                    lscore = new ArrayList<>();
                    DIRS.forEach(this::statemachine);
                });
    }

    private void statemachine(int d) {
        dir = d;
        Etat state = OPPTRAIT;
        while (true)
            if ((state = state.exec(this)) == ENDLINE || state == null) break;
    }

    public void fmove(boolean undomove) {
        move.lscore()
                .forEach(score -> rangeClosed(0, score.n())
                        .forEach(n -> etats[move.sq0() + n * score.dir()] = undomove ? -trait : trait));
        etats[move.sq0()] = undomove ? vide : trait;
    }

    public int nextcase() {
        return _case=caseO + n * dir;
    }

    public boolean memetrait() {
       return etats[_case] == trait;
    }
}