package othello;


import eval.OthEval;
import oth.Oth;

import java.util.ArrayList;

import static oth.Oth.Coups.NOMOVE;

public class Othello extends Oth{


    private final OthPrinter othprint;
    private boolean passe = true;
    private boolean findepartie;


    public Othello() {
        othprint = new OthPrinter(this);
    }

    public void jouer() {
        while (!findepartie) {
            gen(trait);
            lcoups = lcoups.stream().distinct().toList();//sans doublon
            move = OthEval.aleatoire_evaluation(lcoups);
            passe_findepartie();
            trait = -trait;
            lcoups = new ArrayList<>();
        }
        othprint.resultat();
    }

    private void passe_findepartie() {
        if (move == NOMOVE) if (passe) findepartie = true;
        else passe = true;
        else {
            if (passe) passe = false;
            fmove(!undomove);
            othprint.affichage();
        }
    }


}