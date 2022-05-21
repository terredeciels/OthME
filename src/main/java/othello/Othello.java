package othello;


import oth.Oth;

import java.util.ArrayList;

import static eval.OthEval.eval;
import static oth.Oth.Coups.NOMOVE;

public class Othello {

    private final OthPrinter othprint;
    Oth o;
    private boolean passe = true;
    private boolean findepartie;


    public Othello() {
        o = new Oth();
        othprint = new OthPrinter(o);
    }

    public void jouer() {
        findepartie = false;
        passe = false;
        o.lcoups = new ArrayList<>();
        while (true) {
            if (!findepartie) {
                o.gen(o.trait);
                o.move = eval(o.lcoups.stream().distinct().toList());
                passe_et_findepartie();
                o.changeside();
            } else break;
        }
        othprint.resultat();

    }

    private void passe_et_findepartie() {
        if (o.move == NOMOVE) if (passe) findepartie = true;
        else passe = true;
        else {
            if (passe) passe = false;
            o.fmove(!o.undomove);
            // othprint.affichage();
        }
    }


}