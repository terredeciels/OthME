package othello;


import oth.Oth;

import static eval.OthEval.eval;
import static oth.Oth.Coups.NOMOVE;

public class Othello extends Oth {


    private final OthPrinter othprint;
    private boolean passe = true;
    private boolean findepartie;


    public Othello() {
        othprint = new OthPrinter(this);
    }

    public void jouer() {
        while (true) {
            if (!findepartie) {
                move = eval(gen(trait).stream().distinct().toList());
                if (move == NOMOVE) if (passe) findepartie = true;
                else passe = true;
                else {
                    if (passe) passe = false;
                    fmove(!undomove);
                    othprint.affichage();
                }
                changeside();
            } else break;
        }
        othprint.resultat();
    }


}